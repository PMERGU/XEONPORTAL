'use strict';

angular.module('portalApp')
	.controller('PurchaseOrderCommentController', function($scope, $uibModalInstance, entity, PurchaseOrder, AlertService) {

        $scope.purchaseOrder = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmComment = function (id) {
            if ($scope.purchaseOrder.comment !== undefined || $scope.purchaseOrder.comment !== null || $scope.purchaseOrder.comment.length >= 5) {
                PurchaseOrder.updateComment({id: $scope.purchaseOrder.id}, $scope.purchaseOrder.comment,
                    function () {
                        $uibModalInstance.close(true);
                    }
                );

            }
        };

    });
