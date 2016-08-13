'use strict';

angular.module('portalApp')
    .factory('PurchaseOrder', function ($resource, DateUtils) {
        return $resource('api/purchaseOrders/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'queryByState': {
                url: 'api/purchaseOrders/state/:state',
                method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.captureDate = DateUtils.convertDateTimeFromServer(data.captureDate);
                    data.deliveryDate = DateUtils.convertLocaleDateFromServer(data.deliveryDate);
                    return data;
                }
            },
            'getByPONumber': {
                method: 'GET',
                url: 'api/purchaseOrders/poNumber/:poNumber'
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.deliveryDate = DateUtils.convertLocaleDateToServer(data.deliveryDate);
                    return angular.toJson(data);
                }
            },
            'updateState': {
                url: 'api/purchaseOrders/:id/state',
                method: 'PUT'
            },
            'updateComment': {
                url: 'api/purchaseOrders/:id/comment',
                method: 'PUT'
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.deliveryDate = DateUtils.convertLocaleDateToServer(data.deliveryDate);
                    return angular.toJson(data);
                }
            },
            'getLines': {
                url: 'api/purchaseOrders/:id/lines',
                method: 'GET', isArray: true
            },
            'getComments': {
                url: 'api/purchaseOrders/:id/comments',
                method: 'GET', isArray: true
            },
            'getAttachments': {
                url: 'api/purchaseOrders/:id/all/attachments',
                method: 'GET', isArray: true
            }
        });
    });
