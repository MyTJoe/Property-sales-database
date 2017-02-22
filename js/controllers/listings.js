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
            },
            {
                value: 'rutherford',
                label: 'Rutherford County'
            }
        ];

        //pagination maybe
        $scope.btnNums =  (loc) => {
            let count = 0;
            console.log(`work already ${loc}`);
            for (let i = 0; i < loc.length / 10; i++) {
                count++;
                btnCount.push(count);
                console.log(`some num here: ${btnCount}`);
            }
                return btnCount;
        };
         let btnCount = [];
         $scope.num = btnCount;
         let currentPage = 1;
         let sNum = 0;
         let eNum = 10;

        $scope.showPage = (operator) => {
            
            let startNum = (operator - 1) * 10;
            let endNum = operator * 10;
            console.log(`showPage func. Button ${operator} was pressed: ${startNum}, ${endNum}`);
            sNum = startNum;
            eNum = endNum;

            // A bit wasteful because it redoes a bit AJAX request for each page load.
            ListingsService.getLoc(pickCounty).then(function (listings) {
                //$scope.locations = listings;
                $scope.locations = listings.slice(sNum, eNum);
            });
        };
        //end of pagination

        //refreshes page when new county is selected
        $scope.changedValue = (item) => {
            $scope.displayCounty = item.label;
            pickCounty = item.value;
            ListingsService.getLoc(pickCounty).then(function (listings) {
                //$scope.locations = listings;
            });
        };
        //getting array of locations 
         initialCountyLoad = () => {
            let initCounty = pickCounty;
            ListingsService.getLoc(initCounty).then(function (listings) {
                let allListings = listings
                $scope.locations = allListings.slice(sNum, eNum);
                console.log(`this is all list: ${allListings}`)
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
