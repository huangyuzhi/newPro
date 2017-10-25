var map = new ol.Map({
    target: 'allmap',
    layers: [
        new ol.layer.Tile({
            source: new ol.source.OSM()
        })
    ],
    view: new ol.View({
        center : [106.826838, 26.663088],
        projection : 'EPSG:4326',
        zoom:  14,
        maxZoom: 19
    })
});