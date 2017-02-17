module.exports = {
    name: 'ListingsController',
    func: function ($scope, ListingsService) {
        //$scope.locations = ListingsService.getLoc();
        // let listArr = ListingsService.getLoc();
        ListingsService.getLoc().then(function (listings) {
            $scope.locations = listings;
            
            // function for number of buttons
            // $scope.btnNums = () => {
            //     let count = 0;
            //     for (let i = 1; i < listings.length / 10; i++) {
            //         console.log(listings.length);
            //         count++;
            //         btnCount.push(count);
            //         console.log(btnCount);
            //     }
            //     return btnCount;
                
            // };

            // calling btnNums function for buttons to show on page load
            // $scope.btnNums();

            // $scope.showPage = (operator) => {
            // let startNum = (operator - 1) * 10;
            // let endNum = operator * 10;
            //return startNum;
        //};

        });

        // function for amount of  buttons needed
         //let btnCount = [];
        // console.log(`btnCount array: ${btnCount}`);

        // buttons for pages
        // $scope.num = btnCount;
        // console.log(`btnCount: ${btnCount}`);

        // showPage function
        // 1. Remember current page, update it whenever you change page. 
        // 2. If operator is 'back', set startNum and endNum based on currentPage - 1
        // 3. If operator if 'next', set startNum and endNum based on currentPage + 1
        // 4. Else use the formula we already have.
        //let currentPage = 1;

        // $scope.showPage = (operator) => {
        //     let startNum = (operator - 1) * 10;
        //     let endNum = operator * 10;
        //     console.log(`showPage func. Button ${operator} was pressed: ${startNum}, ${endNum}`);

           // $scope.locations = listings.slice(startNum, endNum);
            // if (operator === 'next' && endNum < listArr.length) {
            //     startNum = startNum + 10;
            //     endNum = endNum + 10;
            //     console.log($scope.num);
            // } else
            //     if (operator === 'back' && startNum > 0) {
            //         startNum = startNum - 10;
            //         endNum = endNum - 10;
            //         console.log('back button');
            //     } else
            //         if (operator === 'back' && startNum === 0 || operator === 'next' && endNum >= listArr.length) {
            //             startNum = startNum;
            //             endNum = endNum;
            //             console.log(`back button value is: ${operator}. Values: ${startNum}, ${endNum}`);
            //         }
            //         else
            //             if (operator === 1) {
            //                 startNum = 0;
            //                 endNum = 10;
            //                 console.log(`button ${operator} was pushed: ${startNum}, ${endNum}`);
            //             } else {
            //                 startNum = operator * 10;
            //                 endNum = startNum + 10;
            //                 console.log(` button ${operator} pushed: ${startNum}, ${endNum}`);
            //             }
            // return $scope.locations;
        //};

    },

};
