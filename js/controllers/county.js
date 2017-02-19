module.exports = {
    name: 'CountyController',
    func: function ($scope, ListingsService) {
        console.log('county controller ');
         $scope.selectCounty = (county) => {
             ListingsService.getLoc(county)
             console.log(county);
         }
    },
};