'use strict';

angular.module('portalApp')
    .controller('MainOrderDetailController', function ($scope, $stateParams, $sce, $window, Principal, purchaseOrder, orderGroup, $log, CustomerOrders, FileSaver, Blob, $interval, Upload) {
        purchaseOrder = purchaseOrder !== undefined ? purchaseOrder : {
            state: "NOT_FOUND"
        };
        $scope.step = 1;

        $scope.purchaseOrder = purchaseOrder;
        $scope.orderGroup = orderGroup;
        $scope.states = {
            "RECEIVED": true,
            "PROCESSED": false,
            "COLLECTED": false,
            "IN_TRANSIT": false,
            "DELIVERED": false,
            "POD": false,
            "INVOICE": false
        };

        $scope.states["PROCESSED"] = purchaseOrder.state === "PROCESSED" ? true : false;

        $log.log("group size " + $scope.orderGroup.length);
        $scope.orderGroup = $scope.orderGroup.sort(function (order1, order2) {
            $log.log(order1.tknum + " - " + order2.tknum);
            return order1.tknum - order2.tknum;
        });

        $log.log("start date : " + $scope.orderGroup[0].datbg);
        $log.log("start time : " + $scope.orderGroup[0].uatbg);

        if($scope.orderGroup[0].daten !== undefined && $scope.orderGroup[0].daten !== null && $scope.orderGroup[0].daten !== ""){
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

        $scope.podLoading = 0;
        if($scope.states["POD"]){
            var stopLoading = $interval(function(){
                if($scope.podLoading >= 100){
                    $interval.cancel(stopLoading);
                }
                $scope.podLoading+=1;
            }, 175);
            $scope.podDate = new Date(new Date($scope.orderGroup[0].audat).setMinutes(new Date($scope.orderGroup[0].audat).getMinutes() + 17));
            $scope.podMessge = "Loading proof of delivery...";
            CustomerOrders.getPod({deliveryNo: $scope.orderGroup[0].dbeln}).$promise.then(function(imageBlob){
                var stopLoading2 = $interval(function(){
                    if($scope.podLoading >= 100){
                        $interval.cancel(stopLoading2);
                        $scope.states["POD_LOADED"] = true;
                    }
                    $scope.podLoading+=1;
                }, 75);
                var creator = $window.URL || $window.webkitURL;
                var fileURL = creator.createObjectURL(imageBlob.response);
                $scope.podBlob = imageBlob.response;
                $scope.pod = $sce.trustAsResourceUrl(fileURL);
            },function(err){
                $scope.podMessge = "POD could not be found.";
                $scope.states["POD_LOADED"] = false;
                $scope.states["POD"] = false;
            });

            $scope.states["INVOICE"] = true;
        }

        if($scope.states["INVOICE"]){
            CustomerOrders.getInvoice({deliveryNo: $scope.orderGroup[0].dbeln}).$promise.then(function(invoiceBlob){
                var creator = $window.URL || $window.webkitURL;
                var fileURL = creator.createObjectURL(invoiceBlob.response);
                $scope.invoiceBlob = invoiceBlob.response;
                $scope.states["INVOICE_LOADED"] = true;
            },function(err){
                $log.debug("Invoice could not be found.");
                $scope.states["INVOICE_LOADED"] = false;
                $scope.states["INVOICE"] = false;
            });
        }

        $scope.downloadPod = function(){
            FileSaver.saveAs($scope.podBlob, $scope.orderGroup[0].dbeln + '.jpg');
        }

        $scope.downloadInvoice = function(){
            FileSaver.saveAs($scope.invoiceBlob, $scope.orderGroup[0].dbeln + '.pdf');
        }

        $scope.downloadAttachment = function(attachment){
            $log.debug("downloadAttachment() //TODO");
            $log.debug(attachment);
            // FileSaver.saveAs($scope.invoiceBlob, $scope.orderGroup[0].dbeln + '.pdf');
        }
        
    });
