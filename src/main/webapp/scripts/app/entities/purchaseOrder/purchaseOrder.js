'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('purchaseOrder', {
                parent: 'entity',
                url: '/purchaseOrders',
                params: {
                    queryType: null
                },
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'PurchaseOrders'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrders.html',
                        controller: 'PurchaseOrdersController'
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
                parent: 'site',
                url: '/purchaseOrder/crud/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Purchase Order'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder.html',
                        controller: 'PurchaseOrderController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PurchaseOrder', function($stateParams, PurchaseOrder) {
                        if($stateParams.id){
                            return PurchaseOrder.get({id : $stateParams.id});
                        }else{
                            return undefined;
                        }
                    }],
                    entityLines: ['$stateParams', 'PurchaseOrder', function($stateParams, PurchaseOrder) {
                        if($stateParams.id){
                            return PurchaseOrder.getLines({id : $stateParams.id});
                        }else{
                            return undefined;
                        }
                    }],
                    currentUser: ['$stateParams', 'Principal', function($stateParams, Principal) {
                        return Principal.identity();
                    }],
                    staticEnums: ['$stateParams', 'StaticServices', function($stateParams, StaticServices) {
                        return StaticServices.getAll()
                            .$promise.then(function (data) {
                                    return data;
                                }, function (errResponse) {
                                    console.error(errResponse);
                                    return undefined;
                                }
                            );
                    }]
                }

            })
            .state('purchaseOrder.poLine', {
                parent: 'purchaseOrder.new',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                params: {
                    serviceType: null,
                    poLine: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/poLine-dialog.html',
                        controller: 'PoLineDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                if($stateParams.poLine){
                                    return $stateParams.poLine;
                                }else{
                                    return {
                                        materialNumber: null,
                                        orderQuantity: null,
                                        unitOfMeasure: null,
                                        warehouse: null,
                                        length: null,
                                        width: null,
                                        height: null,
                                        grossWeight: null,
                                        netWeight: null,
                                        batchNumber: null,
                                        id: null,
                                        dvType: 'DIMENSIONS'
                                    };
                                }
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('purchaseOrder.new');
                    }, function() {
                        $state.go('purchaseOrder.new');
                    })
                }]
            })
            .state('purchaseOrder.newParty', {
                parent: 'purchaseOrder.new',
                url: '/newParty',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                params: {
                  for: null,
                  company: null
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/party-dialog.html',
                        controller: 'POPartyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    houseNumber: null,
                                    streetName: null,
                                    area: null,
                                    reference: null,
                                    company: $stateParams.company,
                                    id: null,
                                    sapId: 100000,
                                    type: "OTHER"
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('purchaseOrder.new');
                    }, function() {
                        $state.go('purchaseOrder.new');
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
            })
            .state('purchaseOrder.process', {
                parent: 'purchaseOrder.new',
                url: '/process',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-process-dialog.html',
                        controller: 'PurchaseOrderProcessController',
                        size: 'md',
                        resolve: {
                            entity: ['PurchaseOrder', function(PurchaseOrder) {
                                return PurchaseOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        console.log("response");
                        console.log(result);
                        $state.go('purchaseOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('purchaseOrder.comment', {
                parent: 'purchaseOrder.new',
                url: '/comment',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/purchaseOrder/purchaseOrder-comment-dialog.html',
                        controller: 'PurchaseOrderCommentController',
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
