'use strict';

angular.module('portalApp')
	.controller('PartyDeleteController', function($scope, $uibModalInstance, entity, Party) {

        $scope.party = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Party.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
