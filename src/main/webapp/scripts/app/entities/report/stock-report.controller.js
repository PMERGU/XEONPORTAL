'use strict';

angular.module('portalApp')
	.controller('StockReportController', function($scope, $log, $state, $stateParams,$http, StockReport, ParseLinks,Principal,FileSaver) {

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
				StockReport.getByUser({ id : 'derick',
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
		
		
		$scope.downloadPdf = function() {
			
			 
			
			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				$http.get('api/stockReport/download/derick', { responseType: 'arraybuffer' }).then(function (response) {
					var fileName = "stock_report.pdf";
		            var a = document.createElement("a");
		            
		            document.body.appendChild(a);
		            a.style = "display: none";
		            alert(response.data);
		            var file = new Blob([response.data], {type: 'application/pdf'});
	                var fileURL = window.URL.createObjectURL(file);
	                a.href = fileURL;
	                a.download = fileName;
	                a.click();
					 
				});
				
//				$http.get('api/stockReport/download/dercik',$scope.data,{responseType:'arraybuffer'})
				
//				StockReport.getByUserDownload({
//					id : 'derick'
//				}).$promise.then(function(imageBlob) {
//					$log.debug(imageBlob.response.type);
//					FileSaver.saveAs(imageBlob.response, 'derick' + "-" + 'derick');
//					$scope.isDownloadingAttachment = false;
//				}, function(err) {
//					$scope.isDownloadingAttachment = false;
//					$log.error("Count not download attachment");
//					$log.error(err);
//				});
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