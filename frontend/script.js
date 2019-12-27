
window.initialize = function(
         _container,
         _style,
         _zoom,
         _pitch,
         _bearing,
         _antialias) {

        mapboxgl.accessToken = 'pk.eyJ1IjoiYXJrYW50b3NkZmMxIiwiYSI6ImNrM2ZiY2Z2dzAweTQzb280ZzlsNDRxZDgifQ.nJ1w2oLWeQ2Aj4vRV4pX0w';

        var map = new mapboxgl.Map({
            container: _container,
            style: _style,
            zoom: _zoom,
            pitch: _pitch,
            bearing: _bearing,
            antialias: _antialias,
            center: [-95.355728, 29.748186]
        });
        
        window.map = map;
    }

window.addNavigationControl = function() {
    window.map.addControl(new mapboxgl.NavigationControl());
}

window.fly = function(lng, lat) {
    window.map.flyTo({
        center: [lng, lat],
        essential: true
    });
}

window.showMarkerDraggable = function() {
    var initialLongitude = -95.355728;
    var initialLatitude = 29.748186;
    var marker = new mapboxgl.Marker({
        draggable: true
    }).setLngLat([initialLongitude, initialLatitude]).addTo(window.map);

    var longitudeElement = document.getElementById('odre-longitude');
    longitudeElement.value = initialLongitude;

    var latitudeElement = document.getElementById('odre-latitude')
    latitudeElement.value = initialLatitude;
    
    function onDragEnd() {
        var lngLat = marker.getLngLat();
        longitudeElement.value = lngLat.lng;
        latitudeElement.value = lngLat.lat;
    }
    marker.on('dragend', onDragEnd);
}

window.addMarker = function(lng, lat) {
    new mapboxgl.Marker({
        draggable: false
    }).setLngLat([lng, lat]).addTo(window.map);
}