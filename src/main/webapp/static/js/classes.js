
function Country(id, name) {
    this.id = id;
    this.name = name;
}

function Place(id, name, country, x, y) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.x = x;
    this.y = y;
}

function Article(id, name, url, year, photo, places, tags) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.year = year;
    this.photo = photo;
    this.places = places;
    this.tags = tags;
}

function CardInfo(title, articles) {
    this.title = title;
    this.articles = articles;
}

function Map(mapId) {

    this.initMap = function() {
        var map = L.map(mapId).setView([30.6995576, 81.0782663], 3);
        L.tileLayer('http://server.arcgisonline.com/ArcGIS/rest/services/NatGeo_World_Map/MapServer/tile/{z}/{y}/{x}', {
                attribution: 'Tiles &copy; Esri &mdash; National Geographic, Esri, DeLorme, NAVTEQ, UNEP-WCMC, USGS, NASA, ESA, METI, NRCAN, GEBCO, NOAA, iPC',
                maxZoom: 16
            })
            .addTo(map);

        return map;
    };
    this.addClickEvent = function(event) {
        this.map.on('click', event);
    };

    this.id = mapId;
    this.map = this.initMap();
}

function MarkerInfo(place, articles) {
    this.title = place.name;
    this.articles = articles;
    this.place = place;
}
