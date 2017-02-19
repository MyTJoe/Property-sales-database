module.exports = {
    name: 'ListingsController',
    func: function ($scope, $state, ListingsService, MapService) {
        console.log('listingcontoller activated');
        ListingsService.getLoc().then(function (listings) {
            $scope.locations = listings;
        });
        $scope.coord = (lat, lng) => {
            MapService.locate(lat, lng);
            //$state.go('map');
            console.log(`coordFunc:${lat}, ${lng}`);
        }
    },
};
