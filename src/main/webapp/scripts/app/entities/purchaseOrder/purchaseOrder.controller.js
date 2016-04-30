'use strict';

angular.module('portalApp').controller('PurchaseOrderController',
    ['$rootScope', '$scope', '$stateParams', '$state', '$q', '$log', 'entity', 'entityLines', 'PurchaseOrder', 'PoLine', 'Party', 'User', 'AlertService', 'Principal',
        function ($rootScope, $scope, $stateParams, $state, $q, $log, entity, entityLines, PurchaseOrder, PoLine, Party, User, AlertService, Principal) {
            if(entity === undefined){
                $scope.purchaseOrder = {
                    state: "UNPROCESSED",
                    poNumber: "1",
                    accountReference: "1",
                    reference: "1",
                    collective: "1",
                    serviceType: "COURIER",
                    serviceLevel: "ECONOMY",
                    customerType: "REGULAR",
                    modeOfTransport: 'ROAD',
                    transportParty: "XEON",
                    vehicleSize: null,
                    labourRequired: null,
                    //STEP 2
                    pickUpParty:  null,
                    pickUpType: "STANDARD",
                    collectionDate: new Date(),
                    collectionReference: "1",
                    shipToParty: null,
                    shipToType: "HOME_DROPBOX",
                    dropOffDate: new Date(),
                    //STEP 3
                    cargoClassification: "NON_HAZARDOUS",
                    poLines: [{
                        rowId: 1,
                        batchNumber: "123",
                        grossWeight: 12,
                        height: 100,
                        id: null,
                        length: 100,
                        materialNumber: "123",
                        materialType: "PACKAGE",
                        netWeight: 11,
                        orderQuantity: 10,
                        unitOfMeasure: "123",
                        warehouse: "123",
                        width: 100
                    }],
                    //STEP 4
                    soldToParty: null
                };
            }else{
                $scope.purchaseOrder = entity;
                $.each($scope.purchaseOrder.poLines, function(idx, po){
                    $scope.purchaseOrder.poLines[idx].rowId = idx+1;
                });
            }

            $scope.isXeon = false;
            Principal.identity().then(function(user) {
                $scope.user = user;
                $scope.isXeon = user.company.type === "XEON";
            });

            $scope.shiptopartys = Party.query({filter: 'purchaseorder-is-null'});
            $scope.pickuppartys = Party.query({filter: 'purchaseorder-is-null'});

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
                dateDisabled: disabled,
                formatYear: 'yy',
                // maxDate: new Date(new Date(todaysDate).setMonth(todaysDate.getMonth()+2)),
                minDate: new Date(),
                startingDay: 0
            };

            // Disable weekend selection
            function disabled(data) {
                var date = data.date,
                    mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }

            $scope.deletePoLine = function(rowId){
                $scope.purchaseOrder.poLines.splice(rowId-1, 1);
                $.each($scope.purchaseOrder.poLines, function(idx, po){
                    $scope.purchaseOrder.poLines[idx].rowId = idx+1;
                });
            }

            $scope.$watch('purchaseOrder.serviceLevel', serviceLevelWatch);
            function serviceLevelWatch(value){
                $log.debug("watch serviceLevel triggered with value : " + value);
                switch (value){
                    case "ECONOMY":
                    case "EXPRESS_AM":
                    case "EXPRESS_PM":
                        hideOrShow([{name: 'vehicleSize'},{name: 'labourRequired'}]);
                        break;
                    case "DEDICATED_EXPRESS":
                    case "DEDICATED_ECONOMY":
                        hideOrShow([
                            {name: 'vehicleSize', show: true, value: IFTET($scope.purchaseOrder.vehicleSize, 'FOUR_TON')},
                            {name: 'labourRequired', show: true, value:IFTET($scope.purchaseOrder.labourRequired, '1')}
                        ]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.modeOfTransport', modeOfTransportWatch);
            function modeOfTransportWatch(value){
                $log.debug("watch modeOfTransport triggered with value : " + value);
                switch (value){
                    case "AIR_TRANSFERS":
                    case "AIR_DELIVERIES":
                    case "SEA":
                        hideOrShow([
                            {name: 'cvName', show: true, value: IFTET($scope.purchaseOrder.cvName, '')},
                            {name: 'cvNumber', show: true, value: IFTET($scope.purchaseOrder.cvNumber, '')},
                            {name: 'cvOrigin', show: true, value:IFTET($scope.purchaseOrder.cvOrigin, '')},
                            {name: 'cvContainerNo', show: true, value:IFTET($scope.purchaseOrder.cvContainerNo, '')},
                            {name: 'cvCarrierRef', show: true, value:IFTET($scope.purchaseOrder.cvCarrierRef, '')},
                            {name: 'cvConsol', show: true, value:IFTET($scope.purchaseOrder.cvConsol, '')},
                            {name: 'cvWaybill', show: true, value:IFTET($scope.purchaseOrder.cvWaybill, '')},
                            {name: 'cvWaybillIssue', show: true, value:IFTET($scope.purchaseOrder.cvWaybillIssue, '')},
                            {name: 'cvHouseWaybill', show: true, value:IFTET($scope.purchaseOrder.cvHouseWaybill, '')},
                            {name: 'cvHouseWaybillIssue', show: true, value:IFTET($scope.purchaseOrder.cvHouseWaybillIssue, '')},
                            {name: 'cvShipper', show: true, value:IFTET($scope.purchaseOrder.cvShipper, '')},
                            {name: 'cvEtd', show: true, value:IFTET($scope.purchaseOrder.cvEtd, '')},
                            {name: 'cvEta', show: true, value:IFTET($scope.purchaseOrder.cvEta, '')},
                            {name: 'cvDestination', show: true, value:IFTET($scope.purchaseOrder.cvDestination, '')},
                            {name: 'cvCommodity', show: true, value:IFTET($scope.purchaseOrder.cvCommodity, '')}
                        ]);
                        break;
                    case "ROAD":
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

            $scope.$watch('purchaseOrder.serviceType', serviceTypeWatch);
            function serviceTypeWatch(value){
                $log.debug("watch serviceType triggered with value : " + value);
                switch (value){
                    case "COURIER":
                        limitSelect([
                            {name: 'transportParty', value: IFTET($scope.purchaseOrder.transportParty, 'XEON')}
                        ]);
                        break;
                    case "INBOUND":
                        limitSelect([
                            {name: 'transportParty', value: IFTET($scope.purchaseOrder.transportParty, 'XEON'), all: true}
                        ]);
                        break;
                    case "OUTBOUND":
                        limitSelect([
                            {name: 'transportParty', value: IFTET($scope.purchaseOrder.transportParty, 'XEON'), all: true}
                        ]);
                        break;
                }
                transportPartyWatch(IFTET($scope.purchaseOrder.transportParty, 'XEON'));
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
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: IFTET($scope.purchaseOrder.pickUpType, 'STANDARD')
                            },
                            {name: 'collectionDate',
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: IFTET(new Date($scope.purchaseOrder.collectionDate))
                            },
                            {name: 'collectionReference',
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: IFTET($scope.purchaseOrder.collectionReference, 'ref')
                            },
                            {name: 'shipToParty',
                                show:  true,
                                value: IFTET($scope.purchaseOrder.shipToParty, {id: 1})
                            },
                            {name: 'shipToType',
                                show: serviceType === "COURIER" || serviceType === "OUTBOUND" ? true : false,
                                value: IFTET($scope.purchaseOrder.shipToType, 'STANDARD')
                            },
                            {name: 'dropOffDate',
                                show: serviceType === "COURIER" || serviceType === "OUTBOUND" ? true : false,
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

            function limitSelect(elements){
                $.each(elements, function(idx, element) {
                    var field = $('#' + 'field_' + element.name);
                    if(field === undefined){
                        alert("unknown field : " + element.name);
                    }else{
                        field.prop( "disabled", element.all === undefined ? true : !element.all);
                        $scope.purchaseOrder[element.name] = element.value === undefined ? null : element.value;
                    }
                })
            }

            var onSaveSuccess = function (result) {
                $scope.$emit('portalApp:purchaseOrderUpdate', result);
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

            $scope.save = function () {
                $scope.isSaving = true;
                $log.log($scope.purchaseOrder.poLines);
                if ($scope.purchaseOrder.id != null) {
                    $log.log("not saving as yet...");
                    PurchaseOrder.update($scope.purchaseOrder, onSaveSuccess, onSaveError);
                } else {
                    $log.log($scope.purchaseOrder);
                    PurchaseOrder.save($scope.purchaseOrder, onSaveSuccess, onSaveError);
                }
            };

            $scope.clear = function () {
                $uibModalInstance.dismiss('cancel');
            };

            $scope.dpDropOff = {
                opened: false
            };

            $scope.dpDropOffOpen = function($event){
                $scope.dpDropOff.opened = true;
            };

            $scope.dpCollectionDate = {
                opened: false
            };

            $scope.dpCollectionDateOpen = function($event){
                $scope.dpCollectionDate.opened = true;
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

            // listen for poLine add event
            $rootScope.$on('portalApp:poLineUpdate', function (event, poLine) {
                $log.debug("current row id : " + poLine.rowId);
                if(poLine.rowId === null || poLine.rowId === undefined) {
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

            // listen for party create event
            $rootScope.$on('portalApp:partyUpdate', function (event, data) {
                $scope.pickuppartys.push(data);
                switch(data.for){
                    case("pickup"):
                        $scope.purchaseOrder.pickUpParty = { id: data.id };
                        break;
                    case("dropoff"):
                        $scope.purchaseOrder.shipToParty = { id: data.id };
                        break;
                    case("soldTo"):
                        $scope.purchaseOrder.soldToParty = { id: data.id };
                        break;
                }
            });


            function calculateTotals(lines){
                $scope.totalWeight = 0;
                $scope.totalCubes = 0;
                $.each(lines, function(idx, line){
                    $log.log(line.grossWeight);
                    $scope.totalWeight += parseFloat(line.grossWeight * line.orderQuantity);
                    $scope.totalCubes += parseFloat(((line.height * line.length * line.width)/1000000) * line.orderQuantity);
                })
            }

            calculateTotals($scope.purchaseOrder.poLines);


            function IFTET(ifThis, elseThat){
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
        }]);
