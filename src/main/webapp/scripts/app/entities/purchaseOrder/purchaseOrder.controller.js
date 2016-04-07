'use strict';

angular.module('portalApp').controller('PurchaseOrderController',
    ['$rootScope', '$scope', '$stateParams', '$q', 'entity', 'entityLines', 'PurchaseOrder', 'PoLine', 'Party', 'User', 'AlertService', 'Principal',
        function ($rootScope, $scope, $stateParams, $q, entity, entityLines, PurchaseOrder, PoLine, Party, User, AlertService, Principal) {
            var todaysDate = new Date();
            // Initial step
            $scope.step = 4;
            $scope.purchaseOrder = entity;
            $scope.purchaseOrderLines = entityLines === undefined ? [] : entityLines;
            $scope.dateformat = 'yyyy-MM-dd';
            $scope.dateOptions = {
                dateDisabled: disabled,
                // maxDate: new Date(new Date(todaysDate).setMonth(todaysDate.getMonth()+2)),
                // minDate: new Date(new Date(todaysDate).setDate(todaysDate.getDate+1)),
                startingDay: 0
            };

            $scope.isXeon = false;
            Principal.hasAuthority('ROLE_USER').then(function() {
                $scope.isXeon = true;
            });

            // Disable weekend selection
            function disabled(data) {
                var date = data.date,
                    mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }

            if($scope.purchaseOrder === undefined){
                $scope.purchaseOrder = {
                    accountReference: "1",
                    carrierVesselName: "1",
                    carrierVesselNumber: "1",
                    collective: "1",
                    customerType: "REGULAR",
                    deliveryDate: new Date('2016-04-02'),
                    employeeId: 1,
                    modeOfTransport: "AIR_DELIVERIES",
                    pickUpPartyId: 1,
                    pickUpType: "STANDARD",
                    poNumber: "1",
                    reference: "1",
                    serviceLevel: "ECONOMY",
                    shipToPartyId: 1,
                    shipToType: "HOME_DROP_BOX",
                    telephone: "1"
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
                    netWeight: 11,
                    orderQuantity: 123,
                    unitOfMeasure: "123",
                    warehouse: "123",
                    width: 1
                }];
            }

            $scope.polines = PoLine.query();
            $scope.shiptopartys = Party.query({filter: 'purchaseorder-is-null'});
            // $q.all([$scope.purchaseOrder.$promise, $scope.shiptopartys.$promise]).then(function() {
            //     if (!$scope.purchaseOrder.shipToPartyId) {
            //         return $q.reject();
            //     }
            //     return Party.get({id : $scope.purchaseOrder.shipToPartyId}).$promise;
            // }).then(function(shipToParty) {
            //     $scope.shiptopartys.push(shipToParty);
            // });
            $scope.pickuppartys = Party.query({filter: 'purchaseorder-is-null'});
            // $q.all([$scope.purchaseOrder.$promise, $scope.pickuppartys.$promise]).then(function() {
            //     if (!$scope.purchaseOrder.pickUpPartyId) {
            //         return $q.reject();
            //     }
            //     return Party.get({id : $scope.purchaseOrder.pickUpPartyId}).$promise;
            // }).then(function(pickUpParty) {
            //     $scope.pickuppartys.push(pickUpParty);
            // });
            $scope.employees = User.query();
            $scope.load = function (id) {
                PurchaseOrder.get({id: id}, function (result) {
                    $scope.purchaseOrder = result;
                });
            };

            var onSaveSuccess = function (result) {
                $scope.$emit('portalApp:purchaseOrderUpdate', result);
                $scope.isSaving = false;
            };

            var onSaveError = function (result) {
                $scope.isSaving = false;
            };

            $scope.save = function () {
                $scope.isSaving = true;
                if ($scope.purchaseOrder.id != null) {
                    PurchaseOrder.update($scope.purchaseOrder, onSaveSuccess, onSaveError);
                } else {
                    PurchaseOrder.save($scope.purchaseOrder, onSaveSuccess, onSaveError);
                }
            };

            $scope.clear = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.datePickerForCaptureDate = {};

            $scope.datePickerForCaptureDate.status = {
                opened: false
            };

            $scope.datePickerForCaptureDateOpen = function ($event) {
                $scope.datePickerForCaptureDate.status.opened = true;
            };
            $scope.datePickerForDeliveryDate = {};

            $scope.datePickerForDeliveryDate.status = {
                opened: false
            };

            $scope.datePickerForDeliveryDateOpen = function ($event) {
                $scope.datePickerForDeliveryDate.status.opened = true;
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

            // listen for the event in the relevant $scope
            $rootScope.$on('portalApp:poLineUpdate', function (event, data) {
                $scope.purchaseOrderLines.push(data);
                calculateTotals($scope.purchaseOrderLines);
            });


            function calculateTotals(lines){
                $scope.totalWeight = 0;
                $scope.totalCubes = 0;
                $.each(lines, function(idx, line){
                    console.log(line.grossWeight);
                    $scope.totalWeight += parseFloat(line.grossWeight);
                    $scope.totalCubes += (parseFloat(line.height) * parseFloat(line.length) * parseFloat(line.width));
                })
            }

            $scope.$watch(function(scope) {return scope.purchaseOrderLines },
                function(newLines, oldLines) {
                    console.log("lines changed");
                    calculateTotals(newLines);
                }
            );
        }]);
