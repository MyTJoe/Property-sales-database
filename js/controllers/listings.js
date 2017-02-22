module.exports = {
    name: 'ListingsController',
    func: function ($scope, $state, ListingsService, MapService) {
        // county select box
        $scope.displayCounty = 'Harnett County';
        let pickCounty = 'harnett';
        $scope.countyList = [
            {
                value: 'franklin',
                label: 'Franklin County',
                lat: 36.0741,
                long: -78.2427
            },
            {
                value: 'harnett',
                label: 'Harnett County',
                lat: 35.3579,
                long: -78.8836
            },
            {
                value: 'lee',
                label: 'Lee County',
                lat: 35.4694,
                long: -79.1549
            },
            {
                value: 'rutherford',
                label: 'Rutherford County',
                lat: 35.4259,
                long: -81.9098
            }
        ];

       // MapService.allCounties($scope.countyList);
        //refreshes page when new county is selected
        $scope.changedValue = (item) => {
            $scope.displayCounty = item.label;
            pickCounty = item.value;
            ListingsService.getLoc(pickCounty).then(function (listings) {
                $scope.locations = listings;
            });
        };

        //getting array of locations 
         initialCountyLoad = () => {
            let initCounty = pickCounty;
            ListingsService.getLoc(initCounty).then(function (listings) {
                $scope.locations = listings;
            });
        };

        initialCountyLoad();

        $scope.coord = (location) => {
            MapService.locate(location.latitude, location.longitude);
            $state.go('map', {
                pid: location.propertyId,
            }); // rerouting to a different view
        }





    },
};
