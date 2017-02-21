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

    }

};