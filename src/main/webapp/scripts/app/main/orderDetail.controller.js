'use strict';

angular.module('portalApp')
    .controller('MainOrderDetailController', function ($scope, $stateParams, Principal) {
        console.log($stateParams.order);
        $scope.order = $stateParams.order;
    });
