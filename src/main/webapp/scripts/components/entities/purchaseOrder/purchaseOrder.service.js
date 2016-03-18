'use strict';

angular.module('portalApp')
    .factory('PurchaseOrder', function ($resource, DateUtils) {
        return $resource('api/purchaseOrders/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.captureDate = DateUtils.convertDateTimeFromServer(data.captureDate);
                    data.deliveryDate = DateUtils.convertLocaleDateFromServer(data.deliveryDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.deliveryDate = DateUtils.convertLocaleDateToServer(data.deliveryDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.deliveryDate = DateUtils.convertLocaleDateToServer(data.deliveryDate);
                    return angular.toJson(data);
                }
            }
        });
    });
