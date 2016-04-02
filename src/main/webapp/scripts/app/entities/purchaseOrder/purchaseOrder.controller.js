'use strict';

angular.module('portalApp').controller('PurchaseOrderController',
    ['$rootScope', '$scope', '$stateParams', '$q', 'entity', 'entityLines', 'PurchaseOrder', 'PoLine', 'Party', 'User',
        function ($rootScope, $scope, $stateParams, $q, entity, entityLines, PurchaseOrder, PoLine, Party, User) {
            // Initial step
            $scope.step = 2;
            $scope.purchaseOrder = entity;
            $scope.purchaseOrderLines = entityLines === undefined ? [] : entityLines;
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
                $uibModalInstance.close(result);
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
