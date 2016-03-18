'use strict';

angular.module('portalApp')
    .controller('CompanyDetailController', function ($scope, $rootScope, $stateParams, DataUtils, entity, Company, Employee) {
        $scope.company = entity;
        $scope.load = function (id) {
            Company.get({id: id}, function(result) {
                $scope.company = result;
            });
        };
        var unsubscribe = $rootScope.$on('portalApp:companyUpdate', function(event, result) {
            $scope.company = result;
        });
        $scope.$on('$destroy', unsubscribe);

        $scope.byteSize = DataUtils.byteSize;
    });
