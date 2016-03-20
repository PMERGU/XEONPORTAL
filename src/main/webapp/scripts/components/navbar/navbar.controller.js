'use strict';

angular.module('portalApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal, ENV, MonitoringService) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';
        $scope.servicesDown = [];

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };

        MonitoringService.checkHealth().then(function (response) {
            $.each(response, function(object, id){
                if(this.status === "DOWN"){
                    $scope.servicesDown.push({name: object, result: this});
                }
            });
        }, function (response) {
            $.each(response.data, function(object, id){
                if(this.status === "DOWN"){
                    $scope.servicesDown.push({name: object, result: this})
                }
            });
        });

    });
