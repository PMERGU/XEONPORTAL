'use strict';

angular.module('portalApp')
    .controller('BillingController', function ($scope, $state, Billing, ParseLinks) {

        $scope.billings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        
        $scope.loadPage = function(page) {
            $scope.page = page;
        };
         

        $scope.refresh = function () {
            
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
