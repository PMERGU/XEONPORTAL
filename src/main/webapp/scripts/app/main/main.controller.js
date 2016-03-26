'use strict';

angular.module('portalApp')
    .controller('MainController', function ($scope, $cacheFactory, Principal, Company, CustomerOrders, DTOptionsBuilder, DTColumnDefBuilder) {
        $scope.deliveredOrders = [];
        $scope.undeliveredOrders = [];
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withBootstrap()
            .withPaginationType('full_numbers')
            .withOption('rowCallback', rowCallback)
            .withDisplayLength(13);
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
            $scope.company = Company.get({id : account.company.id });
            getOrders();
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
            CustomerOrders.get({id : $scope.account.company.sapId }).$promise.then(function(data) {
                $scope.deliveredOrders = data.filter(function (el) {
                    return (el.PDSTK === "C");
                });
                $scope.undeliveredOrders = data.filter(function (el) {
                    console.log(el.PDSTK);
                    return (el.PDSTK !== "C");
                });
            });
        }

    });
