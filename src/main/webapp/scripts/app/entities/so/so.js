'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('so', {
                parent: 'entity',
                url: '/so',
                params: {
                    queryType: null
                },
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'SalesOrderss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/so/salesOrders.html',
                        controller: 'NewSalesOrdersController'
                    }
                },
                resolve: {
                    identity: ['$stateParams', 'Principal', function($stateParams, Principal) {
                        return Principal.identity();
                    }]
                }
            }) .state('orderdetailNew', {
                parent: 'site',
                url: '/so/orders/{poNumber}/{deliveryNo}/{type}?step={orderStep})',
                data: {
                    authorities: ['ROLE_CUSTOMER', 'ROLE_USER'],
                    pageTitle: 'Order Details'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/so/orderDetail.html',
                        controller: 'NewOrderDetailController'
                    }
                },
                params: {
                },
                resolve: {
                	
                	order: ['$stateParams', 'SOService', function ($stateParams, SOService) {
                        return SOService.getByDeliveryNo({deliveryNo: $stateParams.deliveryNo}).$promise.then(function (data) {
                        	var finalData={};
                        	return SOService.getPOByNumber({poNumber: $stateParams.poNumber})
                            .$promise.then(function (dataa) {
                            	var dataOr=data;
                            	var po=dataa;
                            	finalData.Order=dataOr;
                            	finalData.poData=po;
                            	return finalData;}, function (errResponse) {console.log(errResponse);return undefined;})
                        	
                        	
                        	 });
                    }],
                    
                    deliveryNo: ['$stateParams', function ($stateParams) {
                        return $stateParams.deliveryNo === undefined || $stateParams.deliveryNo === null ? 0 : $stateParams.deliveryNo;
                    }],
                    orderStep: ['$stateParams', function ($stateParams) {
                        return $stateParams.orderStep === undefined || $stateParams.orderStep === null ? 0 : $stateParams.orderStep;
                    }],
                    delAttachments: ['$stateParams', 'Attachment', '$q', 'PurchaseOrder', function ($stateParams, Attachment, $q, PurchaseOrder) {
                        return Attachment.query({deliveryNumber: $stateParams.deliveryNo});
                    }],
                    poAttachments: ['$stateParams', 'Attachment', '$q', 'SOService', function ($stateParams, Attachment, $q, SOService) {
                        return SOService.getAttachments({poNumber: $stateParams.poNumber});
                    }],
                    comments: ['$stateParams', 'Comment', '$q', 'SOService', function ($stateParams, Comment, $q, SOService) {
                        return SOService.getComments({poNumber: $stateParams.poNumber});
                    }],
                    huDetails: ['$stateParams', 'SOService', function ($stateParams, SOService) {
                        return SOService.getHUDetails({deliveryNo: $stateParams.deliveryNo});
                    }],
                    currentUser: ['$stateParams', 'Principal', function($stateParams, Principal) {
                        return Principal.identity();
                    }]
                }
            }).state('orderdetailNew.attachment', {
                parent: 'orderdetailNew',
                url: '/attachments',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                },
                params: {
                    for: null,
                    company: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/so/attachment-dialog.html',
                        controller: 'NewAttachmentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    category: null,
                                    description: null,
                                    deliveryNumber: $stateParams.for
                                };
                            },
                            currentUser: ['$stateParams', 'Principal', function($stateParams, Principal) {
                                return Principal.identity();
                            }]
                        }
                    }).result.then(function (result) {
                        $state.go('orderdetailNew', null, {reload: true});
                    }, function () {
                        $state.go('orderdetailNew');
                    })
                }]
            })
            .state('orderdetailNew.attachmentDelete', {
                parent: 'orderdetailNew',
                url: '/attachments/{id}/delete',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                },
                params: {
                    attachment: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/so/attachment-delete-dialog.html',
                        controller: 'NewAttachmentDeleteController',
                        size: 'md',
                        resolve: {
                            attachment: function () {
                                return $stateParams.attachment;
                            }
                        }

                    }).result.then(function () {
                        $state.go('orderdetailNew', null, {reload: true});
                    }, function () {
                        $state.go('^');
                    })
                }]
            })
    });
