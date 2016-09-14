'use strict';

angular.module('portalApp').controller('PurchaseOrderController',
    function ($rootScope, $scope, $interval, $timeout, $stateParams, $state, $q, $log, entity, entityLines, PurchaseOrder, PoLine,
                  Party, User, AlertService, Principal, Company, currentUser, Attachment, UploadTools, sweet, FileSaver,
                  staticEnums) {
            $scope.user = currentUser;
            $scope.isXeon = currentUser.company.type === "XEON";

            Party.query({size: 10000, sort: 'name', type: 'OTHER'}).$promise.then(function(data){
                $scope.otherParties = data;
                Party.query({size: 10000, sort: 'name', type: 'SOLD_TO_PARTY'}).$promise.then(function(soldTo){
                    $.each(soldTo, function(idx, item){
                        $scope.otherParties.push(item);
                    });
                    $scope.soldToParties = soldTo;
                });
            });

            $scope.staticEnums = staticEnums;

            function resetPo(){
                $log.debug("clearing po");
                $scope.purchaseOrder = {
                    state: "UNPROCESSED",
                    poNumber: '',
                    accountReference: '',
                    reference: '',
                    collective: '',
                    service: "TRANSPORT",
                    serviceType: null,
                    serviceLevel: null,
                    customerType: "REGULAR",
                    tradeType: 'DOMESTIC',
                    modeOfTransport: null,
                    transportParty: null,
                    vehicleSize: null,
                    labourRequired: null,
                    //STEP 2
                    pickUpParty:  null,
                    pickUpType: "STANDARD",
                    collectionDate: new Date(),
                    collectionReference: '',
                    shipToParty: null,
                    shipToType: "HOME_DROPBOX",
                    dropOffDate: new Date(),

                    //STEP 3
                    cargoClassification: "NON_HAZARDOUS",
                    poLines: [],
                    //STEP 4
                    soldToParty: null,
                    //extras
                    company: null,
                    capturedBy: null
                };
                $scope.purchaseOrder.poLines.length = 0;
                $scope.attachments = [];
            }

            if(entity === undefined){
                resetPo();

                //code below is for the 'capture as another user feature'
                if($scope.isXeon){
                    $scope.capturedAs = {
                        company: null,
                        employee: null
                    };
                    $scope.companies = Company.query();
                    //WATCH when company is selected (for capture as company employee feature)
                    $scope.$watch(function() {
                        return $scope.capturedAs.company
                    }, function (company) {
                        $scope.capturedAs.employee = null;
                        if(company !== null && company !== undefined) {
                            $log.debug(company);
                            $log.debug("Selected company id : " + company.id);
                            $scope.employees = Company.getUsers({id: company.id});
                        }
                    });

                    //Tied to the above code, refreshed the screen once employee is set
                    $scope.$watch(function() {
                        return $scope.capturedAs.employee;
                    }, function (employee) {
                        $scope.clearPO();
                        $scope.purchaseOrder.user = employee;
                        $scope.user = employee;
                        $timeout(function(){
                            serviceWatch('TRANSPORT');
                        },200);
                    });

                }
            }else{
                $scope.purchaseOrder = entity;
                $.each($scope.purchaseOrder.poLines, function(idx){
                    $scope.purchaseOrder.poLines[idx].rowId = idx+1;
                });
                $scope.purchaseOrder.$promise.then(function(result){
                    $scope.attachments = PurchaseOrder.getAttachments({id: result.id});
                });

            }

            // $scope.load = function (id) {
            //     PurchaseOrder.get({id: id}, function (result) {
            //         $scope.purchaseOrder = result;
            //     });
            // };

            // Initial step
            $scope.step = 1;
            $scope.dateformat = 'yyyy-MM-dd';
            $scope.requiredFields = {};

            $scope.dateOptions = {
                //dateDisabled: disabled,
                formatYear: 'yy',
                // maxDate: new Date(new Date(todaysDate).setMonth(todaysDate.getMonth()+2)),
                //minDate: new Date(),
                startingDay: 0
            };

            // Disable weekend selection
            function disabled(data) {
                var date = data.date,
                    mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            };

            $scope.deletePoLine = function(rowId){
                $scope.purchaseOrder.poLines.splice(rowId-1, 1);
                $.each($scope.purchaseOrder.poLines, function(idx, po){
                    $scope.purchaseOrder.poLines[idx].rowId = idx+1;
                });
                calculateTotals($scope.purchaseOrder.poLines);
            };

            $scope.$watch('purchaseOrder.service', serviceWatch);
            function serviceWatch(value){
                $log.debug("watch service triggered with value : " + value);
                switch (value){
                    case 'WAREHOUSING':
                        limitSelect([
                            {name: 'serviceType', values: ["INBOUND","OUTBOUND"], defaultValue: "INBOUND"}
                        ]);
                        break;
                    case 'TRANSPORT':
                        limitSelect([
                            {name: 'serviceType', values: ["BREAKBULK_TRANSPORT", "CROSS_HAUL","FULL_CONTAINER_LOAD","FULL_TRUCK_LOAD"], defaultValue: "CROSS_HAUL"}
                        ]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.serviceType', serviceTypeWatch);
            function serviceTypeWatch(value){
                $log.debug("watch serviceType triggered with value : " + value);
                switch (value){
                    case "INBOUND":
                        forceSelected([ {name: 'transportParty', value: IFTET($scope.purchaseOrder.transportParty, 'XEON'), all: true} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["ECONOMY","EXPRESS","CUSTOMER_DROP_OFF"], defaultValue: "ECONOMY"},
                            {name: 'modeOfTransport', values: ["ROAD"], defaultValue: "ROAD"}
                        ]);
                        break;
                    case "OUTBOUND":
                        forceSelected([ {name: 'transportParty', value: IFTET($scope.purchaseOrder.transportParty, 'XEON'), all: true} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["ECONOMY","EXPRESS","CUSTOMER_COLLECTION"], defaultValue: "ECONOMY"},
                            {name: 'modeOfTransport', values: ["ROAD"], defaultValue: "ROAD"}
                        ]);
                        break;
                    case "BREAKBULK_TRANSPORT":
                        forceSelected([ {name: 'transportParty', value: 'XEON'} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["ECONOMY","ECONOMY_PLUS","EXPRESS","OVERNIGHT_EXPRESS"], defaultValue: "ECONOMY_PLUS"},
                            {name: 'modeOfTransport', values: ["AIR","ROAD","SEA"], defaultValue: "ROAD"}
                        ]);
                        break;
                    case "FULL_CONTAINER_LOAD":
                        forceSelected([ {name: 'transportParty', value: 'XEON'} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["ECONOMY","EXPRESS"], defaultValue: "ECONOMY"},
                            {name: 'modeOfTransport', values: ["ROAD_FCL","SEA_FCL"], defaultValue: "ROAD_FCL"}
                        ]);
                        break;
                    case "FULL_TRUCK_LOAD":
                        forceSelected([ {name: 'transportParty', value: 'XEON'} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["STANDARD_FTL"], defaultValue: "STANDARD_FTL"},
                            {name: 'modeOfTransport', values: ["ALL_MODES_OF_TRANSPORT_FTL"], defaultValue: "ALL_MODES_OF_TRANSPORT_FTL"}
                        ]);
                        break;
                    case "CROSS_HAUL":
                        forceSelected([ {name: 'transportParty', value: 'XEON'} ]);
                        limitSelect([
                            {name: 'serviceLevel', values: ["STANDARD_CROSS_DOCK"], defaultValue: "STANDARD_CROSS_DOCK"},
                            {name: 'modeOfTransport', values: ["ALL_MODES_OF_TRANSPORT_CH"], defaultValue: "ALL_MODES_OF_TRANSPORT_CH"}
                        ]);
                        break;
                }

                transportPartyWatch(IFTET($scope.purchaseOrder.transportParty, 'XEON'));
                // serviceLevelWatch(IFTET($scope.purchaseOrder.serviceLevel, 'ECONOMY'));
            };

            $scope.$watch('purchaseOrder.serviceLevel', serviceLevelWatch);
            function serviceLevelWatch(value){
                $log.debug("watch serviceLevel triggered with value : " + value);
                switch (value){
                    // case 'ECONOMY':
                    // case 'EXPRESS':
                    //     if($scope.purchaseOrder.serviceType !== 'FULL_CONTAINER_LOAD'){
                    //         break;
                    //     }
                    case 'STANDARD_CROSS_DOCK':
                    case 'STANDARD_FTL':
                        hideOrShow([
                            {name: 'vehicleSize', show: true, value: IFTET($scope.purchaseOrder.vehicleSize, 'FOUR_TON')},
                            {name: 'labourRequired', show: true, value:IFTET($scope.purchaseOrder.labourRequired, '1')}
                        ]);
                        break;
                    default:
                        hideOrShow([{name: 'vehicleSize'},{name: 'labourRequired'}]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.modeOfTransport', modeOfTransportWatch);
            function modeOfTransportWatch(value){
                $log.debug("watch modeOfTransport triggered with value : " + value);
                switch (value){
                    case "AIR":
                        hideOrShow([
                            {name: 'cvName', show: true, value: IFTET($scope.purchaseOrder.cvName, '')},
                            {name: 'cvNumber', show: true, value: IFTET($scope.purchaseOrder.cvNumber, '')},
                            {name: 'cvOrigin', show: true, value:IFTET($scope.purchaseOrder.cvOrigin, '')},
                            {name: 'cvContainerNo'},
                            {name: 'cvCarrierRef'},
                            {name: 'cvConsol', show: true, value:IFTET($scope.purchaseOrder.cvConsol, '')},
                            {name: 'cvWaybill', show: true, value:IFTET($scope.purchaseOrder.cvWaybill, '')},
                            {name: 'cvHouseWaybill', show: true, value:IFTET($scope.purchaseOrder.cvHouseWaybill, '')},
                            {name: 'cvWaybillIssue', show: true, value:IFTET(new Date($scope.purchaseOrder.cvWaybillIssue), '')},
                            {name: 'cvHouseWaybillIssue', show: true, value:IFTET(new Date($scope.purchaseOrder.cvHouseWaybillIssue), '')},
                            {name: 'cvShipper', show: true, value:IFTET($scope.purchaseOrder.cvShipper, '')},
                            {name: 'cvEtd', show: true, value:IFTET(new Date($scope.purchaseOrder.cvEtd), '')},
                            {name: 'cvEta', show: true, value:IFTET(new Date($scope.purchaseOrder.cvEta), '')},
                            {name: 'cvDestination', show: true, value:IFTET($scope.purchaseOrder.cvDestination, '')},
                            {name: 'cvCommodity', show: true, value:IFTET($scope.purchaseOrder.cvCommodity, '')}
                        ]);
                        break;
                    case "SEA":
                    case "SEA_FCL":
                        hideOrShow([
                            {name: 'cvName', show: true, value: IFTET($scope.purchaseOrder.cvName, '')},
                            {name: 'cvNumber', show: true, value: IFTET($scope.purchaseOrder.cvNumber, '')},
                            {name: 'cvOrigin', show: true, value:IFTET($scope.purchaseOrder.cvOrigin, '')},
                            {name: 'cvContainerNo', show: true, value:IFTET($scope.purchaseOrder.cvContainerNo, '')},
                            {name: 'cvCarrierRef', show: true, value:IFTET($scope.purchaseOrder.cvCarrierRef, '')},
                            {name: 'cvConsol', show: true, value:IFTET($scope.purchaseOrder.cvConsol, '')},
                            {name: 'cvWaybill', show: true, value:IFTET($scope.purchaseOrder.cvWaybill, '')},
                            {name: 'cvHouseWaybill', show: true, value:IFTET($scope.purchaseOrder.cvHouseWaybill, '')},
                            {name: 'cvWaybillIssue', show: true, value:IFTET(new Date($scope.purchaseOrder.cvWaybillIssue), '')},
                            {name: 'cvHouseWaybillIssue', show: true, value:IFTET(new Date($scope.purchaseOrder.cvHouseWaybillIssue), '')},
                            {name: 'cvShipper', show: true, value:IFTET($scope.purchaseOrder.cvShipper, '')},
                            {name: 'cvEtd', show: true, value:IFTET(new Date($scope.purchaseOrder.cvEtd), '')},
                            {name: 'cvEta', show: true, value:IFTET(new Date($scope.purchaseOrder.cvEta), '')},
                            {name: 'cvDestination', show: true, value:IFTET($scope.purchaseOrder.cvDestination, '')},
                            {name: 'cvCommodity', show: true, value:IFTET($scope.purchaseOrder.cvCommodity, '')}
                        ]);
                        break;
                    case "ROAD":
                    case "ROAD_FCL":
                    case "ALL_MODES_OF_TRANSPORT_CH":
                    case "ALL_MODES_OF_TRANSPORT_FTL":
                    default:
                        hideOrShow([{name: 'cvName'},{name: 'cvNumber'},{name: 'cvOrigin'},
                            {name: 'cvContainerNo'},
                            {name: 'cvContainerNo'},
                            {name: 'cvCarrierRef'},
                            {name: 'cvConsol'},
                            {name: 'cvWaybill'},
                            {name: 'cvWaybillIssue'},
                            {name: 'cvHouseWaybill'},
                            {name: 'cvHouseWaybillIssue'},
                            {name: 'cvShipper'},
                            {name: 'cvEtd'},
                            {name: 'cvEta'},
                            {name: 'cvDestination'},
                            {name: 'cvCommodity'}
                        ]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.transportParty', transportPartyWatch);
            function transportPartyWatch(value){
                $log.debug("watch transportParty triggered with value : " + value);
                var serviceType = $scope.purchaseOrder.serviceType;
                switch (value){
                    case "XEON":
                        hideOrShow([
                            {name: 'pickUpParty',
                                show: true,
                                value: IFTET($scope.purchaseOrder.pickUpParty, {id: 1})
                            },
                            {name: 'pickUpType',
                                show: serviceType !== "OUTBOUND",
                                value: IFTET($scope.purchaseOrder.pickUpType, 'STANDARD')
                            },
                            {name: 'collectionDate',
                                show: serviceType !== "OUTBOUND",
                                value: IFTET(new Date($scope.purchaseOrder.collectionDate))
                            },
                            {name: 'collectionReference',
                                show: serviceType !== "OUTBOUND",
                                value: IFTET($scope.purchaseOrder.collectionReference, 'ref')
                            },
                            {name: 'shipToParty',
                                show:  true,
                                value: IFTET($scope.purchaseOrder.shipToParty, {id: 1})
                            },
                            {name: 'shipToType',
                                show: serviceType !== "INBOUND",
                                value: IFTET($scope.purchaseOrder.shipToType, 'STANDARD')
                            },
                            {name: 'dropOffDate',
                                show: serviceType !== "INBOUND",
                                value: IFTET(new Date($scope.purchaseOrder.dropOffDate))
                            }
                        ]);
                        break;
                    case "CUSTOMER":
                        hideOrShow([
                            {name: 'pickUpType'},
                            {name: 'collectionDate'},
                            {name: 'collectionReference'},
                            {name: 'shipToType'},
                            {name: 'dropOffDate', show: true, value: IFTET(new Date($scope.purchaseOrder.dropOffDate), new Date())}
                        ]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.cargoClassification', function(value){
                $log.debug("watch cargoClassification triggered with value : " + value);
                switch (value){
                    case "HAZARDOUS":
                        hideOrShow([{name: 'cargoType', show: true, value: IFTET($scope.purchaseOrder.shipToType, 'CORROSIVES')}]);
                        break;
                    case "NON_HAZARDOUS":
                    default:
                        hideOrShow([{name: 'cargoType'}]);
                        break;
                }
            });

            function hideOrShow(elements){
                $.each(elements, function(idx, element) {
                    var field = $('#' + 'field_' + element.name);
                    if(field === undefined){
                        $log.error("hideOrShow, unknown field : " + element.name);
                    }else{
                        field.prop( "disabled", element.show === undefined ? true : !element.show);
                        $scope.requiredFields[element.name] = element.show === undefined ? false : element.show;
                        $scope.purchaseOrder[element.name] = element.value === undefined ? null : element.value;
                        if(element.show === undefined ? true : !element.show){
                            field.closest('.form-group').slideUp();
                        }else{
                            field.removeClass("animate-glower").addClass("animate-glower");
                            field.closest('.form-group').slideDown();
                        }
                    }
                })
            }

            function forceSelected(elements){
                $.each(elements, function(idx, element) {
                    var field = $('#' + 'field_' + element.name);
                    if(field === undefined){
                        alert("unknown field : " + element.name);
                    }else{
                        $scope.purchaseOrder[element.name] = element.value === undefined ? null : element.value;
                        field.prop( "disabled", element.all === undefined ? true : !element.all);
                    }
                })
            }

            function limitSelect(elements){
                $.each(elements, function(idx, element) {
                    var field = $('#' + 'field_' + element.name);
                    if(field === undefined){
                        alert("unknown field : " + element.name);
                    }else{
                        element.selectOptions = element.values;
                        $scope.staticEnums[element.name + 's'] = element.values;
                        $scope.purchaseOrder[element.name] = element.defaultValue;
                    }
                })
            }

            var onSaveSuccess = function (result) {
                $scope.$emit('portalApp:purchaseOrderUpdate', result);
                resetPo();
                $scope.isSaving = false;
                $state.go('purchaseOrder');
            };
            $scope.closeAlert = function(index) {
                $scope.purchaseOrder.comment = null;
            };
            var onSaveError = function (result) {
                $log.error(result);
                var msg = "Failed to create order :\n\n";
                $.each(result.data.fieldErrors, function(idx, error){
                    msg+= "Field " + error.field + " on " + error.objectName;
                    switch (error.message){
                        case "NotNull":
                            msg+= " cant be null. Please enter a valid value";
                            break;
                        default:
                            $log.log("have not seen this error before : " + error.message);
                    }
                    msg+= "\n";
                });
                AlertService.error(msg);
                $scope.isSaving = false;
            };

            function showProgress(percentage){
                $log.debug("Percentage : " + percentage);
                if(percentage === 100) {
                    sweet.show({
                        timer: 4000,
                        title: 'PO created and attachments uploaded',
                        text: ''
                        + '<div class="m" ng-show="uploadingAttachments">'
                        + '    <div class="progress m-t-xs full progress-striped active animated">'
                        + '        <div style="width: ' + percentage + '%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class="progress-bar progress-bar-success animated">'
                        + '        </div>'
                        + '    </div>'
                        + '</div>',
                        html: true
                    });
                }else{
                    sweet.show({
                        timer: 20000,
                        title: 'Busy uploading attachments',
                        text: ''
                        + '<div class="m" ng-show="uploadingAttachments">'
                        + '    <div class="progress m-t-xs full progress-striped active animated">'
                        + '        <div style="width: ' + percentage + '%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class="progress-bar progress-bar-success animated">'
                        + '        </div>'
                        + '    </div>'
                        + '</div>',
                        html: true,
                        showConfirmButton: false
                    });
                }
            }

            $scope.save = function () {
                $log.debug("Saving po to backend");
                $scope.isSaving = true;
                $log.debug($scope.purchaseOrder.poLines);
                $log.debug($scope.purchaseOrder);
                if ($scope.purchaseOrder.id != null) {
                    PurchaseOrder.update($scope.purchaseOrder, onSaveSuccess, onSaveError);
                } else {
                    PurchaseOrder.save($scope.purchaseOrder,
                        function (result) {
                            $log.debug("Result of saving : ");
                            $log.debug(result);
                            $log.debug("Now gonna try and save the attachments");
                            $scope.uploadingAttachments = true;
                            var completed = 0;
                            var attachments = $scope.attachments;
                            $log.debug(attachments);
                            if (attachments && attachments.length) {
                                for (var i = 0; i < attachments.length; i++) {
                                    attachments[i].purchaseOrder = {
                                        id: result.id
                                    };
                                    $log.debug(attachments[i]);
                                    UploadTools.upload(attachments[i],
                                        function (success) {
                                            completed++;
                                            showProgress(completed * (100 / attachments.length));
                                            if (completed === attachments.length) {
                                                $scope.uploadingAttachments = false;
                                                showProgress(100);
                                                onSaveSuccess(result);
                                            }
                                        },
                                        function (err) {
                                            $log.error(err);
                                            $scope.uploadingAttachments = false;
                                        },
                                        function (progress) {
                                            $log.debug(progress);
                                            showProgress(completed * (100 / attachments.length) + progress.percentage / attachments.length / 2);
                                        }
                                    );
                                }
                            } else {
                                showProgress(100);
                                onSaveSuccess(result);
                            }
                        },
                        onSaveError
                    );
                }
            };

            $scope.clear = function () {
                $uibModalInstance.dismiss('cancel');
            };

            $scope.clearPO = function () {
                $log.debug("clearing po");
                resetPo();
                entity = undefined;
                $scope.step = 1;
            };

            $scope.dateSetup = {
                dpDropOff: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpDropOff.opened = true;
                    }
                },
                dpEta: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpEta.opened = true;
                    }
                },
                dpEtd: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpEtd.opened = true;
                    }
                },
                dpCollectionDate: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpCollectionDate.opened = true;
                    }
                },
                dpWaybillIssue: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpWaybillIssue.opened = true;
                    }
                },
                dpHouseWaybillIssue: {
                    opened: false,
                    open: function($event){
                        $scope.dateSetup.dpHouseWaybillIssue.opened = true;
                    }
                }
            };

            // Wizard functions
            $scope.wizard = {
                show: function (number) {
                    $scope.step = number;
                },
                next: function () {
                    $scope.step++;
                },
                prev: function () {
                    $scope.step--;
                }
            };

            if($rootScope.poLineUpdateBroadcast != undefined){
                $log.debug("clearning previous registered poLineUpdateBroadcast event");
                $rootScope.poLineUpdateBroadcast();
            }
            // listen for poLine add event
            $rootScope.poLineUpdateBroadcast = $rootScope.$on('portalApp:poLineUpdate', function (event, poLine) {
                $log.debug("current row id : " + poLine.rowId);
                if(poLine.rowId === null || poLine.rowId === undefined || $scope.purchaseOrder.poLines.length == 0) {
                    poLine.rowId = $scope.purchaseOrder.poLines.length+1;
                    $log.debug("new Row id : " + poLine.rowId);
                    $scope.purchaseOrder.poLines.push(poLine);
                }else{
                    $.each($scope.purchaseOrder.poLines, function(idx, po){
                        if(po.rowId === poLine.rowId){
                            $log.debug("Found match");
                            angular.copy(poLine, $scope.purchaseOrder.poLines[idx]);
                        }
                    });
                }
                calculateTotals($scope.purchaseOrder.poLines);
            });

            if($rootScope.partyUpdateBroadcast != undefined){
                $log.debug("clearning previous registered partyUpdateBroadcast event");
                $rootScope.partyUpdateBroadcast();
            }
            // listen for party create event
            $rootScope.partyUpdateBroadcast = $rootScope.$on('portalApp:partyUpdate', function (event, data) {
                switch(data.for){
                    case("pickup"):
                        $scope.otherParties.push(data);
                        $scope.purchaseOrder.pickUpParty = data;
                        break;
                    case("dropoff"):
                        $scope.otherParties.push(data);
                        $scope.purchaseOrder.shipToParty = data;
                        break;
                    case("soldTo"):
                        $scope.soldToParties.push(data);
                        $scope.purchaseOrder.soldToParty = data;
                        break;
                }
            });

            $scope.addAttachment = function(){
                $log.debug("addAttachment()");
                $scope.attachments.push({
                    category: $scope.attachmentCategories[0],
                    description: null
                })
            };

            $scope.deleteAttachment = function(index){
                $log.debug("deleteAttachment(" + index + ")");
                $scope.attachments.splice(index, 1);
            };

            $scope.downloadAttachment = function(attachment){
                $scope.isDownloadingAttachment = true;
                $scope.selectedAttachment = attachment;
                $log.debug("downloadAttachment(" + attachment.uuid + " )");
                Attachment.get({uuid: attachment.uuid}).$promise.then(function(imageBlob){
                    $log.debug(imageBlob.response.type);
                    FileSaver.saveAs(imageBlob.response, attachment.category + "-" + attachment.id);
                    $scope.isDownloadingAttachment = false;
                },function(err){
                    $scope.isDownloadingAttachment = false;
                    $log.error("Count not download attachment");
                    $log.error(err);
                });
            };

            function calculateTotals(lines){
                $scope.totalWeight = 0;
                $scope.totalCubes = 0;
                $.each(lines, function(idx, line){
                    $scope.totalWeight += parseFloat(line.grossWeight * line.orderQuantity);
                    $scope.totalCubes += parseFloat(((line.height * line.length * line.width)/1000000) * line.orderQuantity);
                })
            }

            calculateTotals($scope.purchaseOrder.poLines);


            function IFTET(ifThis, elseThat, debug){
                if(ifThis === undefined || ifThis === null){
                    if(elseThat === undefined || elseThat === null){
                        return null;
                    }else{
                        return elseThat;
                    }
                }else{
                    return ifThis;
                }
            }
        });

