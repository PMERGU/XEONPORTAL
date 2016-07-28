'use strict';

angular.module('portalApp').controller('PartyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Party', 'Company', 'Principal', 'Area', '$log',
        function($scope, $stateParams, $uibModalInstance, entity, Party, Company, Principal, Area, $log) {

        $scope.party = entity;
        $scope.companies = Company.query();

        $scope.isXeon = false;
        Principal.identity().then(function(user) {
            $scope.user = user;
            $scope.isXeon = (user.company.type === "XEON");
            $scope.party.$promise.then(function(party){
                if($scope.isXeon && party.sapId === "100000"){
                    $scope.party.sapId = null;
                }
            });
        });

        $scope.load = function(id) {
            Party.get({id: id}, function(result) {
                $scope.party = result;
            });
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

            Area.query({search: search}).$promise.then(function(data){
                $scope.filteredAreas = data;
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
