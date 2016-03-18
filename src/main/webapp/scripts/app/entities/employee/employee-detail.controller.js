'use strict';

angular.module('portalApp')
    .controller('EmployeeDetailController', function ($scope, $rootScope, $stateParams, entity, Employee, PurchaseOrder, Company) {
        $scope.employee = entity;
        $scope.load = function (id) {
            Employee.get({id: id}, function(result) {
                $scope.employee = result;
            });
        };
        var unsubscribe = $rootScope.$on('portalApp:employeeUpdate', function(event, result) {
            $scope.employee = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
