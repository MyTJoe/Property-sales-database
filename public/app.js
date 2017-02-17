(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
const app = angular.module('PropData', [
        'ui.router',        
        'ngMaterial',
        'ngAnimate',
        'ngAria', 
        'angularUtils.directives.dirPagination',
    ]);
// angular material theme
// config(function ($mdThemingProvider) {
//     $mdThemingProvider.theme('default')
//         .primaryPalette('blue')
//         .accentPalette('orange')
    //end of theme


    //controller loop
    const controllers = [
        require('./controllers/listings'),
        require('./controllers/map'),
    ];
    for (let i = 0; i < controllers.length; i++) {
        app.controller(controllers[i].name, controllers[i].func)
    };
    // components loop
    const components = [
        require('./components/listings'),
        require('./components/map'),
    ];
    for (let i = 0; i < components.length; i++) {
        app.component(components[i].name, components[i].object)
    };
    // services loop
    const services = [
        require('./services/listings'),
        require('./services/map'),
    ];
    for (let i = 0; i < services.length; i++) {
        app.factory(services[i].name, services[i].func)
    };
    // router
    const routers = require('./routers');
    app.config($stateProvider => {
        for (let i = 0; i < routers.length; i++) {
            $stateProvider.state(routers[i]);
        }
    });

    // left in from starter pack. delete if not needed
// });
window.addEventListener('load', function () {
    console.log('ready to rock');
});




// might need for button pagination

// function OtherController($scope) {
//   $scope.pageChangeHandler = function(num) {
//     console.log('going to page ' + num);
//   };
// }
},{"./components/listings":2,"./components/map":3,"./controllers/listings":4,"./controllers/map":5,"./routers":6,"./services/listings":7,"./services/map":8}],2:[function(require,module,exports){
module.exports = {
    name: 'listings',
    object: {
        controller: 'ListingsController',
        templateUrl: 'templates/listings.html',
    },
};
},{}],3:[function(require,module,exports){
module.exports = {
    name: 'map',
    object: {
        controller: 'MapController',
        templateUrl: 'templates/map.html',
    },
};
},{}],4:[function(require,module,exports){
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

},{}],5:[function(require,module,exports){
module.exports = {
    name: 'MapController',
    func: function ($scope, MapService) {




    let kings_map;
    function initMap() {
        kings_map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                 lat: -34.397,
                 lng: 150.644,
            },
            zoom: 8
        });

        let marker = new google.maps.Marker({
            position: {
                // lat: -34.397,
                // lng: 150.644,
            },
            map: kings_map
        });
    };

    initMap();
    



        console.log('map controller');
        $scope.locate = () => {
            console.log('scope locate was clicked, activated in map controller');
            MapService.locate();
        }
    }

};



// (function(window, google) {

//     // map options
//     let options = {
//         center: {
//             lat: 37.791350, // lat and long for san fran. Change later
//             lng: -122.435883,
//     },
//     zoom:10,
// },
//     element = document.getElementById('#map')
//     // map
//     map = new google.maps.Map(element, options)

// }(window, google));
},{}],6:[function(require,module,exports){
module.exports = [
    {
        name: 'listings',
        url: '/listings',
        component: 'listings',
    },
    {
        name: 'map',
        url: '/map',
        component: 'map',
    },

]
},{}],7:[function(require,module,exports){
module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        // const locations = [];
        // temp get request for testing
        // $http.get('/fakerequest.json')
        // // $http.get('https://still-retreat-79338.herokuapp.com/address.json')
        // .then(function (response) {
        //     angular.copy(response.data, locations);
        //     console.log('then function');
        //     console.log(response.data);
        // });
        return {
            getLoc: () => {
                console.log('getLoc function');
                // console.log(locations);
                // return locations;
                //return $http.get('/fakerequest.json')
                return $http.get('https://still-retreat-79338.herokuapp.com/harnett')
                    .then(function (response) {
                        // angular.copy(response.data, locations);
                        console.log('then function');
                        //console.log(response.data);
                        return response.data;
                    });
            },
        }
    },
};

},{}],8:[function(require,module,exports){
module.exports = {
    name: 'MapService', 
    func:  () => {
    
        return {
            locate: () => {
                console.log('my map was clicked');
               //return mymap;
            }
        }
    },
};
},{}]},{},[1]);
