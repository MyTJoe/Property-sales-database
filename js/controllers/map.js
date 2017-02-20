module.exports = {
    name: 'MapController',
    func: function ($scope, MapService) {

    let kings_map;
    function initMap(lati, long) {
        kings_map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                 lat: lati,
                 lng: long,
            },
            zoom: 8
        });

        let marker = new google.maps.Marker({
            position: {
                // lat: -34.397,
                // lng: 150.644,
            },
            map: kings_map
        });
    };

    initMap(lati, long);
    



        console.log('map controller');
        $scope.locate = () => {
            console.log('scope locate was clicked, activated in map controller');
            MapService.locate();
        }
    }

};