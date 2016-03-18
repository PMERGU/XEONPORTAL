'use strict';

describe('Controller Tests', function() {

    describe('PurchaseOrder Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPurchaseOrder, MockPoLine, MockParty, MockEmployee;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPurchaseOrder = jasmine.createSpy('MockPurchaseOrder');
            MockPoLine = jasmine.createSpy('MockPoLine');
            MockParty = jasmine.createSpy('MockParty');
            MockEmployee = jasmine.createSpy('MockEmployee');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'PurchaseOrder': MockPurchaseOrder,
                'PoLine': MockPoLine,
                'Party': MockParty,
                'Employee': MockEmployee
            };
            createController = function() {
                $injector.get('$controller')("PurchaseOrderDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'portalApp:purchaseOrderUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
