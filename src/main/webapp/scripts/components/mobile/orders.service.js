'use strict';

angular.module('portalApp')
    .factory('CustomerOrders', function ($resource) {
        return $resource('api/mobile/customers/:id/orders', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                isArray: true,
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'getPod': {
                url: "api/mobile/pods/:deliveryNo",
                method: 'GET',
                headers: {
                    accept: 'image/jpeg'
                },
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data) {
                    var jpg;
                    if (data) {
                        jpg = new Blob([data], {
                            type: 'image/jpeg'
                        });
                    }
                    return {
                        response: jpg
                    };
                }
            },
            'getInvoice': {
                url: "api/mobile/invoices/:deliveryNo",
                method: 'GET',
                headers: {
                    accept: 'application/pdf'
                },
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data) {
                    var pdf;
                    if (data) {
                        pdf = new Blob([data], {
                            type: 'application/pdf'
                        });
                    }
                    return {
                        response: pdf
                    };
                }
            }
        });
    });
