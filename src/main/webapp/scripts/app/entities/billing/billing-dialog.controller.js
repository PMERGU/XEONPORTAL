'use strict';

angular.module('portalApp').controller('BillingDialogController',
	[ '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Billing', 'Company', 'Principal', 'Area', '$log', 'StaticServices',
		function($scope, $stateParams, $uibModalInstance, entity, Billing, Company, Principal, Area, $log, StaticServices) {

			$scope.billing = entity;
			$scope.companies = Company.query();
			$scope.staticEnums = StaticServices.getAll();

			$scope.isXeon = false;
			Principal.identity().then(function(user) {
				$scope.user = user;
				$scope.isXeon = (user.company.type === "XEON");
			});

			$scope.load = function(id) {
				Billing.get({
					id : id
				}, function(result) {
					$scope.billing = result;
				});
			};

			var onSaveSuccess = function(result) {
				// $scope.$emit('portalApp:billingUpdate', result);
				$scope.billing = result;
				$scope.isSaving = true;
				$scope.isSaving = true;
				console.log('calculated volume based bill : ' + $scope.billing.calculatedBill);
				if ($scope.billing.calculatedBill == 0)
					$scope.zeroBill = true;
				else
					$scope.zeroBill = false;
				$uibModalInstance.update(result);
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.save = function() {
				$scope.isSaving = true;
				{
					Billing.save($scope.billing, onSaveSuccess, onSaveError);
				}
			};

			$scope.clear = function() {
				$uibModalInstance.dismiss('cancel');
			};
		} ]);