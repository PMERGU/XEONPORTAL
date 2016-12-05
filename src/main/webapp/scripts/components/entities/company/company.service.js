'use strict';

angular.module('portalApp')
	.factory('Company', function($resource, DateUtils) {
		return $resource('api/companies/:id', {}, {
			'query' : {
				method : 'GET',
				isArray : true
			},
			'get' : {
				method : 'GET',
				transformResponse : function(data) {
					data = angular.fromJson(data);
					return data;
				}
			},
			'getUsers' : {
				url : 'api/companies/:id/users',
				method : 'GET',
				isArray : true
			},
			'getPurchaseOrders' : {
				url : 'api/companies/:id/purchaseOrders',
				method : 'GET',
				isArray : true
			},
			'update' : {
				method : 'PUT'
			},
			'delete' : {
				url : 'api/companies/delete/:id',
				method : 'GET'
			}
		});
	});