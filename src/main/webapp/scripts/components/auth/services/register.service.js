'use strict';

angular.module('portalApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


