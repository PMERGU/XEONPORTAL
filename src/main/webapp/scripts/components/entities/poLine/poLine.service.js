'use strict';

angular.module('portalApp')
    .factory('PoLine', function ($resource, DateUtils) {
        return $resource('api/poLines/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
