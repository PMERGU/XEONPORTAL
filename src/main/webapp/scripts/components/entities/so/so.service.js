'use strict';

angular.module('portalApp')
    .factory('SOService', function ($resource, DateUtils) {
        return $resource('api/so', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            
            'getByCustomerNumber': {
                method: 'GET', isArray: true,
                url: 'api/so/:customerNumber/orders',
                transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                }
            },
            'getByDeliveryNo': {
                method: 'GET', isArray: true,
                url: 'api/so/orders/:deliveryNo',
                transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    return angular.toJson(data);
                }
            }
        });
    });
