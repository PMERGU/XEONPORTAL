'use strict';

angular.module('portalApp')
	.factory('PODReport', function($resource, DateUtils) {
		return $resource('api/podReport', {}, {
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
            'getByUserDownload': {
                url: 'api/stockReport/download/:id',
                method: 'GET',
                responseType: 'arraybuffer',
//                cache: true
            },
            'save': {
                method: 'POST',
                isArray: true,
                transformRequest: function (data) {
                    return angular.toJson(data);
                }
            }
            
		});
	});