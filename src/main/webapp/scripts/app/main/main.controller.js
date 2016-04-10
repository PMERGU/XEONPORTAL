'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, $cacheFactory, Principal, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, PurchaseOrder) {
        $scope.deliveredOrders = [];
        $scope.undeliveredOrders = [];

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

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            if (account.company.id !== null){
                $scope.company = Company.get({id: account.company.id});
            }
            getOrders();
            getCapturedPOs();
        });

        $scope.reloadData = function () {
            var resetPaging = true;
            getOrders();
        };

        function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            // Unbind first in order to avoid any duplicate handler (see https://github.com/l-lin/angular-datatables/issues/87)
            $('td', nRow).unbind('click');
            $('td', nRow).bind('click', function() {
                $scope.$apply(function() {
                    console.log(aData);
                });
            });
            return nRow;
        };

        function getOrders(){
            if($scope.account.company.id !== null){
                CustomerOrders.get({id : $scope.account.company.sapId }).$promise.then(function(data) {
                    $scope.deliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "B" || el.pdstk === "C");
                    });
                    $scope.undeliveredOrders = data.filter(function (el) {
                        return (el.pdstk === "A");
                    });
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
