'use strict';

angular.module('portalApp')
    .factory('Party', function ($resource, DateUtils) {
        return $resource('api/partys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'getXeon':{ url: 'api/partys/company/xeon', method: 'GET', isArray: true}
        });
    });
