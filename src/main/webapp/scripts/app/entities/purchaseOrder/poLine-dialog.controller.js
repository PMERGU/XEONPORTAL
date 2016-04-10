'use strict';

angular.module('portalApp').controller('PoLineDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PoLine', 'PurchaseOrder', '$timeout',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, PoLine, PurchaseOrder, $timeout) {
        $scope.serviceType = $stateParams.serviceType;
        $scope.poLine = entity;
        $scope.requiredFields = {};

        $scope.load = function(id) {
            PoLine.get({id : id}, function(result) {
                $scope.poLine = result;
            });
        };

        $timeout(function() {
            switch ($scope.serviceType){
                case "COURIER":
                    hideOrShow([
                        {name: 'materialNumber'},
                        {name: 'batchNumber'},
                        {name: 'materialType', show: true, value: "PACKAGE"},
                        {name: 'length', show: true, value: ""},
                        {name: 'width', show: true, value: ""},
                        {name: 'height', show: true, value: ""},
                        {name: 'netWeight', show: true, value: ""},
                        {name: 'grossWeight', show: true, value: ""}
                    ]);
                    break;
                case "INBOUND":
                    hideOrShow([
                        {name: 'materialNumber', show: true, value: ""},
                        {name: 'materialType', show: true, value: "EACH"},
                        {name: 'batchNumber', show: true, value: ""},
                        {name: 'length', show: true, value: ""},
                        {name: 'width', show: true, value: ""},
                        {name: 'height', show: true, value: ""},
                        {name: 'netWeight', show: true, value: ""},
                        {name: 'grossWeight', show: true, value: ""}
                    ]);
                    break;
                case "OUTBOUND":
                    hideOrShow([
                        {name: 'materialNumber', show: true, value: ""},
                        {name: 'batchNumber', show: true, value: ""},
                        {name: 'materialType', show: true, value: "PALLET"},
                        {name: 'length'},
                        {name: 'width'},
                        {name: 'height'},
                        {name: 'netWeight'},
                        {name: 'grossWeight'}
                    ]);
                    break;
            }
        }, 0);

        function hideOrShow(elements){
            $.each(elements, function(idx, element) {
                var field = $('#' + 'field_' + element.name);
                if(field === undefined){
                    alert("unknown field : " + element.name);
                }else{
                    field.prop( "disabled", element.show === undefined ? true : !element.show);
                    $scope.requiredFields[element.name] = element.show === undefined ? false : element.show;
                    $scope.poLine[element.name] = element.value === undefined ? null : element.value;
                    if(element.show === undefined ? true : !element.show){
                        field.closest('.form-group').slideUp();
                    }else{
                        field.removeClass("animate-glower").addClass("animate-glower");
                        field.closest('.form-group').slideDown();
                    }
                }
            })
        }

        $scope.save = function () {
            $rootScope.$emit('portalApp:poLineUpdate', $scope.poLine);
            $uibModalInstance.close($scope.poLine);
            $scope.isSaving = false;
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

}]);
