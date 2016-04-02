'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: ['ROLE_CUSTOMER'],
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
            });
    });
