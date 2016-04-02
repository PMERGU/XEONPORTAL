'use strict';

angular.module('portalApp')
    .controller('XeonMainController', function ($scope, $cacheFactory, $interval, Principal, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder, PurchaseOrder) {
        $scope.processed = [];
        $scope.unprocessed = [];

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
            getCapturedPOs();
        });

        $scope.reloadData = function () {
            getCapturedPOs();
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

        function getCapturedPOs(){
            PurchaseOrder.query().$promise.then(function(data) {
                $scope.processed = data.filter(function (el) {
                    return (el.state === "PROCESSED");
                });
                $scope.unprocessed = data.filter(function (el) {
                    return (el.state === "UNPROCESSED");
                });
            });
        }

        $interval(function(){
            getCapturedPOs();
        }, 60000);
    });
