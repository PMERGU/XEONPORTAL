'use strict';

angular.module('portalApp')
    .controller('MainOrderDetailController', function ($scope, $stateParams, Principal, purchaseOrder, orderGroup, $log) {
        $log.log(orderGroup);
        $log.log(purchaseOrder);
        purchaseOrder = purchaseOrder !== undefined ? purchaseOrder : {
            state: "NOT_FOUND"
        };
        $log.log(purchaseOrder.state);
        $scope.purchaseOrder = purchaseOrder;
        $scope.orderGroup = orderGroup;
        $scope.states = {
            "RECEIVED": true,
            "PROCESSED": false,
            "COLLECTED": false,
            "IN_TRANSIT": false,
            "DELIVERED": false,
            "POD": false,
            "INVOICE": false,
        };

        $scope.states["PROCESSED"] = purchaseOrder.state === "PROCESSED" ? true : false;

        $log.log("group size " + $scope.orderGroup.length);
        $scope.orderGroup = $scope.orderGroup.sort(function (order1, order2) {
            $log.log(order1.tknum + " - " + order2.tknum);
            return order1.tknum - order2.tknum;
        });

        $log.log("start date : " + $scope.orderGroup[0].datbg);
        $log.log("start time : " + $scope.orderGroup[0].uatbg);

        if($scope.orderGroup[0].datbg !== undefined && $scope.orderGroup[0].datbg !== null && $scope.orderGroup[0].datbg !== ""){
            $scope.states["RECEIVED"] = true;
            $scope.states["PROCESSED"] = true;
            $scope.states["COLLECTED"] = true;
            $scope.states["IN_TRANSIT"] = true;
        }

        if($scope.orderGroup[0].pdstk === "B" || $scope.orderGroup[0].pdstk === "C" ){
            $scope.states["DELIVERED"] = true;
        }

        if($scope.states["DELIVERED"]){
            $scope.states["POD"] = true;
        }

    });
