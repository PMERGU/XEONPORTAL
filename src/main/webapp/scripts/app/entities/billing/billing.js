'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billing', {
                parent: 'entity',
                url: '/billing',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Billings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billing/billings.html',
                        controller: 'BillingController'
                    }
                },
                resolve: {
                }
            })
            
            .state('billing.new', {
                parent: 'billing',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billing/billing-dialog.html',
                        controller: 'BillingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    source: null,
                                    sourceZone: null,
                                    destination: null,
                                    destinationZone: null,
                                    weight: null,
                                    serviceType: null,
                                    serviceLevel:null,
                                    volume : null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billing', null, { reload: true });
                    }, function() {
                        $state.go('billing');
                    })
                }]
            });
    });
