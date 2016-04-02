'use strict';

angular.module('portalApp')
	.controller('PurchaseOrderProcessController', function($scope, $uibModalInstance, entity, PurchaseOrder) {

        $scope.purchaseOrder = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmProcess = function (id) {
            $scope.purchaseOrder.state = 'PROCESSED';
            PurchaseOrder.updateState({id: $scope.purchaseOrder.id}, "\"" + $scope.purchaseOrder.state + "\"",
                function () {
                    $uibModalInstance.close(true);
                }
            );
        };

    });
