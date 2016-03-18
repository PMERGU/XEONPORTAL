'use strict';

angular.module('portalApp')
    .controller('PoLineDetailController', function ($scope, $rootScope, $stateParams, entity, PoLine, PurchaseOrder) {
        $scope.poLine = entity;
        $scope.load = function (id) {
            PoLine.get({id: id}, function(result) {
                $scope.poLine = result;
            });
        };
        var unsubscribe = $rootScope.$on('portalApp:poLineUpdate', function(event, result) {
            $scope.poLine = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
