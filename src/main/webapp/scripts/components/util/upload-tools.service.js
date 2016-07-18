/*jshint bitwise: false*/
'use strict';

angular.module('portalApp')
    .service('UploadTools', function ($log, Upload) {
        this.upload = function (attachment, success, error, progress) {
            $log.debug(attachment);
            var files = attachment.files;
            var totalReturned = 0;
            var totalUploaded = 0;
            progress({completed: false, percentage: 10});
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    if (!file.$error) {
                        Upload.upload({
                            url: attachment.deliveryNumber ? 'api/attachments' : 'api/purchaseOrders/' + attachment.purchaseOrder.id + '/attachments',
                            headers: {
                                'Content-Type': file.type
                            },
                            data: {
                                file: file,
                                deliveryNumber: attachment.deliveryNumber ? attachment.deliveryNumber : null,
                                purchaseOrder: !attachment.deliveryNumber ? attachment.purchaseOrder : null,
                                category: attachment.category,
                                description: attachment.description
                            }
                        }).then(function (resp) {
                            totalReturned++;
                            progress({completed: false, percentage: ((totalUploaded*(100/files.length))/2 + (totalReturned*(100/files.length))/2)-10});
                            if(files.length === totalReturned){
                                progress({completed: true, percentage: 100});
                                success(resp);
                            }
                        }, function (resp) {
                            $log.error('Error status: ' + resp.status);
                            error(resp);
                        }, function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            if(totalUploaded <= files.length){
                                if(progressPercentage === 100){
                                    totalUploaded++;
                                }
                                progress({completed: false, percentage: (totalUploaded*(100/files.length))/3 + ((100/files.length)/100*progressPercentage)/2});
                            }
                        });
                    }
                }
            }
        };
    });

