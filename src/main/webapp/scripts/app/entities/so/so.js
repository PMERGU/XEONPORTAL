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
                url: '/so/orders/{deliveryNo}?step={orderStep})',
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
                	
                	salesOrder: ['$stateParams', 'SOService', function ($stateParams, SOService) {
                        return SOService.getByDeliveryNo({deliveryNo: $stateParams.deliveryNo});
                    }],
                    
                    purchaseOrder: ['$stateParams', 'PurchaseOrder', function ($stateParams, PurchaseOrder) {
                        return PurchaseOrder.get({id: $stateParams.poId})
                            .$promise.then(function (data) {return data;}, function (errResponse) {console.log(errResponse);return undefined;});
                    }],
                    order: ['$stateParams', 'PurchaseOrder', function ($stateParams, PurchaseOrder) {
                        return PurchaseOrder.getOrder({id: $stateParams.poId, deliveryNo: $stateParams.deliveryNo});
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
                    poAttachments: ['$stateParams', 'Attachment', '$q', 'PurchaseOrder', function ($stateParams, Attachment, $q, PurchaseOrder) {
                        return PurchaseOrder.getAttachments({id: $stateParams.poId});
                    }],
                    comments: ['$stateParams', 'Comment', '$q', 'PurchaseOrder', function ($stateParams, Comment, $q, PurchaseOrder) {
                        return PurchaseOrder.getComments({id: $stateParams.poId});
                    }],
                    huDetails: ['$stateParams', 'PurchaseOrder', function ($stateParams, PurchaseOrder) {
                        return PurchaseOrder.getHUDetails({id: $stateParams.poId, deliveryNo: $stateParams.deliveryNo});
                    }],
                    currentUser: ['$stateParams', 'Principal', function($stateParams, Principal) {
                        return Principal.identity();
                    }]
                }
            })
    });
