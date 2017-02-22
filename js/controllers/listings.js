module.exports = {
    name: 'ListingsController',
    func: function ($scope, $state, ListingsService, MapService) {
        // county select box
        let count = 0;
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
            },
            {
                value: 'rutherford',
                label: 'Rutherford County'
            }
        ];
        //pagination maybe
        $scope.btnNums = (loc) => {
            //let count = 0;
            let max = 0;
            for (let i = 0; i < loc.length / 10; i++) {
                count++;
                btnCount.push(count);
            }
                return btnCount;
        };
        //button operations;
         let btnCount = [];
         let currentPage = 1;
         let startNum = 0;
         let endNum = 10;
         $scope.num = btnCount;
        $scope.showPage = (operator) => {
            if (operator === 'next' && currentPage < count) {
                startNum += 10;
                endNum += 10;
                currentPage++;
            } else if (operator === 'back' && startNum > 1) {
                startNum = startNum - 10;
                endNum =  endNum - 10;
                currentPage --;
                console.log('back back fourth and fourth')
            } else if (Number.isInteger(operator) === true ) {
                console.log('numbers for all');            
             startNum = (operator - 1) * 10;
             endNum = operator * 10;
             currentPage = operator;
            }
            // A bit wasteful because it redoes a bit AJAX request for each page load.
            ListingsService.getLoc(pickCounty).then(function (listings) {
                $scope.locations = listings.slice(startNum, endNum);
            });
        };
        //end of pagination

        //refreshes page when new county is selected
        $scope.changedValue = (item) => {
            $scope.displayCounty = item.label;
            pickCounty = item.value;
            ListingsService.getLoc(pickCounty).then(function (listings) {
                let allListings = listings
                $scope.locations = allListings.slice(startNum, endNum);
                $scope.btnNums(listings);
            });
        };
        //getting array of locations 
         initialCountyLoad = () => {
            let initCounty = pickCounty;
            ListingsService.getLoc(initCounty).then(function (listings) {
                let allListings = listings
                $scope.locations = allListings.slice(startNum, endNum);
                $scope.btnNums(listings);
            });
        };
        initialCountyLoad();
        //sends coordinates to map
        $scope.coord = (location) => {
            MapService.locate(location.latitude, location.longitude);
            $state.go('map', {
                pid: location.propertyId,
            }); // rerouting to a different view
        };
        
    },
};
