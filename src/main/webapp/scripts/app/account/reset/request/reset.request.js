'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('requestReset', {
                parent: 'bare',
                url: '/reset/request',
                data: {
                    authorities: []
                },
                views: {
                    'login@': {
                        templateUrl: 'scripts/app/account/reset/request/reset.request.html',
                        controller: 'RequestResetController'
                    }
                },
                resolve: {

                }
            });
    });
