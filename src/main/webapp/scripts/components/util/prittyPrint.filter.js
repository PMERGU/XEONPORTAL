'use strict';

angular.module('portalApp')
    .filter('prittyPrint', function () {
        return function (input, scope) {
            if (input != null)
                input = input.toLowerCase();
            return input.substring(0, 1).toUpperCase() + input.substring(1).split('__').join('.').split('_').join(' ');
        }
    });
