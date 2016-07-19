'use strict';

angular.module('portalApp')
    .factory('StaticServices', function ($resource) {
        return $resource('api/static/', {}, {
            'categories': { url: 'api/static/attachmentCategories', method: 'GET', isArray: true},
            'materials': { url: 'api/static/materialTypes', method: 'GET', isArray: true}
        });
    });
