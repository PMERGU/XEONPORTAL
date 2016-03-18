'use strict';

angular.module('portalApp')
    .controller('PurchaseOrderController', function ($scope, $state, PurchaseOrder, ParseLinks) {

        $scope.purchaseOrders = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            PurchaseOrder.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.purchaseOrders = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.purchaseOrder = {
                state: null,
                serviceLevel: null,
                captureDate: null,
                deliveryDate: null,
                poNumber: null,
                reference: null,
                customerType: null,
                shipToType: null,
                telephone: null,
                collective: null,
                accountReference: null,
                modeOfTransport: null,
                carrierVesselName: null,
                carrierVesselNumber: null,
                pickUpType: null,
                id: null
            };
        };
    });
