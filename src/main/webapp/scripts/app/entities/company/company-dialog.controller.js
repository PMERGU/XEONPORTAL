'use strict';

angular.module('portalApp').controller('CompanyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Company', 'User',
        function($scope, $stateParams, $uibModalInstance, DataUtils, entity, Company, User) {

        $scope.company = entity;
        $scope.users = User.query();
        $scope.load = function(id) {
            Company.get({id : id}, function(result) {
                $scope.company = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('portalApp:companyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.company.id != null) {
                Company.update($scope.company, onSaveSuccess, onSaveError);
            } else {
                Company.save($scope.company, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.abbreviate = DataUtils.abbreviate;

        $scope.byteSize = DataUtils.byteSize;

        $scope.setBackground = function ($file, company) {
            if ($file && $file.$error == 'pattern') {
                return;
            }
            if ($file) {
                var fileReader = new FileReader();
                fileReader.readAsDataURL($file);
                fileReader.onload = function (e) {
                    var base64Data = e.target.result.substr(e.target.result.indexOf('base64,') + 'base64,'.length);
                    $scope.$apply(function() {
                        company.background = base64Data;
                        company.backgroundContentType = $file.type;
                    });
                };
            }
        };
}]);
