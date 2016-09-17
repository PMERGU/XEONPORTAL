'use strict';

angular.module('portalApp').controller('PoLineDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PoLine', 'PurchaseOrder', '$timeout', '$log', 'StaticServices',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, PoLine, PurchaseOrder, $timeout, $log, StaticServices) {

        $scope.staticEnums = StaticServices.getAll();
        $scope.serviceType = $stateParams.serviceType;
        $scope.requiredFields = {};
        $scope.poLine = entity;

        $timeout(function() {
            switch ($scope.serviceType){
                case "INBOUND":
                    hideOrShow([
                        {name: 'materialNumber', show: true, value: IFTET($scope.poLine.materialNumber, "")},
                        {name: 'orderQuantity', show: true, value: IFTET($scope.poLine.orderQuantity, 1)},
                        {name: 'unitOfMeasure', show: true, value: IFTET($scope.poLine.unitOfMeasure, "PALLET")},
                        {name: 'batchNumber', show: true, value: IFTET($scope.poLine.batchNumber, "")},
                        {name: 'materialType'},
                        {name: 'length'},
                        {name: 'width'},
                        {name: 'height'},
                        {name: 'netWeight'},
                        {name: 'grossWeight'},
                        {name: 'dvType'},
                        {name: 'volume'}
                    ]);
                    break;
                case "OUTBOUND":
                    hideOrShow([
                        {name: 'materialNumber', show: true, value: IFTET($scope.poLine.materialNumber, "")},
                        {name: 'orderQuantity', show: true, value: IFTET($scope.poLine.orderQuantity, 1)},
                        {name: 'unitOfMeasure', show: true, value: IFTET($scope.poLine.materialType, "EACH")},
                        {name: 'materialType'},
                        {name: 'length'},
                        {name: 'width'},
                        {name: 'height'},
                        {name: 'netWeight'},
                        {name: 'grossWeight'},
                        {name: 'dvType'},
                        {name: 'volume'}
                    ]);
                    break;
                case "FULL_CONTAINER_LOAD":
                case "FULL_TRUCK_LOAD":
                case "CROSS_HAUL":
                    hideOrShow([
                        {name: 'materialNumber'},
                        {name: 'orderQuantity', show: false, value: 1},
                        {name: 'batchNumber'},
                        {name: 'materialType', show: false, value: "DEDICATED"},
                        {name: 'unitOfMeasure'},
                        {name: 'length'},
                        {name: 'width'},
                        {name: 'height'},
                        {name: 'netWeight'},
                        {name: 'dvType', show: false, value: IFTET($scope.poLine.dvType, 'VOLUME')},
                        {name: 'grossWeight', show: true, value: IFTET($scope.poLine.grossWeight, '')},
                        {name: 'volume', show: true, value: IFTET($scope.poLine.volume, "")}

                    ]);
                    break;
                default:
                    hideOrShow([
                        {name: 'materialNumber'},
                        {name: 'batchNumber'},
                        {name: 'unitOfMeasure'},
                        {name: 'orderQuantity', show: true, value: IFTET($scope.poLine.orderQuantity, 1)},
                        {name: 'materialType', show: true, value: IFTET($scope.poLine.materialType, "PACKAGE")},
                        {name: 'length', show: true, value: IFTET($scope.poLine.length, '')},
                        {name: 'width', show: true, value: IFTET($scope.poLine.width, '')},
                        {name: 'height', show: true, value: IFTET($scope.poLine.height, '')},
                        {name: 'netWeight', show: true, value: IFTET($scope.poLine.netWeight, '')},
                        {name: 'grossWeight', show: true, value: IFTET($scope.poLine.grossWeight, '')},
                        {name: 'dvType', show: true, value: IFTET($scope.poLine.dvType, "DIMENSIONS")},
                        {name: 'volume', show: true, value: IFTET($scope.poLine.volume, "")}
                    ]);
                    $scope.$watch('poLine.dvType', dvTypeWatch);
                    break;
            }

        }, 50);

        function dvTypeWatch(value){
            $log.debug("watch dvType triggered with value : " + value);
            switch (value){
                case 'VOLUME':
                    hideOrShow([
                        {name: 'volume', show: true, value: IFTET($scope.poLine.volume, "")},
                        {name: 'length'},{name: 'width'},{name: 'height'}
                    ]);
                    break;
                case 'DIMENSIONS':
                    hideOrShow([
                        {name: 'volume'},
                        {name: 'length', show: true, value: IFTET($scope.poLine.length, "")},
                        {name: 'width', show: true, value: IFTET($scope.poLine.width, "")},
                        {name: 'height', show: true, value: IFTET($scope.poLine.height, "")}
                    ]);
                    break;
            }
        };

        function hideOrShow(elements){
            $.each(elements, function(idx, element) {
                var field = $('#' + 'field_' + element.name);
                if(field === undefined){
                    $log.error("unknown field : " + element.name);
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

        $scope.outOfGage = false;
        $scope.outOfGageL = false;
        $scope.outOfGageH = false;
        $scope.outOfGageW = false;

        $scope.isGageLength = function() {
            $scope.outOfGage = false;
            $scope.outOfGageL = false;
            if (isNotBlank($scope.poLine) && isNotBlank($scope.poLine.length) && isNotBlank($scope.poLine.height) && isNotBlank($scope.poLine.width)) {
                var l = $scope.poLine.length;
                var h = $scope.poLine.height;
                var w = $scope.poLine.width;
                if (isNotBlank(l)) {
                    if (isNotBlank(h)) {
                        if((Number(l) > Number(h)) ? ((l / h) > 1.4) : ((l / h) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageL = true;
                        }
                    }
                    if (isNotBlank(w)) {
                        if((Number(l) > Number(w)) ? ((l / w) > 1.4) : ((l / w) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageL = true;
                        }
                    }
                }
            }
        };

        $scope.isGageWidth = function() {
            $scope.outOfGage = false;
            $scope.outOfGageW = false;
            if (isNotBlank($scope.poLine) && isNotBlank($scope.poLine.length) && isNotBlank($scope.poLine.height) && isNotBlank($scope.poLine.width)) {
                var l = $scope.poLine.length;
                var h = $scope.poLine.height;
                var w = $scope.poLine.width;
                if (isNotBlank(w)) {
                    if (isNotBlank(h)) {
                        if((Number(w) > Number(h)) ? ((w / h) > 1.4) : ((w / h) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageW = true;
                        }
                    }
                    if (isNotBlank(l)) {
                        if((Number(w) > Number(l)) ? ((w / l) > 1.4) : ((w / l) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageW = true;
                        }
                    }
                }
            }
        };

        $scope.isGageHeight = function() {
            $scope.outOfGage = false;
            $scope.outOfGageH = false;
            if (isNotBlank($scope.poLine) && isNotBlank($scope.poLine.length) && isNotBlank($scope.poLine.height) && isNotBlank($scope.poLine.width)) {
                var l = $scope.poLine.length;
                var h = $scope.poLine.height;
                var w = $scope.poLine.width;
                if (isNotBlank(h)) {
                    if (isNotBlank(l)) {
                        if((Number(h) > Number(l)) ? ((h / l) > 1.4) : ((h / l) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageH = true;
                        }
                    }
                    if (isNotBlank(w)) {
                        if((Number(h) > Number(w)) ? ((h / w) > 1.4) : ((h / w) < 0.6)){
                            $scope.outOfGage = true;
                            $scope.outOfGageH = true;
                        }
                    }
                }
            }
        }

        function isNotBlank(field){
            if(field !== undefined && field !== null && field !== ""){
                return true;
            }else{
                return false;
            }
        }

            function IFTET(ifThis, elseThat, debug){
                if(ifThis === undefined || ifThis === null){
                    if(elseThat === undefined || elseThat === null){
                        return null;
                    }else{
                        return elseThat;
                    }
                }else{
                    return ifThis;
                }
            }
}]);
