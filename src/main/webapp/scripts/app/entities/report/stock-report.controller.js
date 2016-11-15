'use strict';

angular.module('portalApp')
	.controller('StockReportController', function($scope, $log, $state, $stateParams, StockReport, ParseLinks,Principal) {

		$scope.purchaseOrders = [];
		$scope.predicate = 'id';
		$scope.reverse = true;
		$scope.page = 1;
		
		Principal.identity().then(function(user) {
            $scope.user = user;
            $scope.isXeon = (user.company.type === "XEON");
        });

		$scope.loadAll = function() {
			
			 
			
			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				StockReport.getByUser({ id : $scope.user.login,
					page : $scope.page - 1,
					size : 20
					//sort : [ $scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id' ]
				}, function(result, headers) {
					$scope.links = ParseLinks.parse(headers('link'));
					$scope.totalItems =  20;
					$scope.purchaseOrders = result;
				});
			}  
		};
		$scope.loadPage = function(page) {
			$scope.page = page;
			$scope.loadAll();
		};
		$scope.loadAll();

		$scope.refresh = function() {
			$scope.poFilter = null;
			$scope.loadAll();
			$scope.clear();
		};
	});