'use strict';

angular.module('portalApp').controller('PurchaseOrderController',
    ['$rootScope', '$scope', '$stateParams', '$state', '$q', '$log', 'entity', 'entityLines', 'PurchaseOrder', 'PoLine', 'Party', 'User', 'AlertService', 'Principal',
        function ($rootScope, $scope, $stateParams, $state, $q, $log, entity, entityLines, PurchaseOrder, PoLine, Party, User, AlertService, Principal) {
            $scope.purchaseOrder = entity;
            $scope.purchaseOrderLines = entityLines === undefined ? [] : entityLines;

            $scope.isXeon = false;
            Principal.hasAuthority('ROLE_USER').then(function() {
                $scope.isXeon = true;
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

            $scope.$watch('purchaseOrder.serviceLevel', function(value){
                $log.debug("watch transportParty triggered with value : " + value);
                switch (value){
                    case "ECONOMY":
                    case "EXPRESS_AM":
                    case "EXPRESS_PM":
                        hideOrShow([{name: 'vehicleSize'},{name: 'labourRequired'}]);
                        break;
                    case "DEDICATED_EXPRESS":
                    case "DEDICATED_ECONOMY":
                        hideOrShow([
                            {name: 'vehicleSize', show: true, value: 'FOUR_TON'},
                            {name: 'labourRequired', show: true, value: '1'}
                        ]);
                        break;
                }
            });
            $scope.$watch('purchaseOrder.serviceType', function(value){
                $log.debug("watch transportParty triggered with value : " + value);
                switch (value){
                    case "COURIER":
                        limitSelect([
                            {name: 'transportParty', value: 'XEON'}
                        ]);
                        break;
                    case "INBOUND":
                        limitSelect([
                            {name: 'transportParty', value: 'XEON', all: true}
                        ]);
                        break;
                    case "OUTBOUND":
                        limitSelect([
                            {name: 'transportParty', value: 'XEON', all: true}
                        ]);
                        break;
                }
                transportPartyWatch("XEON");
            });

            $scope.$watch('purchaseOrder.transportParty', transportPartyWatch);

            function transportPartyWatch(value){
                $log.debug("watch transportParty triggered with value : " + value);
                var serviceType = $scope.purchaseOrder.serviceType;
                switch (value){
                    case "XEON":
                        hideOrShow([
                            {name: 'pickUpParty',
                                show: true,
                                value: {
                                    id: 1
                                }
                            },
                            {name: 'pickUpType',
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: "STANDARD"
                            },
                            {name: 'collectionDate',
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: new Date()
                            },
                            {name: 'collectionReference',
                                show: serviceType === "COURIER" || serviceType === "INBOUND" ? true : false,
                                value: "ref"
                            },
                            {name: 'shipToParty',
                                show:  true,
                                value: {
                                    id: 1
                                }
                            },
                            {name: 'shipToType',
                                show: serviceType === "COURIER" || serviceType === "OUTBOUND" ? true : false,
                                value: "STANDARD"
                            },
                            {name: 'dropOffDate',
                                show: serviceType === "COURIER" || serviceType === "OUTBOUND" ? true : false,
                                value: new Date()
                            }
                        ]);
                        break;
                    case "CUSTOMER":
                        hideOrShow([
                            {name: 'pickUpType'},
                            {name: 'collectionDate'},
                            {name: 'collectionReference'},
                            {name: 'shipToType'},
                            {name: 'dropOffDate', show: true, value: new Date()}
                        ]);
                        break;
                }
            };

            $scope.$watch('purchaseOrder.cargoClassification', function(value){
                $log.debug("watch cargoClassification triggered with value : " + value);
                switch (value){
                    case "NON_HAZARDOUS":
                        hideOrShow([{name: 'cargoType'}]);
                        break;
                    case "HAZARDOUS":
                        hideOrShow([{name: 'cargoType', show: true, value: "CORROSIVES"}]);
                        break;
                }
            });

            function hideOrShow(elements){
                $.each(elements, function(idx, element) {
                    var field = $('#' + 'field_' + element.name);
                    if(field === undefined){
                        alert("unknown field : " + element.name);
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

            if($scope.purchaseOrder === undefined){
                $scope.purchaseOrder = {
                    state: "UNPROCESSED",
                    poNumber: "1",
                    accountReference: "1",
                    reference: "1",
                    collective: "1",
                    serviceType: "COURIER",
                    serviceLevel: "ECONOMY",
                    customerType: "REGULAR",
                    transportParty: "XEON",
                    vehicleSize: null,
                    labourRequired: null,
                    //STEP 2
                    pickUpParty:  null,
                    pickUpType: "STANDARD",
                    collectionDate: new Date(),
                    collectionReference: "1",
                    shipToParty: null,
                    shipToType: "HOME_DROP_BOX",
                    dropOffDate: new Date(),
                    //STEP 3
                    cargoClassification: "NON_HAZARDOUS",
                    //STEP 4
                    soldToParty: null

                };
            }
            if($scope.purchaseOrderLines.length === 0){
                $scope.purchaseOrderLines = [{
                    batchNumber: "123",
                    grossWeight: 12,
                    height: 1,
                    id: null,
                    length: 0.2,
                    materialNumber: "123",
                    materialType: "PACKAGE",
                    netWeight: 11,
                    orderQuantity: 123,
                    unitOfMeasure: "123",
                    warehouse: "123",
                    width: 1
                }];
            }

            var onSaveSuccess = function (result) {
                $scope.$emit('portalApp:purchaseOrderUpdate', result);
                $scope.isSaving = false;
                $state.go('purchaseOrder');
            };

            var onSaveError = function (result) {
                $log.log(result);
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
                $log.log($scope.purchaseOrderLines);
                $scope.purchaseOrder.poLines = $scope.purchaseOrderLines;
                if ($scope.purchaseOrder.id != null) {
                    $log.log("not saving as yet...");
                    // PurchaseOrder.update($scope.purchaseOrder, onSaveSuccess, onSaveError);
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
            $rootScope.$on('portalApp:poLineUpdate', function (event, data) {
                $scope.purchaseOrderLines.push(data);
                calculateTotals($scope.purchaseOrderLines);
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
                    $scope.totalWeight += parseFloat(line.grossWeight);
                    $scope.totalCubes += (parseFloat(line.height) * parseFloat(line.length) * parseFloat(line.width));
                })
            }

            // $scope.$watch(function(scope) {return scope.purchaseOrderLines },
            //     function(newLines, oldLines) {
            //         $log.log("lines changed");
            //         calculateTotals(newLines);
            //     }
            // );
        }]);
