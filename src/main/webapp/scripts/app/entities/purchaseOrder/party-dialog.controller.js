'use strict';

angular.module('portalApp').controller('POPartyDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Party', 'Area', '$log',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, Party, Area, $log) {

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

        $scope.searchArea = function(search){
            $log.debug("filtering ");
            if(Number(search)){
                $log.debug("number : " + search);
                search = 'postalCode:' + search;
            }else{
                $log.debug("alpha : " + search);
                search = 'suburb:' + search;
            }

            Area.query({search}).$promise.then(function(data){
                $scope.filteredAreas = data;
            });
        };

        $scope.save = function () {
            $scope.isSaving = true;
            Party.save($scope.party, onSaveSuccess, onSaveError);
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
