'use strict';

angular.module('portalApp')
    .config(function ($stateProvider) {
        $stateProvider.state('report', {
            parent: 'entity',
            url: '/report',
            params: {
                queryType: null
            },
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
                parent: 'site',
                url: '/report/stock',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'Stock Report'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/report/stockReport.html',
                        controller: 'StockReportController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'StockReport', function($stateParams, StockReport) {
                        if($stateParams.id){
                            return StockReport.get({id : $stateParams.id});
                        }else{
                            return undefined;
                        }
                    }],
                    entityLines: ['$stateParams', 'StockReport', function($stateParams, StockReport) {
                        if($stateParams.id){
                            return StockReport.getLines({id : $stateParams.id});
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
             ;
    });
