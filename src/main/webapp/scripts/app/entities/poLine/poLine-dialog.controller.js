// 'use strict';
//
// angular.module('portalApp').controller('PoLineDialogController',
//     ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'PoLine', 'PurchaseOrder',
//         function($scope, $stateParams, $uibModalInstance, entity, PoLine, PurchaseOrder) {
//
//         $scope.poLine = entity;
//         $scope.purchaseorders = PurchaseOrder.query();
//         $scope.load = function(id) {
//             PoLine.get({id : id}, function(result) {
//                 $scope.poLine = result;
//             });
//         };
//
//         var onSaveSuccess = function (result) {
//             $scope.$emit('portalApp:poLineUpdate', result);
//             $uibModalInstance.close(result);
//             $scope.isSaving = false;
//         };
//
//         var onSaveError = function (result) {
//             $scope.isSaving = false;
//         };
//
//         $scope.save = function () {
//             $scope.isSaving = true;
//             if ($scope.poLine.id != null) {
//                 PoLine.update($scope.poLine, onSaveSuccess, onSaveError);
//             } else {
//                 PoLine.save($scope.poLine, onSaveSuccess, onSaveError);
//             }
//         };
//
//         $scope.clear = function() {
//             $uibModalInstance.dismiss('cancel');
//         };
// }]);
