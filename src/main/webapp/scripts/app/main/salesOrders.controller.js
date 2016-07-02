'use strict';

angular.module('portalApp')
    .controller('SalesOrdersController', function ($scope, $stateParams, $sce, $window, $log, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, CachedOrders, PurchaseOrder) {
        $scope.deliveredOrders = [];
        $scope.undeliveredOrders = [];
        $scope.ordersStep = 0;
        $scope.todaysDate = new Date();
        $scope.loadingOrders = false;

        $scope.companies = Company.query();
        $scope.company = null;
        $scope.selected = {
            company: null
        };

        //WATCH when company is selected (for capture as company employee feature)
        $scope.$watch(function() {
            return $scope.selected.company
        }, function (company) {
            $log.debug("test");
            if(company != null && company != undefined) {
                $log.debug(company);
                $scope.reloadData(false);
            }
        });

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

        function getOrders(dateT, force){
            $scope.loadingOrders = true;
            if($scope.selected.company.id !== null) {
                CachedOrders.getOrders($scope.ordersStep,
                    $scope.selected.company.sapId,
                    new Date(new Date(dateT).setMonth(dateT.getMonth() - 1)),
                    // new Date(new Date(dateT).setDate(dateT.getDate() - 1)),
                    new Date(new Date(dateT).setDate(dateT.getDate()+1)),
                    force
                ).then(function (data) {
                    $scope.deliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "B" || el.pdstk === "C");
                    });
                    $scope.undeliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "A" || el.pdstk === "");
                    });
                    $scope.loadingOrders = false;
                });
            }
        };

        $scope.reloadData = function (refresh) {
            var resetPaging = true;
            getOrders(new Date(), refresh);
            Company.getPurchaseOrders({id: $scope.selected.company.id}).$promise.then(function(data){
                $scope.purchaseOrders = {};
                data.forEach(function(po, idx){
                    $scope.purchaseOrders[po.poNumber] = po;
                });
            });
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

    });
