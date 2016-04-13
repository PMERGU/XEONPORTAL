'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, $cacheFactory, $log, Principal, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, PurchaseOrder) {
        $scope.deliveredOrders = [];
        $scope.undeliveredOrders = [];
        $scope.ordersStep = 0;
        $scope.todaysDate = new Date();
        $scope.loadingOrders = false;

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
            }
            getOrders(new Date());
            getCapturedPOs();
        });

        $scope.reloadData = function () {
            var resetPaging = true;
            getOrders(new Date());
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

        function getOrders(dateT){
            $scope.loadingOrders = true;
            if($scope.account.company.id !== null) {
                CustomerOrders.get({
                    id: $scope.account.company.sapId,
                    from: new Date(new Date(dateT).setMonth(dateT.getMonth() - 1)),
                    to: dateT
                }).$promise.then(function (data) {
                    $scope.deliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "B" || el.pdstk === "C");
                    });
                    $scope.undeliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "A");
                    });
                    $scope.loadingOrders = false;
                });
            }
        }

        function getCapturedPOs(){
            PurchaseOrder.query().$promise.then(function(data) {
                $scope.capturedPos = data;
                $scope.posProcessed = data.filter(function (el) {
                    return (el.state === "PROCESSED");
                });
            });
        }

    });
