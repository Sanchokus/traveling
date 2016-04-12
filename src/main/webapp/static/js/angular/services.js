angular.module('travelingApp')
    .factory('api', function($http, $q) {
        return {
            create: function(entityName, entity, successCallback, errorCallback) {
                $http.post(URL_PREFIX + entityName, entity)
                    .then(successCallback, errorCallback);
            },
            read: function(entityName, successCallback, errorCallback) {
                $http.get(URL_PREFIX + entityName)
                    .then(successCallback, errorCallback);
            },
            update: function(entityName, entity, successCallback, errorCallback) {
                $http.put(URL_PREFIX + entityName, entity)
                    .then(successCallback, errorCallback);
            },
            delete: function(entityName, entities, successCallback, errorCallback) {
                var allDeleteTasks = [];
                for(var i=0; i < entities.length; i++) {
                    var id = entities[i].id;
                    var url = URL_PREFIX + entityName + '/' + id;
                    allDeleteTasks.push($http.delete(url));
                }
                $q.all(allDeleteTasks).then(successCallback);
                //TODO Need to implement error callback
            }
        };
    })
    .factory('appMessages', function($mdDialog) {
        var closeAllDialogs = function() {
            $mdDialog.hide();
        };
        return {
            error: function(errorText) {
                closeAllDialogs();
                $mdDialog.show(
                    $mdDialog.alert()
                        .clickOutsideToClose(true)
                        .title('Ошибка!')
                        .textContent(errorText)
                        .ok('ОК')
                );
            }
        };
    })
    .factory('entityActions', function($log, api, appMessages) {
        var thisService = {
            refresh: function(table) {
                api.read(
                    table.name,
                    function(response) {
                        table.data = response.data;
                    },
                    function(errorResponse) {
                        appMessages.error(errorResponse.data);
                    }
                );
            },
            addNew: function(table, entity) {
                api.create(
                    table.name,
                    entity,
                    function(response) {
                        thisService.refresh(table);
                    },
                    function(errorResponse) {
                        appMessages.error(errorResponse.data);
                    }
                );
            },
            edit: function(table, entity) {
                api.update(
                    table.name,
                    entity,
                    function(response) {
                        thisService.refresh(table);
                    },
                    function(errorResponse) {
                        appMessages.error(errorResponse.data);
                    }
                );
            },
            delete: function(table, entities) {
                api.delete(
                    table.name,
                    entities,
                    function(response) {
                        thisService.refresh(table);
                    },
                    function(errorResponse) {
                        appMessages.error(errorResponse.data);
                    }
                );
            },
            refreshAllEntitiesField: function(allEntities) {
                api.read(
                    'country',
                    function(resp) {
                        allEntities.country = resp.data
                    },
                    function(err) {
                        $log.log(err.data);
                    });
                api.read(
                    'place',
                    function(resp) {
                        allEntities.place = resp.data
                    },
                    function(err) {

                    });
                api.read(
                    'article',
                    function(resp) {
                        allEntities.article = resp.data
                    },
                    function(err) {

                    });
            }
        };
        return thisService;
    })
    .factory('appUtils', function() {
        return {
            createNewEntity: function(entityName) {
                if(entityName.toLowerCase() === 'country') {
                    return new Country();
                }
                else if(entityName.toLowerCase() === 'place') {
                    return new Place();
                }
                else if(entityName.toLowerCase() === 'article') {
                    return new Article();
                }
                else
                    return undefined;
            }
        };
    });