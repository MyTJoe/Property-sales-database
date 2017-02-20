module.exports = {
    name: 'MapController',
    func: function ($scope, MapService, ListingsService) {

    const map = new google.maps.Map(document.querySelector('#map'), {
        center: {
            lat: 35.3579,
            lng: -78.8836,
        },
        zoom: 10,
    });

    ListingsService.getLoc('harnett').then(function (result) {
        for (let i = 0; i < result.length; i++) {
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
    });


    // let kings_map;
    // function initMap() {
    //     let coord = MapService.coordinates;
    //     console.log(`initMap coord= ${coord}`);
    //     kings_map = new google.maps.Map(document.querySelector('#map'), {
    //         center: {
    //              lat: -35.667,
    //              lng: 145.667,
    //         },
    //         zoom: 10
    //     });

    //     // let marker = new google.maps.Marker({
    //     //     position: {
    //     //         // lat: -34.397,
    //     //         // lng: 150.644,
    //     //     },
    //     //     map: kings_map
    //     // });
    // };

    // initMap();
    



    //     console.log('map controller');
    //     $scope.locate = () => {
    //         console.log('scope locate was clicked, activated in map controller');
    //         MapService.locate();
    //     }
    }

};