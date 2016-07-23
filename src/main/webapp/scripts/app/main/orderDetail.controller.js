'use strict';

angular.module('portalApp')
    .controller('MainOrderDetailController', function ($scope, $stateParams, $sce, $window, $q, Principal, PurchaseOrder, purchaseOrder, orderGroup, $log, CustomerOrders, FileSaver, Blob, $interval, Upload, poAttachments, delAttachments, Attachment) {
        $scope.attachments = [];
        delAttachments.$promise.then(function(result){
            for(var i=0; i < result.length; i++){
                $scope.attachments.push(result[i]);
                //check for po deliveries
                if(result[i].category === 'POD'){
                    $scope.podDate = new Date(result[i].createdDate);
                    $scope.states['POD'] = true;

                    CustomerOrders.getInvoice({deliveryNo: $scope.orderGroup[0].dbeln}).$promise.then(function(invoiceBlob){
                        var creator = $window.URL || $window.webkitURL;
                        var fileURL = creator.createObjectURL(invoiceBlob.response);
                        $scope.states['INVOICE'] = true;
                        $scope.states['INVOICE_LOADED'] = true;

                        $scope.attachments.push({
                            activated: true,
                            category: "INVOICE",
                            createdDate: new Date(),
                            deliveryNumber: $scope.orderGroup[0].dbeln,
                            description: "Invoice, generated on completion of order",
                            id: $scope.attachments.length+1,
                            mimeType: "application/pdf",
                            user: {firstName: "Xeon", lastName: "finance"},
                            blob: invoiceBlob.response,
                            uuid: null,
                            fileName: null,
                            purchaseOrder: null,
                            visible: true
                        });
                    },function(err){
                        $log.debug("Invoice could not be found: " + err);
                        $scope.states['INVOICE_LOADED'] = false;
                        $scope.states['INVOICE'] = false;
                    });
                }

            }
        });
        poAttachments.$promise.then(function(result){
            for(var i=0; i < result.length; i++){
                $scope.attachments.push(result[i]);
            }
        });

        $log.debug($scope.attachments);
        $scope.isDownloadingAttachment = false;
        purchaseOrder = purchaseOrder !== undefined ? purchaseOrder : {
            state: 'NOT_FOUND'
        };
        $scope.step = 1;

        $scope.purchaseOrder = purchaseOrder;
        $scope.orderGroup = orderGroup;
        $scope.states = {
            'RECEIVED': true,
            'PROCESSED': false,
            'COLLECTED': false,
            'IN_TRANSIT': false,
            'DELIVERED': false,
            'POD': false,
            'INVOICE': false
        };

        $scope.states['PROCESSED'] = purchaseOrder.state === 'PROCESSED' ? true : false;

        $log.log("group size " + $scope.orderGroup.length);
        $scope.orderGroup = $scope.orderGroup.sort(function (order1, order2) {
            $log.log(order1.tknum + " - " + order2.tknum);
            return order1.tknum - order2.tknum;
        });

        $log.log("start date : " + $scope.orderGroup[0].datbg);
        $log.log("start time : " + $scope.orderGroup[0].uatbg);

        if($scope.orderGroup[0].daten !== undefined && $scope.orderGroup[0].daten !== null && $scope.orderGroup[0].daten !== ""){
            $scope.states['RECEIVED'] = true;
            $scope.states['PROCESSED'] = true;
            $scope.states['COLLECTED'] = true;
            $scope.states['IN_TRANSIT'] = true;
        }

        if($scope.orderGroup[0].pdstk === "B" || $scope.orderGroup[0].pdstk === "C" ){
            $scope.states['DELIVERED'] = true;
        }


        $scope.downloadPod = function(){
            FileSaver.saveAs($scope.podBlob, $scope.orderGroup[0].dbeln + '.jpg');
        };

        $scope.downloadInvoice = function(){
            FileSaver.saveAs($scope.invoiceBlob, $scope.orderGroup[0].dbeln + '.pdf');
        };

        $scope.downloadAttachment = function(attachment){
            $scope.isDownloadingAttachment = true;
            $scope.selectedAttachment = attachment;
            $log.debug("downloadAttachment(" + attachment.uuid + " )");
            if(attachment.category === 'INVOICE'){
                FileSaver.saveAs(attachment.blob, "Xeon " + attachment.category.toLowerCase() + " - " + attachment.deliveryNumber);
                $scope.isDownloadingAttachment = false;
            }else{
                Attachment.get({uuid: attachment.uuid}).$promise.then(function(imageBlob){
                    $log.debug(imageBlob.response.type);
                    FileSaver.saveAs(imageBlob.response, attachment.category + "-" + attachment.id);
                    $scope.isDownloadingAttachment = false;
                },function(err){
                    $scope.isDownloadingAttachment = false;
                    $log.error("Count not download attachment");
                    $log.error(err);
                });
            }
        };
    });
