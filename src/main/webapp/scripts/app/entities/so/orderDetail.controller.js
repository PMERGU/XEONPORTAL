'use strict';

angular.module('portalApp')
    .controller('NewOrderDetailController', function ($scope, $stateParams, $sce, $window, $q, Principal,SOService,  $log, CustomerOrders, FileSaver, Blob, $interval, Upload, Attachment, Comment,
                                                        order, deliveryNo, orderStep, delAttachments, poAttachments, comments,huDetails) {
        
        $scope.order=order.Order;
        $scope.purchaseOrder=order.poData;
        $scope.poNumber=$stateParams.poNumber;
        $scope.deliveryNo = deliveryNo;
        $scope.huDetails=huDetails;
        $scope.orderResolved = false;
        $scope.attachments = [];
        
        
        $scope.orderTypes = {
        		
        		'ZOUT' : 'OUTBOUND',
				'ZINB':'INBOUND',
				'YCO':'Transport',
				'ZRET': 'INBOUND',             //'Returns' replaced with INBOUND bcz for both INBOUND and retuns coditions are same in html page
				'ZFF' : 'Freight Forwarding & Clearing',
				'ZWSE': 'Storage'
        		
        }
        
        $scope.orderType = $scope.orderTypes[$stateParams.type];
        
        $log.debug("purchaseorder :: "+ $scope.purchaseOrder);
        
        $log.debug("OrdserType :: "+ $scope.orderType);
        $scope.log = function(message) {
            $log.debug(message);
        }
         $log.debug("Hu Details" +$scope.huDetails) ;
        

        $scope.states = {
            'RECEIVED': true,
            'PROCESSED':  $scope.purchaseOrder === undefined || $scope.purchaseOrder.state === undefined ? true : $scope.purchaseOrder.state === 'PROCESSED' ? true : false,
            'COLLECTED': false,
            'IN_TRANSIT': false,
            'DELIVERED': false,
            'POD': false,
            'INVOICE': false
        };

        delAttachments.$promise.then(function(result){
            for(var i=0; i < result.length; i++){
                $scope.attachments.push(result[i]);
                //check for po deliveries
                if(result[i].category === 'POD'){
                    $scope.podDate = new Date(result[i].createdDate);
                    $scope.states['POD'] = true;
                }

            }
        });
        poAttachments.$promise.then(function(result){
            for(var i=0; i < result.length; i++){
                $scope.attachments.push(result[i]);
                if(result[i].category === 'POD'){
                    $scope.podDate = new Date(result[i].createdDate);
                    $scope.states['POD'] = true;
                }
            }
        });
        $scope.comments = comments;
        $scope.comment = {
            id: null,
            message: null,
            purchaseOrder: {
            	poNumber: $scope.poNumber
            },
            user: null,
            internal: false,
            category: 'ORDER'
        };

        $log.debug($scope.attachments);
        $scope.isDownloadingAttachment = false;
        $scope.step = 1;

        CustomerOrders.getInvoice({deliveryNo: deliveryNo}).$promise.then(function(invoiceBlob){
            var creator = $window.URL || $window.webkitURL;
            var fileURL = creator.createObjectURL(invoiceBlob.response);
            $scope.states['INVOICE'] = true;
            $scope.states['INVOICE_LOADED'] = true;

            $scope.attachments.push({
                activated: true,
                category: "INVOICE",
                createdDate: new Date(),
                deliveryNumber: deliveryNo,
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
            $scope.states['INVOICE_LOADED'] = false;
            $scope.states['INVOICE'] = false;
        });

        //WHEN ORDER IS LOADED, LETS START WITH THE ORDER DATA
        $scope.order.$promise.then(function(result) {
            $scope.orderResolved = true;

            $scope.order = result.sort(function (order1, order2) {
                $log.debug(order1._tknum + " - " + order2._tknum);
                return order1._tknum - order2._tknum;
            });

            if($scope.order[0]._daten !== undefined && $scope.order[0]._daten !== null && $scope.order[0]._daten !== ""){
                $scope.states['RECEIVED'] = true;
                $scope.states['PROCESSED'] = true;
                $scope.states['COLLECTED'] = true;
                $scope.states['IN_TRANSIT'] = true;
            }

            if($scope.order[0]._pdstk === "B" || $scope.order[0]._pdstk === "C" ){
                $scope.states['RECEIVED'] = true;
                $scope.states['PROCESSED'] = true;
                $scope.states['COLLECTED'] = true;
                $scope.states['IN_TRANSIT'] = true;
                $scope.states['DELIVERED'] = true;
            }
        });



        $scope.cuComment = function(comment){
            $log.debug("cuComment");
            $log.debug(comment);
            Comment.update(comment, function (result) {
                $scope.isSavingComment = false;
                comments.push(result);
                $scope.comment.message = null;
            }, function (result) {
                $log.error(result);
                $scope.isSavingComment = false;
            });
        };

        $scope.deleteComment = function(comment){
            $log.debug("deleteComment");
            $log.debug(comment);
            Comment.delete(comment, function (result) {
                $scope.isSavingComment = false;
            }, function (result) {
                $log.error(result);
                $scope.isSavingComment = false;
            });
        };

        $interval(function () {
            PurchaseOrder.getComments({id:  $scope.purchaseOrder.id}).$promise.then(function(result){
                $scope.comments = result;
            });
        }, 60000);

        $scope.downloadPod = function(){
            FileSaver.saveAs($scope.podBlob, deliveryNo + '.jpg');
        };

        $scope.downloadInvoice = function(){
            FileSaver.saveAs($scope.invoiceBlob, deliveryNo + '.pdf');
        };

        $scope.downloadAttachment = function(attachment){
            $scope.isDownloadingAttachment = true;
            $scope.selectedAttachment = attachment;
            $log.debug("downloadAttachment(" + attachment.uuid + " )");
            if(attachment.category === 'INVOICE' && attachment.uuid === null){
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
