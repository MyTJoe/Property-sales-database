
module.exports = {
    name: 'MapController',
    // $stateParams is how you pull values out of the route (URL)
    func: function ($scope, MapService, ListingsService, $stateParams) {
        // if pid is undefined, need to show everything
        // if pid is defined, only show that one
        console.log($stateParams.pid);

        const map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                lat: 35.3579,
                lng: -78.8836,
            },
            zoom: 10,
        });

        // // Show all markers
        ListingsService.getLoc('harnett').then(function (result) {
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
                } else if ($stateParams.pid === '') {
                    console.log('show it all');
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