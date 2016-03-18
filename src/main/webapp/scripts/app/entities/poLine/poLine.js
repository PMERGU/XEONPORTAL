'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('poLine', {
                parent: 'entity',
                url: '/poLines',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PoLines'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/poLine/poLines.html',
                        controller: 'PoLineController'
                    }
                },
                resolve: {
                }
            })
            .state('poLine.detail', {
                parent: 'entity',
                url: '/poLine/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PoLine'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/poLine/poLine-detail.html',
                        controller: 'PoLineDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PoLine', function($stateParams, PoLine) {
                        return PoLine.get({id : $stateParams.id});
                    }]
                }
            })
            .state('poLine.new', {
                parent: 'poLine',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/poLine/poLine-dialog.html',
                        controller: 'PoLineDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
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
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('poLine', null, { reload: true });
                    }, function() {
                        $state.go('poLine');
                    })
                }]
            })
            .state('poLine.edit', {
                parent: 'poLine',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/poLine/poLine-dialog.html',
                        controller: 'PoLineDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PoLine', function(PoLine) {
                                return PoLine.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('poLine', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('poLine.delete', {
                parent: 'poLine',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/poLine/poLine-delete-dialog.html',
                        controller: 'PoLineDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['PoLine', function(PoLine) {
                                return PoLine.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('poLine', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
