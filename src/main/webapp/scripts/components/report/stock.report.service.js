'use strict';

angular.module('portalApp')
	.factory('StockReport', function($resource, DateUtils) {
		return $resource('api/stockReport', {}, {
			'query' : {
				method : 'POST',
				isArray : true
			},
			'get' : {
				method : 'POST',
				transformResponse : function(data) {
					data = angular.fromJson(data);
					return data;
				}
			},
            'getByUser': {
                url: 'api/stockReport/:id',
                method: 'GET', isArray: true
            }
		});
	});