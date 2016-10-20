'use strict';

angular.module('portalApp')
    .controller('BillingController', function ($scope, $state, Billing, ParseLinks) {

        $scope.billings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
        	Billing.query({page: $scope.page - 1, size: 1000, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.billings = result;
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
            $scope.billing = {
            		source: null,
                    sourceZone: null,
                    destination: null,
                    destinationZone: null,
                    weight: null,
                     
                    volume : null
            };
        };
    });
