'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: ['ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_ADMIN'],
                    pageTitle: 'Dashboard'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'MainController'
                    }
                },
                onEnter: ['$stateParams', '$state', 'Principal', function ($stateParams, $state, Principal) {
                    Principal.hasAuthority('ROLE_USER')
                        .then(function (result) {
                            if (result) {
                                $state.go('xeonhome', null, {reload: true});
                            }
                        });
                }],
                resolve: {}
            })
            .state('orderdetail', {
                parent: 'site',
                url: '/purchaseOrder/{poId}/orders/{deliveryNo}?step={orderStep})',
                data: {
                    authorities: ['ROLE_CUSTOMER', 'ROLE_USER'],
                    pageTitle: 'Order Details'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/orderDetail.html',
                        controller: 'MainOrderDetailController'
                    }
                },
                params: {
                },
                resolve: {
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
            .state('orderdetail.attachment', {
                parent: 'orderdetail',
                url: '/attachment',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                },
                params: {
                    for: null,
                    company: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/main/attachment-dialog.html',
                        controller: 'AttachmentDialogController',
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
                        $state.go('orderdetail', null, {reload: true});
                    }, function () {
                        $state.go('orderdetail');
                    })
                }]
            })
            .state('orderdetail.attachmentDelete', {
                parent: 'orderdetail',
                url: '/attachment/{id}/delete',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                },
                params: {
                    attachment: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/main/attachment-delete-dialog.html',
                        controller: 'AttachmentDeleteController',
                        size: 'md',
                        resolve: {
                            attachment: function () {
                                return $stateParams.attachment;
                            }
                        }

                    }).result.then(function () {
                        $state.go('orderdetail', null, {reload: true});
                    }, function () {
                        $state.go('^');
                    })
                }]
            })
            .state('xeonhome', {
                parent: 'site',
                url: '/xeon',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN'],
                    pageTitle: 'Dashboard'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/xeonmain.html',
                        controller: 'XeonMainController'
                    }
                },
                resolve: {}
            })
            .state('salesOrders', {
                parent: 'site',
                url: '/salesOrders/{company}',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_CUSTOMER_CSU'],
                    pageTitle: 'Sales Orders'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/salesOrders.html',
                        controller: 'SalesOrdersController'
                    }
                },
                resolve: {
                    identity: ['$stateParams', 'Principal', function($stateParams, Principal) {
                        return Principal.identity();
                    }]
                }
            });
    });
