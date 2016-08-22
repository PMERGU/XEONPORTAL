'use strict';

angular.module('portalApp')
    .controller('PartyController', function ($scope, $state, Party, ParseLinks) {

        $scope.partys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            Party.query({page: $scope.page - 1, size: 1000, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.partys = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
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
