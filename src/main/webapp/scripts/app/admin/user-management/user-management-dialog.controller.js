'use strict';

angular.module('portalApp').controller('UserManagementDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'User', 'Company', '$log',
        function($scope, $stateParams, $uibModalInstance, entity, User, Company, $log) {

        $scope.user = entity;
        $scope.authorities = ["ROLE_USER", "ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_CUSTOMER_CSU"];
        $scope.companies = Company.query();
        Company.getUsers({'id': 1}).$promise.then(function(data){
            $log.debug(data);
            var tmp = null;
            var tmpArray = [tmp];
            $scope.csuList = tmpArray.concat(data);
        });

        var onSaveSuccess = function (result) {
            $scope.isSaving = false;
            $uibModalInstance.close(result);
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            console.log($scope.user);
            if($scope.user.authorities[0] === "ROLE_CUSTOMER_CSU"){
                $scope.user.authorities.push("ROLE_CUSTOMER");
            }
            if ($scope.user.id != null) {
                User.update($scope.user, onSaveSuccess, onSaveError);
            } else {
                $scope.user.langKey = 'en';
                User.save($scope.user, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
