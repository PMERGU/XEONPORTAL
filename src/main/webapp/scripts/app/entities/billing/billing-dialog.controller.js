'use strict';

angular.module('portalApp').controller('BillingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Billing', 'Company', 'Principal', 'Area', '$log', 'StaticServices',
        function($scope, $stateParams, $uibModalInstance, entity, Billing, Company, Principal, Area, $log, StaticServices) {

        $scope.billing = entity;
        $scope.companies = Company.query();
        $scope.staticEnums = StaticServices.getAll();

        $scope.isXeon = false;
        Principal.identity().then(function(user) {
            $scope.user = user;
            $scope.isXeon = (user.company.type === "XEON");
        });

        $scope.load = function(id) {
        	Billing.get({id: id}, function(result) {
                $scope.billing = result;
            });
        };



        var onSaveSuccess = function (result) {
           // $scope.$emit('portalApp:billingUpdate', result);
            $scope.billing=result;
            $scope.isSaving = true;
            $uibModalInstance.update(result);
            $scope.isSaving = true;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
              {
            	Billing.save($scope.billing, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
