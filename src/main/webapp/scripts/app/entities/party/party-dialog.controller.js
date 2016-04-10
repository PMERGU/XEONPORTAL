'use strict';

angular.module('portalApp').controller('PartyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Party', 'Company',
        function($scope, $stateParams, $uibModalInstance, entity, Party, Company) {

        $scope.party = entity;
        $scope.companies = Company.query();
            
        $scope.load = function(id) {
            Party.get({id : id}, function(result) {
                $scope.party = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('portalApp:partyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.party.id != null) {
                Party.update($scope.party, onSaveSuccess, onSaveError);
            } else {
                Party.save($scope.party, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
