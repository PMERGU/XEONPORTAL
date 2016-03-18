'use strict';

angular.module('portalApp').controller('PurchaseOrderDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'PurchaseOrder', 'PoLine', 'Party', 'Employee',
        function($scope, $stateParams, $uibModalInstance, $q, entity, PurchaseOrder, PoLine, Party, Employee) {

        $scope.purchaseOrder = entity;
        $scope.polines = PoLine.query();
        $scope.shiptopartys = Party.query({filter: 'purchaseorder-is-null'});
        $q.all([$scope.purchaseOrder.$promise, $scope.shiptopartys.$promise]).then(function() {
            if (!$scope.purchaseOrder.shipToPartyId) {
                return $q.reject();
            }
            return Party.get({id : $scope.purchaseOrder.shipToPartyId}).$promise;
        }).then(function(shipToParty) {
            $scope.shiptopartys.push(shipToParty);
        });
        $scope.pickuppartys = Party.query({filter: 'purchaseorder-is-null'});
        $q.all([$scope.purchaseOrder.$promise, $scope.pickuppartys.$promise]).then(function() {
            if (!$scope.purchaseOrder.pickUpPartyId) {
                return $q.reject();
            }
            return Party.get({id : $scope.purchaseOrder.pickUpPartyId}).$promise;
        }).then(function(pickUpParty) {
            $scope.pickuppartys.push(pickUpParty);
        });
        $scope.employees = Employee.query();
        $scope.load = function(id) {
            PurchaseOrder.get({id : id}, function(result) {
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

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCaptureDate = {};

        $scope.datePickerForCaptureDate.status = {
            opened: false
        };

        $scope.datePickerForCaptureDateOpen = function($event) {
            $scope.datePickerForCaptureDate.status.opened = true;
        };
        $scope.datePickerForDeliveryDate = {};

        $scope.datePickerForDeliveryDate.status = {
            opened: false
        };

        $scope.datePickerForDeliveryDateOpen = function($event) {
            $scope.datePickerForDeliveryDate.status.opened = true;
        };
}]);
