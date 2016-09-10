'use strict';

angular.module('portalApp')
    .factory('StaticServices', function ($resource) {
        return $resource('api/static/', {}, {
            'getAll': { url: 'api/static', method: 'GET', isArray: false}
        });
    });
