'use strict';

angular.module('portalApp')
    .controller('PartyDetailController', function ($scope, $rootScope, $stateParams, entity, Party) {
        $scope.party = entity;
        $scope.load = function (id) {
            Party.get({id: id}, function(result) {
                $scope.party = result;
            });
        };
        var unsubscribe = $rootScope.$on('portalApp:partyUpdate', function(event, result) {
            $scope.party = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
