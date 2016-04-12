angular.module('travelingApp')
    .directive('adminToolbar', function() {
        return {
            templateUrl: TEMPLATES_PREFIX + "admin/toolbar.html"
        };
    })
    .directive('adminCountriesTab', function() {
        return {
            templateUrl: TEMPLATES_PREFIX + "admin/tabs/countries.html"
        };
    })
    .directive('adminPlacesTab', function() {
        return {
            templateUrl: TEMPLATES_PREFIX + "admin/tabs/places.html"
        };
    })
    .directive('adminArticlesTab', function() {
        return {
            templateUrl: TEMPLATES_PREFIX + "admin/tabs/articles.html"
        };
    })
    .directive('countriesTable', function(api, $log, $mdDialog, $window, entityActions, appUtils) {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/countries-table.html",
            scope: {},
            link: function($scope, element, attrs) {
                $scope.table = {
                    name: 'country',
                    data: [],
                    selectedRows: [],
                    order: 'name'
                };
                $scope.toolbar = {
                    add: function() {
                        $scope.entity = appUtils.createNewEntity($scope.table.name);
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/new-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    edit: function() {
                        $scope.entity = _.cloneDeep($scope.table.selectedRows[0]);
                        $scope.table.selectedRows = [];
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/edit-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    delete: function() {
                        var confirmDialog = $mdDialog.confirm()
                            .title('Удаление')
                            .textContent('Выбранные строки будут удалены. Продолжить?')
                            .ok('УДАЛИТЬ')
                            .cancel('Отмена');
                        $mdDialog.show(confirmDialog).then(
                            function() {
                                $mdDialog.hide();
                                entityActions.delete($scope.table, _.cloneDeep($scope.table.selectedRows));
                                $scope.table.selectedRows = [];
                            }
                        );
                    }
                };
                $scope.dialogActions = {
                    addNew: function(entity) {
                        $mdDialog.hide();
                        entityActions.addNew($scope.table, entity);
                    },
                    edit: function(entity) {
                        $mdDialog.hide(entity);
                        entityActions.edit($scope.table, entity);
                    },
                    close: function() {
                        $mdDialog.hide();
                    }
                };
                $scope.allEntities = {
                    country: [],
                    place: [],
                    article: []
                };

                entityActions.refresh($scope.table);
                entityActions.refreshAllEntitiesField($scope.allEntities);
            }
        };
    })
    .directive('placesTable', function(api, $log, $mdDialog, $window, entityActions, appUtils) {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/places-table.html",
            scope: {},
            link: function($scope, element, attrs) {
                $scope.table = {
                    name: 'place',
                    data: [],
                    selectedRows: [],
                    order: 'name'
                };
                $scope.toolbar = {
                    add: function() {
                        $scope.entity = appUtils.createNewEntity($scope.table.name);
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/new-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    edit: function() {
                        $scope.entity = _.cloneDeep($scope.table.selectedRows[0]);
                        $scope.table.selectedRows = [];
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/edit-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    delete: function() {
                        var confirmDialog = $mdDialog.confirm()
                            .title('Удаление')
                            .textContent('Выбранные строки будут удалены. Продолжить?')
                            .ok('УДАЛИТЬ')
                            .cancel('Отмена');
                        $mdDialog.show(confirmDialog).then(
                            function() {
                                $mdDialog.hide();
                                entityActions.delete($scope.table, _.cloneDeep($scope.table.selectedRows));
                                $scope.table.selectedRows = [];
                            }
                        );
                    }
                };
                $scope.dialogActions = {
                    addNew: function(entity) {
                        $mdDialog.hide();
                        entityActions.addNew($scope.table, entity);
                    },
                    edit: function(entity) {
                        $mdDialog.hide(entity);
                        entityActions.edit($scope.table, entity);
                    },
                    close: function() {
                        $mdDialog.hide();
                    }
                };
                $scope.allEntities = {
                    country: [],
                    place: [],
                    article: []
                };

                $scope.openAxisEditWindow = function() {
                    var axisWindow = $window.open(URL_PREFIX + 'axisEdit', 'axisEdit', 'width=500,height=500');
                };

                entityActions.refresh($scope.table);
                entityActions.refreshAllEntitiesField($scope.allEntities);
            }
        };
    })
    .directive('articlesTable', function(api, $log, $mdDialog, entityActions, appUtils) {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/articles-table.html",
            scope: {},
            link: function($scope, element, attrs) {
                $scope.table = {
                    name: 'article',
                    data: [],
                    selectedRows: [],
                    order: 'name'
                };
                $scope.toolbar = {
                    add: function() {
                        $scope.entity = appUtils.createNewEntity($scope.table.name);
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/new-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    edit: function() {
                        $scope.entity = _.cloneDeep($scope.table.selectedRows[0]);
                        $scope.table.selectedRows = [];
                        $mdDialog.show({
                            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/edit-'+$scope.table.name+'.html',
                            clickOutsideToClose:true,
                            scope: $scope,
                            preserveScope: true
                        });
                    },
                    delete: function() {
                        var confirmDialog = $mdDialog.confirm()
                            .title('Удаление')
                            .textContent('Выбранные строки будут удалены. Продолжить?')
                            .ok('УДАЛИТЬ')
                            .cancel('Отмена');
                        $mdDialog.show(confirmDialog).then(
                            function() {
                                $mdDialog.hide();
                                entityActions.delete($scope.table, _.cloneDeep($scope.table.selectedRows));
                                $scope.table.selectedRows = [];
                            }
                        );
                    }
                };
                $scope.dialogActions = {
                    addNew: function(entity) {
                        $mdDialog.hide();
                        entityActions.addNew($scope.table, entity);
                    },
                    edit: function(entity) {
                        $mdDialog.hide(entity);
                        entityActions.edit($scope.table, entity);
                    },
                    close: function() {
                        $mdDialog.hide();
                    }
                };
                $scope.allEntities = {
                    country: [],
                    place: [],
                    article: []
                };
                $scope._ = _;
                entityActions.refresh($scope.table);
                entityActions.refreshAllEntitiesField($scope.allEntities);
            }
        };
    })
    .directive('entityToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/components/toolbar.html"
        };
    })
    .directive('countriesTableData', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/components/countries-table-data.html"
        };
    })
    .directive('placesTableData', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/components/places-table-data.html"
        };
    })
    .directive('articlesTableData', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/tables/components/articles-table-data.html"
        };
    })
    .directive('newCountryDialog', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/dialogs/new-country.html"
        };
    })
    .directive('newPlaceDialog', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/dialogs/new-place.html"
        };
    })
    .directive('newArticleDialog', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/dialogs/new-article.html"
        };
    })
    .directive('dialogCountryProperties', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/components/entity-properties/country.html'
        };
    })
    .directive('dialogPlaceProperties', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/components/entity-properties/place.html'
        };
    })
    .directive('dialogArticleProperties', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/components/entity-properties/article.html'
        };
    })
    .directive('newEntityActionToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/components/action-bar/new.html'
        };
    })
    .directive('editEntityActionToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + 'admin/dialogs/components/action-bar/edit.html'
        };
    })
    .directive('entityDialogTitleToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: TEMPLATES_PREFIX + "admin/dialogs/components/title-toolbar.html",
            transclude: true
        };
    });