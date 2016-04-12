function arrayHasThisEntity(array,object,field) {
    field = field || 'id';
    for(var i=0;i<array.length;i++) {
        if(array[i][field]===object[field]) {
            return true;
        }
    }
    return false;
}

function deleteDuplicates(array,idField) {
    idField = idField || 'id';

    var result = [];
    for(var i=0;i<array.length;i++) {
        var currentObject = array[i];
        if(!arrayHasThisEntity(result,currentObject,idField)) {
            result.push(currentObject);
        }
    }
    return result;
}

function initTestArticles(articles) {
    var countryRussia = new Country(1,'Россия');
    var countrySpain = new Country(2,'Испания');
    var countryNetherlands = new Country(3,'Нидерланды');
    var countryGermany = new Country(4,'Германия');
    var countryBelgium = new Country(5,'Бельгия');

    var placeVladimir = new Place(1,'Владимир', countryRussia,56.129042,40.40703);
    var placeKovrov = new Place(2,'Ковров', countryRussia,56.363628,41.31122);
    var placeAmsterdam = new Place(3,'Амстердам',countryNetherlands,52.373085,4.893276);
    var placeBarcelona = new Place(4,'Барселона',countrySpain,41.38558,2.16874);
    var placeDusseldorf = new Place(5,'Дюссельдорф',countryGermany,51.215626,6.776037);
    var placeAntwerpen = new Place(6,'Антверпен',countryBelgium,51.222085,4.397676);

    var someImgUrl = 'http://macos.ms/of/6/u/images/travel/lr201411_hu_budapest_1.jpg';
    articles.push(new Article(1,'Славный Владимир','http://google.ru',2014,someImgUrl,[placeVladimir],['Владимир','отчизна', "родина"]));
    articles.push(new Article(2,'Великолепный Ковров','http://google.ru',2014,someImgUrl,[placeKovrov],['Ковров','родина',"отчизна","город воинской славы"]));
    articles.push(new Article(3,'Красочная Барселона','http://google.ru',2016,someImgUrl,[placeBarcelona],["Барселона","Европа","Тепло"]));
    articles.push(new Article(4,'Отличный евротур','http://google.ru',2015,someImgUrl,[placeAmsterdam,placeAntwerpen,placeDusseldorf],["Европа","Дюссельдорф","Милота","Германия"]));
    articles.push(new Article(5,'Потрясный Амстердам','http://google.ru',2015,someImgUrl,[placeAmsterdam],["Амстердам","Европа","милота"]));
    articles.push(new Article(6,'Побольше Амстердама','http://google.ru',2016,someImgUrl,[placeAmsterdam],["Амстердам","Европа","милота"]));
    articles.push(new Article(7,'Ещё больше Амстердама!','http://google.ru',2016,someImgUrl,[placeAmsterdam],["Амстердам","Европа","милота"]));
    articles.push(new Article(8,'Амстердам повсюду!! Вот это да!!!','http://google.ru',2016,someImgUrl,[placeAmsterdam],["Амстердам","Европа","милота","опять"]));

}
