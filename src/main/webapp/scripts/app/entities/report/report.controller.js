'use strict';

angular.module('portalApp')
    .controller('ReportController', function ($scope, $state, StockReport, ParseLinks) {

        $scope.stockData = [];
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
            $scope.report = {
                    company: null,
                    mName: null,
                    plantNo: null,
                    sType: null
            };
        };
    });
