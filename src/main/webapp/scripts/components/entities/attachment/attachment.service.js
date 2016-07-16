'use strict';

angular.module('portalApp')
    .factory('Attachment', function ($resource) {
        return $resource('api/attachments/:uuid', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data, headers) {
                    var image;
                    if (data) {
                        var mine = headers('content-type').substr(0, headers('content-type').indexOf(';'));
                        image = new Blob([data], {
                            type: mine
                        });
                    }
                    return {
                        response: image
                    };
                }
            },
            'delete': { method:'DELETE' }
        });
    });
