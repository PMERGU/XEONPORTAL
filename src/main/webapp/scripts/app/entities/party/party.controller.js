'use strict';

angular.module('portalApp')
    .controller('PartyController', function ($scope, $state, Party) {

        $scope.partys = [];
        $scope.loadAll = function() {
            Party.query(function(result) {
               $scope.partys = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.party = {
                name: null,
                houseNumber: null,
                streetName: null,
                district: null,
                postalCode: null,
                city: null,
                country: null,
                reference: null,
                id: null
            };
        };
    });
