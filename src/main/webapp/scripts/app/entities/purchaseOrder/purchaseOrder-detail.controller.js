'use strict';

angular.module('portalApp')
    .controller('PurchaseOrderDetailController', function ($scope, $rootScope, $stateParams, entity, PurchaseOrder, PoLine, Party, Employee) {
        $scope.purchaseOrder = entity;
        $scope.load = function (id) {
            PurchaseOrder.get({id: id}, function(result) {
                $scope.purchaseOrder = result;
            });
        };
        var unsubscribe = $rootScope.$on('portalApp:purchaseOrderUpdate', function(event, result) {
            $scope.purchaseOrder = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
