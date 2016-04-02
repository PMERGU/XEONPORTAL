 'use strict';

angular.module('portalApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-portalApp-alert');
                if (angular.isString(alertKey)) {
                    console.log(113);
                    AlertService.success(alertKey, { param : response.headers('X-portalApp-params')});
                }
                return response;
            }
        };
    });
