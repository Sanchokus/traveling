var URL_PREFIX = '../';
var TEMPLATES_PREFIX = URL_PREFIX + "static/templates/";

angular.module('travelingApp', ['ngMaterial', 'md.data.table'])
    .controller('IndexTravelingCtrl', function($scope, $mdDialog, api, appMessages) {
        mainMap = new Map('travel-map');

        $scope.articles = [];
        $scope.currentCardInfo = undefined; //for showing concrete articles

        var originatorEv;
        this.openMenu = function($mdOpenMenu, ev) {
            originatorEv = ev;
            $mdOpenMenu(ev);
        };

        $scope.getYears = function() {
            var years = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var currentYear = $scope.articles[i].year;
                if(years.indexOf(currentYear) === -1) {
                    years.push(currentYear);
                }
            }

            years.sort();

            return years;
        }
        $scope.getPlaces = function() {
            var places = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var article = $scope.articles[i];
                for(var j=0;j<article.places.length;j++) {
                    var place = article.places[j];
                    if (!arrayHasThisEntity(places, place)) {
                        places.push(place);
                    }
                }
            }
            places.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            return places;
        };
        $scope.getCountries = function() {
            var countries = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var article = $scope.articles[i];
                for(var j=0;j<article.places.length;j++) {
                    var country = article.places[j].country;
                    if (!arrayHasThisEntity(countries, country)) {
                        countries.push(country);
                    }
                }
            }
            countries.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            return countries;
        };
        $scope.getTags = function() {
            var result = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var article = $scope.articles[i];
                for(var j=0;j<article.tags.length;j++) {
                    var tag = article.tags[j];
                    if(result.indexOf(tag) === -1) {
                        result.push(tag);
                    }
                }
            }
            result.sort();
            return result;
        };
        $scope.getMarkerInfos = function() {
            var placeForArticlesMap = {};
            var allPlaces = $scope.getPlaces();
            for(var i=0;i<allPlaces.length;i++) {
                var currentPlace = allPlaces[i];
                placeForArticlesMap[''+currentPlace.id] = new MarkerInfo(currentPlace,[]);
            }
            for(var i=0;i<$scope.articles.length;i++) {
                var currentArticle = $scope.articles[i];
                for(var j=0;j<currentArticle.places.length;j++) {
                    var placeId = currentArticle.places[j].id;
                    placeForArticlesMap[''+placeId].articles.push(currentArticle);
                }
            }

            var result = [];
            for(var i=0;i<allPlaces.length;i++) {
                result.push(placeForArticlesMap[''+allPlaces[i].id]);
            }
            return result;
        };
        $scope.initMarkers = function() {
            var markerInfos = $scope.getMarkerInfos();

            for(var i=0;i<markerInfos.length;i++) {
                var markerInfo = markerInfos[i];
                var marker = L.marker(
                    [markerInfo.place.x,markerInfo.place.y],
                    {title: markerInfo.place.name}
                );
                marker.additionalInfo = markerInfo;
                marker.on('click', function(e){
                    var additionalInfo = e.target.additionalInfo;
                    $scope.openArticlesDialog(e, additionalInfo);
                });
                marker.addTo(mainMap.map);
            }
        };
        $scope.getInfoByCountry = function(country) {
            var articles = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var currentArticle = $scope.articles[i];
                for(var j=0;j<currentArticle.places.length;j++) {
                    var currentCountry = currentArticle.places[j].country;
                    if(currentCountry.id === country.id) {
                        articles.push(currentArticle);
                    }
                }
            }
            deleteDuplicates(articles);
            articles.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            return new CardInfo(country.name, articles);
        };
        $scope.getInfoByYear = function(year) {
            var articles = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var currentArticle = $scope.articles[i];
                if(currentArticle.year === year) {
                    articles.push(currentArticle);
                }
            }
            deleteDuplicates(articles);
            articles.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            return new CardInfo(year + 'год', articles);
        };
        $scope.getInfoByTag = function(tag) {
            var articles = [];
            for(var i=0;i<$scope.articles.length;i++) {
                var currentArticle = $scope.articles[i];
                for(var j=0;j<currentArticle.tags.length;j++) {
                    var currentTag = currentArticle.tags[j];
                    if(currentTag === tag) {
                        articles.push(currentArticle);
                    }
                }
            }
            deleteDuplicates(articles);
            articles.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            return new CardInfo(tag, articles);
        };

        $scope.getTagMatches = function(searchText) {
            var allTags = $scope.getTags();

            var result = [];
            for(var i=0;i<allTags.length;i++) {
                var tag = allTags[i];
                if(tag.toLowerCase().indexOf(searchText.toLowerCase()) !== -1) {
                    result.push(tag);
                }
            }
            return result;
        };

        $scope.showArticlesByCountry = function(country, $event) {
            var info = $scope.getInfoByCountry(country);
            $scope.openArticlesDialog($event, info);
        };
        $scope.showArticlesByYear = function(year, $event) {
            var info = $scope.getInfoByYear(year);
            $scope.openArticlesDialog($event, info);
        };
        $scope.showArticlesByTag = function(tag, $event) {
            var info = $scope.getInfoByTag(tag);
            $scope.openArticlesDialog($event, info);
        };
        $scope.openArticlesDialog = function($event, info) {
            $scope.currentCardInfo = info;
            $mdDialog.show({
                templateUrl: '../static/templates/articles.tmpl.html',
                targetEvent: $event,
                clickOutsideToClose:true,
                scope: $scope,
                preserveScope: true
            });
        };

        $scope.showSearchInput = function($event) {
            $mdDialog.show({
                templateUrl: '../static/templates/search.tmpl.html',
                targetEvent: $event,
                clickOutsideToClose:true,
                scope: $scope,
                preserveScope: true
            });
        };
        $scope.showArticlesFromTagDialog = function(tag, $event) {
            $mdDialog.hide(); //closes search dialog
            $scope.showArticlesByTag(tag, $event);
        };

        // initTestArticles($scope.articles);
        api.read(
            'article',
            function(response) {
                $scope.articles = response.data;
                $scope.initMarkers();
            },
            function(errorResponse) {
                appMessages.error(errorResponse.data);
            }
        )

        // $scope.initMarkers();
    })
    .controller('AdminTravelingCtrl', function() {
    });