'use strict';

angular.module('portalApp')
	.controller('StockReportController', function($scope, $log, $state, $stateParams, StockReport, ParseLinks) {

		$scope.stockData = [];
		$scope.predicate = 'id';
		$scope.reverse = true;
		$scope.page = 1;
		$scope.loadAll = function() {
			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				StockReport.query({
					page : $scope.page - 1,
					size : 20,
					sort : [ $scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id' ]
				}, function(result, headers) {
					$scope.links = ParseLinks.parse(headers('link'));
					$scope.totalItems = headers('X-Total-Count');
					$scope.purchaseOrders = result;
				});
			} else {
				PurchaseOrder.queryByState({
					state : $stateParams.queryType,
					page : $scope.page - 1,
					size : 20,
					sort : [ $scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id' ]
				}, function(result, headers) {
					$scope.links = ParseLinks.parse(headers('link'));
					$scope.totalItems = headers('X-Total-Count');
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