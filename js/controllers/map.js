
module.exports = {
    name: 'MapController',
    // $stateParams is how you pull values out of the route (URL)
    func: function ($scope, MapService, ListingsService, $stateParams) {
        let county = ListingsService.passCounty();
        let mainLat = 35.3579;
        let mainLong = -78.8836;
        
        if (county === null || county === undefined) {
            county = 'harnett';
        } else {county === 'franklin'} 

        let map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                lat: 35.3579,
                lng: -78.8836,
            },
            zoom: 10,
            scrollwheel: false,
        });
        // show single map view
        ListingsService.getLoc(county).then((result) => {
            for (let i = 0; i < result.length; i++) {
                if (result[i].propertyId === $stateParams.pid) {
                    const lat = parseFloat(result[i].latitude);
                    const long = parseFloat(result[i].longitude);
                    let hybridMap = new google.maps.Map(document.querySelector('#map'), {
                        center: {
                            lat: lat,
                            lng: long,
                        },
                        zoom: 17,
                        mapTypeId: 'hybrid', 
                        scrollwheel: false,
                    });             
                    let marker = new google.maps.Marker({
                        position: {
                            lat: lat,
                            lng: long,
                        },
                        map: hybridMap,
                    });
                //shows all markers on road map 
                } else if ($stateParams.pid === '' || $stateParams.pid === undefined) {
                    const lat = parseFloat(result[i].latitude);
                    const long = parseFloat(result[i].longitude);

                    let marker = new google.maps.Marker({
                        position: {
                            lat: lat,
                            lng: long,
                        },
                        map: map,
                    });
                }
            }
        });
    },
};