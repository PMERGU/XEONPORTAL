'use strict';

angular.module('portalApp')
    .controller('LeftmenuController', function ($scope, $location, $state, Auth, Principal, ENV) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';

        Principal.identity().then(function(account) {
            $scope.identity = account;
        });
    });
