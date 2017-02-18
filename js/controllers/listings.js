module.exports = {
    name: 'ListingsController',
    func: function ($scope, ListingsService) {
        console.log('listingcontoller activated');
        ListingsService.getLoc().then(function (listings) {
            $scope.locations = listings;
        });
        $scope.coord = (lat, lng) => {
            console.log(`coordFunc:${lat}, ${lng}`);
        }
    },
};
