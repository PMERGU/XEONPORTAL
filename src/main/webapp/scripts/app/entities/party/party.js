'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('party', {
                parent: 'entity',
                url: '/partys',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Partys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/party/partys.html',
                        controller: 'PartyController'
                    }
                },
                resolve: {
                }
            })
            .state('party.detail', {
                parent: 'entity',
                url: '/party/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Party'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/party/party-detail.html',
                        controller: 'PartyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Party', function($stateParams, Party) {
                        return Party.get({id : $stateParams.id});
                    }]
                }
            })
            .state('party.new', {
                parent: 'party',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/party/party-dialog.html',
                        controller: 'PartyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    houseNumber: null,
                                    streetName: null,
                                    area: null,
                                    reference: null,
                                    id: null,
                                    sapId: 100000,
                                    type: "OTHER"
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('party', null, { reload: true });
                    }, function() {
                        $state.go('party');
                    })
                }]
            })
            .state('party.edit', {
                parent: 'party',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/party/party-dialog.html',
                        controller: 'PartyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Party', function(Party) {
                                return Party.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('party', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('party.delete', {
                parent: 'party',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/party/party-delete-dialog.html',
                        controller: 'PartyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Party', function(Party) {
                                return Party.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('party', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
