'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('report', {
                parent: 'entity',
                url: '/report',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Reports'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/report/report.html',
                        controller: 'ReportController'
                    }
                },
                resolve: {
                }
            })
            
            .state('report.stock', {
                parent: 'report',
                url: '/stock',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/report/stock-report.html',
                        controller: 'StockReportController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    company: null,
                                    mName: null,
                                    plantNo: null,
                                    sType: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('report', null, { reload: true });
                    }, function() {
                        $state.go('report');
                    })
                }]
            });
    });
