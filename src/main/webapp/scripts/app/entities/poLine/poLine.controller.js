'use strict';

angular.module('portalApp')
    .controller('PoLineController', function ($scope, $state, PoLine, ParseLinks) {

        $scope.poLines = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            PoLine.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.poLines = result;
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
            $scope.poLine = {
                materialNumber: null,
                orderQuantity: null,
                unitOfMeasure: null,
                warehouse: null,
                length: null,
                width: null,
                height: null,
                grossWeight: null,
                netWeight: null,
                batchNumber: null,
                id: null
            };
        };
    });
