module.exports = {
    name: 'MapService', 
    func:  () => {
        let coordinates;
        let kings_map;
    function initMap() {
        let coord = MapService.coordinates;
        console.log(`initMap coord= ${coord}`);
        kings_map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                 lat: -35.667,
                 lng: 145.667,
            },
            zoom: 10
        });

        // let marker = new google.maps.Marker({
        //     position: {
        //         // lat: -34.397,
        //         // lng: 150.644,
        //     },
        //     map: kings_map
        // });
    };

        return {
            locate: (lat, lng) => {
                coordinates = [lat, lng]
                console.log(`my map was clicked ${coordinates[0]}`);
               return kings_map;
            }
        }
    },
};



