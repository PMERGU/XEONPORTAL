'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, $cacheFactory, $log, Principal, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, PurchaseOrder, CachedOrders,SOService) {
        $scope.deliveredOrders = [];
        $scope.emptyStatusOrders = [];
		$scope.undeliveredOrders = [];
		$scope.ordersStep = 0;
		$scope.todaysDate = new Date();
		$scope.loadingOrders = false;
		$scope.showTable = false;
		$scope.showOrderType=false;
		$scope.soData = {
				fromDate : null,
				toDate : null,
				orType : null,
				id : null
			};
		$scope.dateformat = 'yyyy-MM-dd';
		$scope.requiredFields = {};
		$scope.dateOptions = {
				// dateDisabled: disabled,
				formatYear : 'yy',
				// maxDate: new Date(new
				// Date(todaysDate).setMonth(todaysDate.getMonth()+2)),
				// minDate: new Date(),
				startingDay : 0
			};

			// Disable weekend selection
			function disabled(data) {
				var date = data.date,
					mode = data.mode;
				return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
			}
			;

			$scope.dateSetup = {
				dpFromDate : {
					opened : false,
					open : function($event) {

						$scope.dateSetup.dpFromDate.opened = true;
					}
				},

				dpToDate : {
					opened : false,
					open : function($event) {
						$scope.dateSetup.dpToDate.opened = true;
					}
				}
			};
		 


        $scope.dominant = '#fff';
        $scope.colors = {
            dominant: '#fff',
            palette: '#fff'
        }

        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withBootstrap()
            .withPaginationType('full_numbers')
            .withOption('rowCallback', rowCallback)
            .withDisplayLength(10);
        $scope.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1),
            DTColumnDefBuilder.newColumnDef(2),
            DTColumnDefBuilder.newColumnDef(3),
            DTColumnDefBuilder.newColumnDef(4)
        ];

        $scope.ordersWizard = {
            next: function () {
                if($scope.ordersStep > 0){
                    $log.debug("$scope.ordersStep : " + $scope.ordersStep);
                    $scope.ordersStep--;
                    getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
                }else{
                    $log.debug("Already at step 0");
                }
            },
            prev: function () {
                $log.debug("$scope.ordersStep : " + $scope.ordersStep);
                $scope.ordersStep++;
                getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
            },
            reset: function(){
                $scope.ordersStep=0;
                getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
            }
        };

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            if (account.company.id !== null){
                $scope.company = Company.get({id: account.company.id});
                Company.getPurchaseOrders({id: account.company.id}).$promise.then(function(data){
                    $scope.purchaseOrders = {};
                    data.forEach(function(po, idx){
                        $scope.purchaseOrders[po.poNumber] = po;
                    });
                });
            }
//            getOrders(new Date());
            getCapturedPOs();
        });

        $scope.reloadData = function () {
            var resetPaging = true;
            $scope.showTable=true;
            getOrders(new Date(), true);
        };
        
        $scope.orderTypes = {
        		
        		'ZOUT' : 'OUTBOUND',
				'ZIN1':'INBOUND',
				'YCO':'Transport',
				'YRET': 'INBOUND',             //'Returns' replaced with INBOUND bcz for both INBOUND and retuns coditions are same in html page
				'ZFF' : 'Freight Forwarding & Clearing',
				'ZWSE': 'Storage'
        		
        }

        function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            // Unbind first in order to avoid any duplicate handler (see https://github.com/l-lin/angular-datatables/issues/87)
            $('td', nRow).unbind('click');
            $('td', nRow).bind('click', function() {
                $scope.$apply(function() {
                    $log.debug(aData);
                });
            });
            return nRow;
        };

        function getOrders(dateT, force){
            $scope.loadingOrders = true;
            $scope.deliveredOrders = [];
			$scope.emptyStatusOrders = [];
			$scope.undeliveredOrders = [];
			$scope.showOrderType=false;
            if($scope.account.company.id !== null) {
               /* CachedOrders.getOrders($scope.ordersStep,
                    $scope.account.company.sapId,
                    new Date(new Date(dateT).setMonth(dateT.getMonth() - 1)),
                    // new Date(new Date(dateT).setDate(dateT.getDate() - 1)),
                    new Date(new Date(dateT).setDate(dateT.getDate()+1)),
                    force
                )*/
            	
            	if($scope.soData.orType == null || $scope.soData.orType == '')
				{
            		$scope.showOrderType=true;
				}
                SOService.getByCustomerNumber({type :   $scope.soData.orType == null ? "" : $scope.soData.orType  , customerNumber: $scope.account.company.sapId,
                	from : $scope.soData.fromDate,
					to : $scope.soData.toDate 
				
				}).$promise.then(function(data) {
					
					$log.debug("Total length :: " + data.length);
					$scope.orderDate=data.filter(function(el) {
						$log.debug($scope.orderTypes[el._auart] + " el._auart :: " + el._auart);
						return ($scope.orderTypes[el._auart] != null);
					});
					data =$scope.orderDate;
					$log.debug("Total first filter length :: " + data.length);
					
					$scope.deliveredOrders = data.filter(function(el) {
						$log.debug("el._pdstk :: " + el._pdstk);
						return (el._pdstk === "B" || el._pdstk === "C");
					});
					$scope.undeliveredOrders = data.filter(function(el) {
						return (el._pdstk === "A");
					});
					$scope.emptyStatusOrders = data.filter(function(el) {
						return (el._pdstk === "");
					});
					$scope.loadingOrders = false;
					$log.debug("delivered length :: " + $scope.deliveredOrders.length);
					$log.debug("emptyStatusOrders length :: " + $scope.emptyStatusOrders.length);
					$log.debug("undelivered length :: " + $scope.undeliveredOrders.length);
				});
            }
        }

        function getCapturedPOs(){
            $scope.pos = {};
            PurchaseOrder.queryByState({state: "PROCESSED"}).$promise.then(function(data) {
                $scope.pos.processed = data;
            });
            PurchaseOrder.queryByState({state: "PROCESSING"}).$promise.then(function(data) {
                $scope.pos.processing = data;
            });
            PurchaseOrder.queryByState({state: "UNPROCESSED"}).$promise.then(function(data) {
                $scope.pos.unprocessed = data;
            });
        }

    });
