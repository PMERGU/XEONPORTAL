'use strict';

angular.module('portalApp')
    .service('CachedOrders', function (CustomerOrders, CacheFactory, $q, $log) {
        $log.debug("[CachedOrders] : checking if cache exists");

        if (!CacheFactory.get('CachedOrders')) {
            $log.debug("[CachedOrders] : does not exists....creating");
            CacheFactory.createCache('CachedOrders', {
                deleteOnExpire: 'none',
                recycleFreq: 60000,
                storageMode: 'localStorage'
            });
        }

        var cachedOrders = CacheFactory.get('CachedOrders');
        $log.debug(cachedOrders.info());

        return {
            getOrders: function(key, sapId, from, to, force) {
                $log.debug(key + ' - ' + sapId + ' - ' + force);
                key = key + '-' + sapId;
                if (!force && cachedOrders.get(key)) {
                    $log.debug("Found key in cache");
                    return cachedOrders.get(key);
                }

                var promise = CustomerOrders.get({
                    id: sapId,
                    from: from,
                    to: to,
                }).$promise.then(function (data) {
                    cachedOrders.put(key, $q.when(data));
                    return data;
                });

                cachedOrders.put(key, promise);
                return promise;
            },
            getOrderGroup: function(key, sapId, dbeln){
                $log.debug(key + ' - ' + dbeln);
                key = key + '-' + sapId;
                var group = [];
                cachedOrders.get(key).then(function(orders){
                    $.each(orders, function(idx, order){
                        if(order.dbeln === dbeln){
                            group.push(order);
                        }
                    });
                });
                return group;
            },
            removeAll: function(){
                cachedOrders.removeAll();
            }
        };
    });
