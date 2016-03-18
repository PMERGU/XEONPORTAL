'use strict';

describe('Controller Tests', function() {

    describe('PoLine Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPoLine, MockPurchaseOrder;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPoLine = jasmine.createSpy('MockPoLine');
            MockPurchaseOrder = jasmine.createSpy('MockPurchaseOrder');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'PoLine': MockPoLine,
                'PurchaseOrder': MockPurchaseOrder
            };
            createController = function() {
                $injector.get('$controller')("PoLineDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'portalApp:poLineUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
