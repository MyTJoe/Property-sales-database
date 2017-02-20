module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        return {
            getLoc: (county) => {
                console.log(`getLoc func in ListingsService: ${county}`);
                return $http.get
                (`https://countycrasher.herokuapp.com/${county}`)
                    .then(function (response) {
                        console.log(`data ${response.data}`);
                        return response.data;
                    });
            },
        }
    },
};
