module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        let thisCounty = null;
        return {
            getLoc: (county) => {
                thisCounty = county;
                return $http.get
                (`https://countylink.herokuapp.com/${county}`)
                    .then(function (response) {
                        return response.data;
                    });
            },
            passCounty: () => {
                return thisCounty;
            },
        }
    },
};
