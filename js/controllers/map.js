
module.exports = {
    name: 'MapController',
    // $stateParams is how you pull values out of the route (URL)
    func: function ($scope, MapService, ListingsService, $stateParams) {
        // if pid is undefined, need to show everything
        // if pid is defined, only show that one
        let county = ListingsService.passCounty();
        let mainLat = 35.3579;
        let mainLong = -78.8836;
        


        // let allCounties = MapService.allCounties();
        // console.log(`this is all counties: ${allCounties}`);



//testing county changes, lat and long changes with county
// only harnett has lat and long
        if (county === null || county === undefined) {
            county = 'harnett';
             mainLat = 35.3579;
             mainLong = -78.8836;
        } else if (county === 'franklin') {
            mainLat = 36.0741;
            mainLong = -78.2427;
        }

//end of county testing
        let map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                lat: mainLat,
                lng: mainLong,
            },
            zoom: 10,
        });

        // // Show all markers
        ListingsService.getLoc(county).then(function (result) {
            for (let i = 0; i < result.length; i++) {
                // console.log(result[i].propertyId);
                if (result[i].propertyId === $stateParams.pid) {
                    const lat = parseFloat(result[i].latitude);
                    const long = parseFloat(result[i].longitude);

                     singlemap = new google.maps.Map(document.querySelector('#map'), {
                        center: {
                            lat: lat,
                            lng: long,
                                },
                            zoom: 15,
                            mapTypeId: 'satellite'
                        });

                    let marker = new google.maps.Marker({
                        position: {
                            lat: lat,
                            lng: long,
                        },
                        map: singlemap,
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