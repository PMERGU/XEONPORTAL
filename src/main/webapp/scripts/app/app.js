'use strict';

angular.module('portalApp', ['LocalStorageModule','anguFixedHeaderTable',
    'ngResource', 'ngCookies', 'ngAria', 'ngCacheBuster', 'ngFileUpload', 'ngSanitize', 'ngAnimate',
    // jhipster-needle-angularjs-add-module JHipster will add new module here
    'ui.bootstrap', 'ui.router',  'infinite-scroll', 'angular-loading-bar', 'ui.gravatar',
        'datatables', 'datatables.bootstrap', 'ngFileSaver', 'ui.select', 'angular-cache', 'hSweetAlert', 'ngAvatar'])

    .run(function ($rootScope, $location, $window, $http, $state,  Auth, Principal, ENV, VERSION) {
        $rootScope.ENV = ENV;
        $rootScope.VERSION = VERSION;
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }

        });

        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
            var titleKey = 'portal' ;

            // Remember previous state unless we've been redirected to login or we've just
            // reset the state memory after logout. If we're redirected to login, our
            // previousState is already set in the authExpiredInterceptor. If we're going
            // to login directly, we don't want to be sent to some previous state anyway
            if (toState.name !== 'login' && $rootScope.previousStateName) {
              $rootScope.previousStateName = fromState.name;
              $rootScope.previousStateParams = fromParams;
            }

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $window.document.title = titleKey;
        });

        $rootScope.back = function() {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };

    })
    .config(function ($compileProvider, ENV) {
        // Optimize load start with remove binding information inside the DOM element
        //TODO: change to prod for prod...need to do this automatigally
        $compileProvider.debugInfoEnabled(ENV === 'prod' ? false: true);
    })
    .config(function (CacheFactoryProvider) {
        angular.extend(CacheFactoryProvider.defaults, { maxAge: 15 * 60 * 1000 });
    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider,  httpRequestInterceptorCacheBusterProvider, AlertServiceProvider) {
        // uncomment below to make alerts look like toast
        //AlertServiceProvider.showAsToast(true);

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: 'scripts/components/navbar/navbar.html',
                    controller: 'NavbarController'
                },
                'leftmenu@': {
                    templateUrl: 'scripts/components/leftmenu/leftmenu.html',
                    controller: 'LeftmenuController'
                }
            },
            data: {
                authorities: ['ROLE_USER','ROLE_CUSTOMER']
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        })
        .state('bare', {
            'abstract': true,
            views: {
            },
            data: {
                authorities: ['ROLE_USER','ROLE_CUSTOMER']
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        });

        $httpProvider.interceptors.push('errorHandlerInterceptor');
        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');
        $httpProvider.interceptors.push('notificationInterceptor');
        // jhipster-needle-angularjs-add-interceptor JHipster will add new application interceptor here

    })
    // jhipster-needle-angularjs-add-config JHipster will add new application configuration here
    .config(['$urlMatcherFactoryProvider', function($urlMatcherFactory) {
        $urlMatcherFactory.type('boolean', {
            name : 'boolean',
            decode: function(val) { return val === true ? true : val === 'true' ? true : false; },
            encode: function(val) { return val ? 1 : 0; },
            equals: function(a, b) { return this.is(a) && a === b; },
            is: function(val) { return [true,false,0,1].indexOf(val) >= 0; },
            pattern: /bool|true|0|1/
        });
    }]).directive('validNumber', function() {
        return {
            require: '?ngModel',
            link: function(scope, element, attrs, ngModelCtrl) {
            	
            	
              if(!ngModelCtrl) {
                return; 
              }

              ngModelCtrl.$parsers.push(function(val) {
                if (angular.isUndefined(val)) {
                    var val = '';
                }
                
                 
                val=val+'';
                var clean = val.replace(/[^-0-9\.]/g, '');
                var negativeCheck = clean.split('-');
      			var decimalCheck = clean.split('.');
                if(!angular.isUndefined(negativeCheck[1])) {
                    negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
                    clean =negativeCheck[0] + '-' + negativeCheck[1];
                    if(negativeCheck[0].length > 0) {
                    	clean =negativeCheck[0];
                    }
                    
                }
                  
                if(!angular.isUndefined(decimalCheck[1])) {
                    decimalCheck[1] = decimalCheck[1].slice(0,3);
                    clean =decimalCheck[0] + '.' + decimalCheck[1];
                }

                if (val !== clean) {
                  ngModelCtrl.$setViewValue(clean);
                  ngModelCtrl.$render();
                }
                return clean;
              });

              element.bind('keypress', function(event) {
                if(event.keyCode === 32) {
                  event.preventDefault();
                }
              });
            }
          };
        });;

