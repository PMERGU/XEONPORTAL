'use strict';

angular.module('portalApp')
	.factory('IOReport', function($resource, DateUtils) {
		return $resource('api/iorReport', {}, {
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
                url: 'api/iorReport/download/:id',
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