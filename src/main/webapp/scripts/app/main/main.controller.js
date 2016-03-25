'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, Principal, Company) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.company = Company.get({id : account.company.id });
        });
    });
