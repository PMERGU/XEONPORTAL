'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('finishReset', {
                parent: 'bare',
                url: '/reset/finish?key',
                data: {
                    authorities: [],
                    pageTitle: 'Reset password'
                },
                views: {
                    'login@': {
                        templateUrl: 'scripts/app/account/reset/finish/reset.finish.html',
                        controller: 'ResetFinishController'
                    }
                },
                resolve: {

                }
            });
    });
