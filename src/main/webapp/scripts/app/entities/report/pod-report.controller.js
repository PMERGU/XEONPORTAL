'use strict';

angular.module('portalApp')
	.controller('PODReportController', function($scope, $log, $state, $stateParams, $http, Company, PODReport, ParseLinks, Principal) {

		$scope.podData = [];
		$scope.predicate = 'id';
		$scope.reverse = true;
		$scope.page = 1;
		$scope.warehouse = null;
		$scope.showdownloadbuttons = false;
		$scope.fromDate = null;
		$scope.toDate = null;
		$scope.podStatus = null;
		function resetSR() {
			$log.debug("clearing po");
			$scope.podReport = {
				fromDate : null,
				toDate : null,
				podType : null,
				orType : null,
				id : null
			};
			$scope.podData = [];
			$scope.showdownloadbuttons = false;
		}

		// Initial step
		$scope.step = 1;
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

		$scope.$watch(function() {
			return $scope.podReport.podType
		}, function(podType) {
			$log.debug("test");
			if (podType != null && podType != undefined) {
				$log.debug("POD Status ");
				$log.debug(podType);
				$scope.podData = [];
				$scope.showdownloadbuttons = false;
			//                $scope.loadAll(company);
			}
		});

		Principal.identity().then(function(user) {

			$scope.user = user;
			$scope.authorities = user.authorities;
			$scope.isXeon = (user.company.type === "XEON");

			if ($scope.user.authorities.indexOf("ROLE_CUSTOMER") != -1) {
				$scope.companies = [ $scope.user.company ];
				$scope.company = $scope.user.company;
				$scope.selected = {
					company : $scope.user.company
				};
			} else {
				$scope.companies = Company.query();
				$scope.company = null;
				$scope.selected = {
					company : null
				};
			}

			//WATCH when company is selected (for capture as company employee feature)
			$scope.$watch(function() {
				return $scope.selected.company
			}, function(company) {
				$log.debug("test");
				if (company != null && company != undefined) {
					$log.debug("Comapnay Name ");
					$log.debug(company);
					$scope.podData = [];
					$scope.showdownloadbuttons = false;
				//                    $scope.loadAll(company);
				}
			});

			$scope.$watch(function() {
				return $scope.warehouse
			}, function(warehouse) {
				$log.debug("test");
				if (warehouse != null && warehouse != undefined && warehouse != '') {
					$log.debug("Comapnay Name ");
					$log.debug(company);
				//                    $scope.loadAll(company);
				}
			});
		});



		var onSaveSuccess = function(result) {
			$scope.podData = result;
			if (result != null && result != undefined && result.length > 0) {
				$scope.showdownloadbuttons = true;
			} else {
				$scope.showdownloadbuttons = false;
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

		$scope.loadAll = function() {



			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {
				$scope.podReport.sapId = $scope.selected.company.sapId;
				$scope.podReport.orType = $scope.podReport.orType == null ? "" : $scope.podReport.orType ;
				//				$scope.stockReport.
				$log.debug("test Status: " + $scope.podStatus);
				PODReport.save($scope.podReport, onSaveSuccess, onSaveError);

			}
		};


		$scope.downloadPdf = function() {



			if ($stateParams.queryType === undefined || $stateParams.queryType === null || $stateParams.queryType === "ALL") {

				$log.debug($scope.selected.company.id);
				$scope.podReport.sapId = $scope.selected.company.sapId;
				$http.post('api/podReport/download/', $scope.podReport, {
					responseType : 'arraybuffer'
				}).then(function(response) {
					var fileName = "pod_report.pdf";
					var a = document.createElement("a");
					document.body.appendChild(a);
					a.style = "display: none";
					var file = new Blob([ response.data ], {
						type : 'application/pdf'
					});
					var fileURL = window.URL.createObjectURL(file);
					a.href = fileURL;
					a.download = fileName;
					a.click();

				});

			}
		};


		resetSR();

	});