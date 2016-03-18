'use strict';

angular.module('portalApp').controller('EmployeeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Employee', 'PurchaseOrder', 'Company',
        function($scope, $stateParams, $uibModalInstance, entity, Employee, PurchaseOrder, Company) {

        $scope.employee = entity;
        $scope.purchaseorders = PurchaseOrder.query();
        $scope.companys = Company.query();
        $scope.load = function(id) {
            Employee.get({id : id}, function(result) {
                $scope.employee = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('portalApp:employeeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.employee.id != null) {
                Employee.update($scope.employee, onSaveSuccess, onSaveError);
            } else {
                Employee.save($scope.employee, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
