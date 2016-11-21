'use strict';

angular.module('portalApp')
	.controller('StockReportController', function($scope, $log, $state, $stateParams,$http,Company, StockReport, ParseLinks,Principal) {

		$scope.stockData = [];
		$scope.predicate = 'id';
		$scope.reverse = true;
		$scope.page = 1;
		$scope.warehouses = { 'M01' : 'JHB', 'M03' : 'DBN', 'M04' : 'PE', 'M05' : 'CPT'};
		$scope.warehouse = null;
		$scope.showdownloadbuttons=false;
		function resetSR() {
			$log.debug("clearing po");
			$scope.stockReport = {
					plantNo: null,
					sType: null,
					mName:null,
				company : null
				 
			};
		  $scope.stockData = [];
		  $scope.showdownloadbuttons=false;
		}
		
		Principal.identity().then(function(user) {
			 
            $scope.user = user;
            $scope.authorities =user.authorities;
            $scope.isXeon = (user.company.type === "XEON");
            
            if($scope.user.authorities.indexOf("ROLE_CUSTOMER") != -1){
                $scope.companies = [ $scope.user.company];
                $scope.company =  $scope.user.company;
                $scope.selected = {
                    company:  $scope.user.company
                };
            }else{
                $scope.companies = Company.query();
                $scope.company = null;
                $scope.selected = {
                    company: null
                };
            }



            //WATCH when company is selected (for capture as company employee feature)
            $scope.$watch(function() {
                return $scope.selected.company
            }, function (company) {
                $log.debug("test");
                if(company != null && company != undefined) {
                    $log.debug("Comapnay Name ");
                    $log.debug(company);
                    $scope.stockData = [];
                    $scope.showdownloadbuttons =false;
                    $scope.loadAll(company);
                }
            });
            
            $scope.$watch(function() {
                return $scope.warehouse
            }, function (warehouse) {
                $log.debug("test");
                if(warehouse != null && warehouse != undefined && warehouse != '') {
                    $log.debug("Comapnay Name ");
                    $log.debug(company);
                    $scope.loadAll(company);
                }
            });
        });
		
		
		 
		var onSaveSuccess = function(result) {
			$scope.stockData=result;
			if(result!=null && result!= undefined && result.length>0)
				{
					$scope.showdownloadbuttons =true;
				}else{
					$scope.showdownloadbuttons =false;
				}
			 
		};
		
		var onSaveError = function(result) {
			$log.error(result);
			var msg = "Failed to create order :\n\n";
			$.each(result.data.fieldErrors, function(idx, error) {
				msg += "Field " + error.field + " on " + error.objectName;
				switch (error.message) {
				case "NotNull":
					msg += " cant be null. Please enter a valid value";
					break;
				default:
					$log.log("have not seen this error before : " + error.message);
				}
				msg += "\n";
			});
		};

		$scope.loadAll = function(company) {
			
			 
			
			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				$scope.stockReport.company = company.name;
//				$scope.stockReport.
				StockReport.save($scope.stockReport, onSaveSuccess, onSaveError);
				 
			}  
		};
		
		
		$scope.downloadPdf = function() {
			
			 
			
			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				
				$log.debug($scope.selected.company.id);
				$scope.stockReport.company = $scope.selected.company.name;
				$http.post('api/stockReport/download/', $scope.stockReport, { responseType: 'arraybuffer' }).then(function (response) {
					var fileName = "stock_report.pdf";
		            var a = document.createElement("a");
		            document.body.appendChild(a);
		            a.style = "display: none";
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
		 
		 
		resetSR();
		 
	});