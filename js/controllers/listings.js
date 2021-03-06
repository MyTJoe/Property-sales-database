module.exports = {
    name: 'ListingsController',
    func: function ($scope, $state, ListingsService, MapService) {
        // county select box
        let count = 0;
        let btnCount = [];
        let currentPage = 1;
        let startNum = 0;
        let endNum = 10;
        let pickCounty = 'franklin';
        window.location.hash = '#top';
        $scope.num = btnCount;
        $scope.displayCounty = 'Franklin County';
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
            //pagination
            $scope.btnNums = (loc) => {
            count = 0;
            $scope.num.length = 0;
            for (let i = 0; i < loc.length / 10; i++) {
                count++;
                btnCount.push(count);
            }
                return btnCount;
        };
        $scope.showPage = (operator) => {
            if (operator === 'next' && currentPage < count) {
                startNum += 10;
                endNum += 10;
                currentPage++;
                window.location.hash = '#top';
            } else if (operator === 'back' && startNum > 1) {
                startNum = startNum - 10;
                endNum =  endNum - 10;
                currentPage --;
                window.location.hash = '#top';
            } else if (Number.isInteger(operator) === true ) {
                startNum = (operator - 1) * 10;
                endNum = operator * 10;
                currentPage = operator;
                window.location.hash = '#top';
            }
            //need to change later
            ListingsService.getLoc(pickCounty).then((listings) => {
                $scope.locations = listings.slice(startNum, endNum);
            });
        };

        //refreshes page when new county is selected
        $scope.changedValue = (item) => {
            $scope.displayCounty = item.label;
            pickCounty = item.value;
            ListingsService.getLoc(pickCounty).then((listings) => {
                let allListings = listings
                $scope.locations = allListings.slice(startNum, endNum);
                $scope.btnNums(listings);
            });
        };
        //getting array of locations
         initialCountyLoad = () => {
            let initCounty = pickCounty;
            ListingsService.getLoc(initCounty).then((listings) => {
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
            });
        };

    },
};
