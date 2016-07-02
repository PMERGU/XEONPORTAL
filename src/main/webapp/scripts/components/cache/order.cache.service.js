'use strict';

angular.module('portalApp')
    .factory('CachedOrders', function (CustomerOrders, $cacheFactory, $q, $log) {
        var cache = $cacheFactory('CustomerOrdersCache', {capacity: 1000});
        return {
            getOrders: function(key, sapId, from, to, force) {
                $log.debug(key + ' - ' + sapId + ' - ' + force);
                if (!force && cache.get(key)) {
                    return cache.get(key);
                }

                var promise = CustomerOrders.get({
                    id: 213,
                    from: from,
                    to: to,
                }).$promise.then(function (data) {
                    cache.put(key, $q.when(data));
                    return data;
                });

                cache.put(key, promise);
                return promise;
            },
            getOrderGroup: function(key, dbeln){
                $log.debug(key + ' - ' + dbeln);
                var group = [];
                cache.get(key).then(function(orders){
                    $.each(orders, function(idx, order){
                        if(order.dbeln === dbeln){
                            group.push(order);
                        }
                    });
                });
                return group;
            },
            removeAll: function(){
                cache.removeAll();
            }
        };
    });
