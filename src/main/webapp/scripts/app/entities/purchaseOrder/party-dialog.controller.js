'use strict';

angular.module('portalApp').controller('POPartyDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Party',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, Party) {

        $scope.party = entity;

        var onSaveSuccess = function (result) {
            result.for = $stateParams.for;
            $rootScope.$emit('portalApp:partyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            Party.save($scope.party, onSaveSuccess, onSaveError);
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
