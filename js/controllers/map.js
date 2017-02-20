module.exports = {
    name: 'MapController',
    func: function ($scope, MapService) {

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

    MapService.kings_map;
    



        console.log('map controller');
        $scope.locate = () => {
            console.log('scope locate was clicked, activated in map controller');
            MapService.locate();
        }
    }

};