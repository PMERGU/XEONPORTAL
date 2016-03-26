'use strict';

angular.module('portalApp')
    .factory('CustomerOrders', function ($resource) {
        return $resource('api/mobile/customers/:id/orders', {}, {
            'get': {
                method: 'GET',
                isArray: true,
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
