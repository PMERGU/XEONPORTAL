'use strict';

angular.module('portalApp').controller('StockReportController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'StockReport', 'Company', 'Principal',
        function($scope, $stateParams, $uibModalInstance, entity, StockReport, Company, Principal) {

        $scope.report = entity;
        $scope.companies = Company.query();

        $scope.isXeon = false;
        Principal.identity().then(function(user) {
            $scope.user = user;
            $scope.isXeon = (user.company.type === "XEON");
        });

        var onStockReportSuccess = function (result) {
            $scope.stockData=result;
            $scope.isFetching = true;
        };

        var onStockReportError = function (result) {
            $scope.isFetching = false;
        };

        $scope.fetch = function () {
            $scope.isFetching = true;
              {
            	  StockReport.get($scope.companyName, $scope.mName, $scope.plantNo, $scope.sType);
              }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
