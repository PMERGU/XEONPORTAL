'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, Principal, company) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.company = company;
        });
    });
