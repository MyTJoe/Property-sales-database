module.exports = {
    name: 'CountyController',
    func: function ($scope, $state, ListingsService) {
        console.log('county controller ');
         $scope.selectCounty = (county) => {
             ListingsService.getLoc(county);
             //$state.go('listings');

         }
    },
};