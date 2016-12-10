'use strict';

angular.module('portalApp')
	.controller('SalesOrdersController', function($scope, $stateParams, $sce, $window, $log, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, CachedOrders, PurchaseOrder, sweet, identity) {
		$log.debug("ID : " + $stateParams.company);
		$scope.deliveredOrders = [];
		$scope.undeliveredOrders = [];
		$scope.ordersStep = 0;
		$scope.todaysDate = new Date();
		$scope.loadingOrders = false;

		$scope.identity = identity;
		if ($scope.identity.authorities.indexOf("ROLE_CUSTOMER") != -1) {
			$scope.companies = [ $scope.identity.company ];
			$scope.company = $scope.identity.company;
			$scope.selected = {
				company : $scope.identity.company
			};
		} else {
			$scope.company = null;
			$scope.selected = {
				company : null
			};

			if ($stateParams.company != null && $stateParams.company != undefined) {
				Company.query().$promise.then(function(companies) {
					$.each(companies, function(idx, company) {
						$log.debug(idx + " - " + company.id);
						if ($stateParams.company === company.id.toString()) {
							$scope.selected.company = company;
							$scope.company = company;
						}
					});
					$scope.companies = companies;
					$scope.reloadData(false);
					$log.debug($scope.companies + " : Comapnies : " + $scope.company);
				});
			} else {
				$scope.companies = Company.query();
			}
		}

		//WATCH when company is selected (for capture as company employee feature)
		$scope.$watch(function() {
			return $scope.selected.company
		}, function(company, oldComapny) {
			$log.debug("test : " + oldComapny);
			if (company != null && company != undefined && ((oldComapny == null || oldComapny == undefined || oldComapny.id != company.id))) {
				$log.debug(company);
				var a = document.getElementById("salesOR");
				a.click();

			}
		});

		$scope.dtOptions = DTOptionsBuilder.newOptions()
			.withBootstrap()
			.withPaginationType('full_numbers')
			.withOption('rowCallback', rowCallback)
			.withDisplayLength(25);
		$scope.dtColumnDefs = [
			DTColumnDefBuilder.newColumnDef(0),
			DTColumnDefBuilder.newColumnDef(1),
			DTColumnDefBuilder.newColumnDef(2),
			DTColumnDefBuilder.newColumnDef(3),
			DTColumnDefBuilder.newColumnDef(4)
		];

		$scope.ordersWizard = {
			next : function() {
				if ($scope.ordersStep > 0) {
					$log.debug("$scope.ordersStep : " + $scope.ordersStep);
					$scope.ordersStep--;
					getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
				} else {
					$log.debug("Already at step 0");
				}
			},
			prev : function() {
				$log.debug("$scope.ordersStep : " + $scope.ordersStep);
				$scope.ordersStep++;
				getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
			},
			reset : function() {
				$scope.ordersStep = 0;
				getOrders(new Date(new Date($scope.todaysDate).setMonth($scope.todaysDate.getMonth() - $scope.ordersStep)))
			}
		};

		function getOrders(dateT, force) {
			$scope.loadingOrders = true;
			if ($scope.selected.company.id !== null) {
				CachedOrders.getOrders($scope.ordersStep,
					$scope.selected.company.sapId,
					new Date(new Date(dateT).setMonth(dateT.getMonth() - 1)),
					// new Date(new Date(dateT).setDate(dateT.getDate() - 1)),
					new Date(new Date(dateT).setDate(dateT.getDate() + 1)),
					force
				).then(function(data) {
					$scope.deliveredOrders = data.filter(function(el) {
						$log.debug("el._pdstk :: " + el._pdstk);
						return (el._pdstk === "B" || el._pdstk === "C");
					});
					$scope.undeliveredOrders = data.filter(function(el) {
						return (el._pdstk === "A" || el._pdstk === "");
					});
					$scope.loadingOrders = false;
					$log.debug("delivered length :: " + $scope.deliveredOrders.length);
					$log.debug("undelivered length :: " + $scope.undeliveredOrders.length);
				});
				
			}
		}
		;

		$scope.reloadData = function(refresh) {
			var resetPaging = true;
			getOrders(new Date(), refresh);
		//			if ($scope.selected.company.id !== null) {
		//				Company.getPurchaseOrders({
		//					id : $scope.selected.company.id
		//				}).$promise.then(function(data) {
		//					$scope.purchaseOrders = {};
		//					data.forEach(function(po, idx) {
		//						$scope.purchaseOrders[po.poNumber] = po;
		//					});
		//				});
		//			} else {
		//				sweet.show({
		//					title : 'Something went wrong...notify derick',
		//					text : 'Company.id is null and we cant figure out why :'
		//						+ '<div class="m">'
		//						+ '    ' + $scope.selected
		//						+ '</div>',
		//					html : true
		//				});
		//			}
		};

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
		
		$scope.viewRow=function (index) {

		    //var empname = $scope.undeliveredOrders[index];  
		    alert(index);

		}

	});