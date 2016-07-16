'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: ['ROLE_CUSTOMER','ROLE_USER','ROLE_ADMIN'],
                    pageTitle: 'Dashboard'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'MainController'
                    }
                },
                onEnter: ['$stateParams', '$state', 'Principal', function($stateParams, $state, Principal) {
                    Principal.hasAuthority('ROLE_USER')
                        .then(function (result) {
                            if (result) {
                                $state.go('xeonhome', null, { reload: true });
                            }
                        });
                }],
                resolve: {
                }
            })
            .state('orderdetail', {
                parent: 'site',
                url: '/orderdetail/{deliveryNo}',
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
                    order: null,
                    orderStep: null,
                    company: null
                },
                resolve: {
                    purchaseOrder: ['$stateParams', 'PurchaseOrder', function($stateParams, PurchaseOrder) {
                        if($stateParams.order){
                            return PurchaseOrder.getByPONumber({poNumber : $stateParams.order.bstkd}).$promise.then(function(purchaseOrder) {
                                return purchaseOrder;
                            }, function(errResponse) {
                                console.log(errResponse);
                                return undefined;
                            });
                        }else{
                            return undefined;
                        }
                    }],
                    orderGroup: ['$stateParams', 'PurchaseOrder', 'CachedOrders', function($stateParams, PurchaseOrder, CachedOrders) {
                        return CachedOrders.getOrderGroup($stateParams.orderStep, $stateParams.company.sapId, $stateParams.order.dbeln);
                    }],
                    attachments: ['$stateParams', 'Attachment', 'CachedOrders', function($stateParams, Attachment, CachedOrders) {
                        return Attachment.query({deliveryNumber:  $stateParams.order.dbeln})
                    }]
                }
            })
            .state('orderdetail.attachment', {
                parent: 'orderdetail',
                url: '/attachment',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                params: {
                    for: null,
                    company: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
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
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orderdetail', null, { reload: true });
                    }, function() {
                        $state.go('orderdetail');
                    })
                }]
            })
            .state('orderdetail.attachmentDelete', {
                parent: 'orderdetail',
                url: '/attachment/{id}/delete',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                params: {
                    attachment: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/main/attachment-delete-dialog.html',
                        controller: 'AttachmentDeleteController',
                        size: 'md',
                        resolve: {
                            attachment: function() {
                                return $stateParams.attachment
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orderdetail', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('xeonhome', {
                parent: 'site',
                url: '/xeon',
                data: {
                    authorities: ['ROLE_USER','ROLE_ADMIN'],
                    pageTitle: 'Dashboard'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/xeonmain.html',
                        controller: 'XeonMainController'
                    }
                },
                resolve: {
                }
            })
            .state('salesOrders', {
                parent: 'site',
                url: '/salesOrders',
                data: {
                    authorities: ['ROLE_USER','ROLE_ADMIN'],
                    pageTitle: 'Sales Orders'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/salesOrders.html',
                        controller: 'SalesOrdersController'
                    }
                },
                resolve: {
                }
            });
    });
