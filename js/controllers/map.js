
module.exports = {
    name: 'MapController',
    // $stateParams is how you pull values out of the route (URL)
    func: function ($scope, MapService, ListingsService, $stateParams) {
        // if pid is undefined, need to show everything
        // if pid is defined, only show that one
        let county = ListingsService.passCounty();
        let mainLat = 35.3579;
        let mainLong = -78.8836;
        
        if (county === null || county === undefined) {
            county = 'harnett';
            // mainLat = 35.3579;
            // mainLong = -78.8836;
        } else if (county === 'franklin') {
            console.log('franklin in the house');
        }
        console.log(`dispaly county: ${county}`);
        console.log(`this is somename`)
        console.log($stateParams.pid);

        const map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                lat: 35.3579,
                lng: -78.8836,
            },
            zoom: 11,
        });

        // // Show all markers
        ListingsService.getLoc(county).then(function (result) {
            for (let i = 0; i < result.length; i++) {
                // console.log(result[i].propertyId);
                if (result[i].propertyId === $stateParams.pid) {
                    const lat = parseFloat(result[i].latitude);
                    const long = parseFloat(result[i].longitude);

                    let marker = new google.maps.Marker({
                        position: {
                            lat: lat,
                            lng: long,
                        },
                        map: map,
                    });
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