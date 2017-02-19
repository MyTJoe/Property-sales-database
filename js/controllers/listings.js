module.exports = {
    name: 'ListingsController',
    func: function ($scope, $state, ListingsService, MapService) {
        // county select box
        $scope.displayCounty = 'Harnett County';
        let pickCounty = 'harnett';
        $scope.countyList = [
            {
                value: 'franklin',
                label: 'Franklin County'
            },
            {
                value: 'harnett',
                label: 'Harnett County'
            },
            {
                value: 'lee',
                label: 'Lee County'
            }
        ];

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

        // map stuff that's currently not working
        $scope.coord = (lat, lng) => {
            MapService.locate(lat, lng);
            //$state.go('map');
        }
    },
};
