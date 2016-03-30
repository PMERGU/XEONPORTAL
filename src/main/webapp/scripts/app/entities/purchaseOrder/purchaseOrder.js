'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('purchaseOrder', {
                parent: 'entity',
                url: '/purchaseOrders',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'PurchaseOrders'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrders.html',
                        controller: 'PurchaseOrderController'
                    }
                },
                resolve: {
                }
            })
            .state('purchaseOrder.detail', {
                parent: 'entity',
                url: '/purchaseOrder/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'PurchaseOrder'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-detail.html',
                        controller: 'PurchaseOrderDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PurchaseOrder', function($stateParams, PurchaseOrder) {
                        return PurchaseOrder.get({id : $stateParams.id});
                    }]
                }
            })
            .state('purchaseOrder.new', {
                parent: 'entity',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-dialog.html',
                        controller: 'PurchaseOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
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
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('purchaseOrder', null, { reload: true });
                    }, function() {
                        $state.go('purchaseOrder');
                    })
                }]
            })
            .state('purchaseOrder.edit', {
                parent: 'purchaseOrder',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-dialog.html',
                        controller: 'PurchaseOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PurchaseOrder', function(PurchaseOrder) {
                                return PurchaseOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('purchaseOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('purchaseOrder.delete', {
                parent: 'purchaseOrder',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-delete-dialog.html',
                        controller: 'PurchaseOrderDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['PurchaseOrder', function(PurchaseOrder) {
                                return PurchaseOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('purchaseOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
