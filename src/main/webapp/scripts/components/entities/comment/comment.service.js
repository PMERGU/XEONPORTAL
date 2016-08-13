'use strict';

angular.module('portalApp')
    .factory('Comment', function ($resource, DateUtils) {
        return $resource('api/comments/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'delete': {
                method: 'delete'
            }
        });
    });
