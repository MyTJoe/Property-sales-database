module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        // const locations = [];
        // temp get request for testing
        // $http.get('/fakerequest.json')
        // // $http.get('https://still-retreat-79338.herokuapp.com/address.json')
        // .then(function (response) {
        //     angular.copy(response.data, locations);
        //     console.log('then function');
        //     console.log(response.data);
        // });
        return {

            getLoc: (county) => {
                
                console.log(`getLoc func: ${county}`);
                // console.log(locations);
                // return locations;
                //return $http.get('/fakerequest.json')
<<<<<<< HEAD
                return $http.get(`https://still-retreat-79338.herokuapp.com/${county}`)
=======
                return $http.get('https://still-retreat-79338.herokuapp.com/franklin')
>>>>>>> cdeac502e66d6040a72da7ea97fa9fd93a4eedcb
                    .then(function (response) {
                        // angular.copy(response.data, locations);
                        console.log('then function');
                        //console.log(response.data);
                        return response.data;
                    });
            },
        }
    },
};
