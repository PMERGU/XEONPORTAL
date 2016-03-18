'use strict';

angular.module('portalApp')
	.controller('PoLineDeleteController', function($scope, $uibModalInstance, entity, PoLine) {

        $scope.poLine = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            PoLine.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
