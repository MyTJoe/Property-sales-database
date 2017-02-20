(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
const app = angular.module('PropData', [
        'ui.router',        
        'ngMaterial',
        'ngAnimate',
        'ngAria',
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
        require('./controllers/pager'),
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
        require('./services/pager'),
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
},{"./components/listings":2,"./components/map":3,"./controllers/listings":4,"./controllers/map":5,"./controllers/pager":6,"./routers":7,"./services/listings":8,"./services/map":9,"./services/pager":10}],2:[function(require,module,exports){
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
        $scope.coord = (location) => {
            MapService.locate(location.latitude, location.longitude);
            $state.go('map', {
                pid: location.totalValue, // not right
            }); // rerouting to a different view
        }
    },
};

},{}],5:[function(require,module,exports){
module.exports = {
    name: 'MapController',
    func: function ($scope, MapService, ListingsService) {

    const map = new google.maps.Map(document.querySelector('#map'), {
        center: {
            lat: 35.3579,
            lng: -78.8836,
        },
        zoom: 10,
    });

    ListingsService.getLoc('harnett').then(function (result) {
        for (let i = 0; i < result.length; i++) {
            const lat = parseFloat(result[i].latitude);
            const long = parseFloat(result[i].longitude);

            let marker = new google.maps.Marker({
                position: {
                    lat: lat,
                    lng: long,
                },
                map: map,
            });
        }
    });


    // let kings_map;
    // function initMap() {
    //     let coord = MapService.coordinates;
    //     console.log(`initMap coord= ${coord}`);
    //     kings_map = new google.maps.Map(document.querySelector('#map'), {
    //         center: {
    //              lat: -35.667,
    //              lng: 145.667,
    //         },
    //         zoom: 10
    //     });

    //     // let marker = new google.maps.Marker({
    //     //     position: {
    //     //         // lat: -34.397,
    //     //         // lng: 150.644,
    //     //     },
    //     //     map: kings_map
    //     // });
    // };

    // initMap();
    



    //     console.log('map controller');
    //     $scope.locate = () => {
    //         console.log('scope locate was clicked, activated in map controller');
    //         MapService.locate();
    //     }
    }

};
},{}],6:[function(require,module,exports){
module.exports = {
    name: 'PagerController',
    func: (PagerService) => {
    var vm = this;
 
    vm.dummyItems = _.range(1, 151); // dummy array of items to be paged
    vm.pager = {};
    vm.setPage = setPage;
 
    initController();
 
    function initController() {
        // initialize to page 1
        vm.setPage(1);
    }
 
    function setPage(page) {
        if (page < 1 || page > vm.pager.totalPages) {
            return;
        }
 
        // get pager object from service
        vm.pager = PagerService.GetPager(vm.dummyItems.length, page);
 
        // get current page of items
        vm.items = vm.dummyItems.slice(vm.pager.startIndex, vm.pager.endIndex + 1);
    }
}
}

},{}],7:[function(require,module,exports){
module.exports = [
    {
        name: 'listings',
        url: '/listings',
        component: 'listings',
    },
    {
        name: 'map',
        url: '/map/:pid', // colon indicates a 'route parameter'
        component: 'map',
    },

]
},{}],8:[function(require,module,exports){
module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        return {
            getLoc: (county) => {
                console.log(`getLoc func in ListingsService: ${county}`);
                return $http.get
                (`https://countylink.herokuapp.com/${county}`)
                    .then(function (response) {
                        console.log(`data ${response.data}`);
                        return response.data;
                    });
            },
        }
    },
};

},{}],9:[function(require,module,exports){
 module.exports = {
     name: 'MapService', 
     func: () => {
         let coordinates = [];
         let lati;
         let long;
//         let coordinates;
//         let kings_map;
//     function initMap() {
//         let coord = MapService.coordinates;
//         console.log(`initMap coord= ${coord}`);
//         kings_map = new google.maps.Map(document.querySelector('#map'), {
//             center: {
//                  lat: -35.667,
//                  lng: 145.667,
//             },
//             zoom: 10
//         });

//         let marker = new google.maps.Marker({
//             position: {
//                 // lat: -34.397,
//                 // lng: 150.644,
//             },
//             map: kings_map
//         });
//     };

         return {
            // locate: (lat, lng) => {
            //     coordinates = [lat, lng]
            //     console.log(`my map was clicked ${coordinates[0]}`);
            //    //return kings_map;
            // },

                // function initMap () {
             initMap: (lat, lng) => {
                 console.log(`initMap func: ${coordinates}`);
                 console.log(`logging coordinates in initMap: ${lati}`);
    // const map = new google.maps.Map(document.querySelector('#map'), {
    //     center: {
    //         lat: -34.397,
    //         lng: 135.543,
    //     },
    //     zoom: 10
    // });
    //return map;
},
locate: (lat, lng) => {
                coordinates = [lat, lng]
                lati = lat;
                long = lng;
                console.log(`my map was clicked ${coordinates[0]}`);
               return coordinates;
            },

         }  
     },
 };
 

// function initMap () {
//     const map = new google.maps.Map(document.querySelector('#map'), {
//         center: {
//             lat: -34.567,
//             lng: 35.543,
//         },
//         zoom: 10
//     });
// };
},{}],10:[function(require,module,exports){
module.exports = {
    name: 'PagerService',
    func: () => {
    // service definition
        var service = {};
    
        service.GetPager = GetPager;
    
        return service;
    
        // service implementation
        function GetPager(totalItems, currentPage, pageSize) {
            // default to first page
            currentPage = currentPage || 1;
    
            // default page size is 10
            pageSize = pageSize || 10;
    
            // calculate total pages
            var totalPages = Math.ceil(totalItems / pageSize);
    
            var startPage, endPage;
            if (totalPages <= 10) {
                // less than 10 total pages so show all
                startPage = 1;
                endPage = totalPages;
            } else {
                // more than 10 total pages so calculate start and end pages
                if (currentPage <= 6) {
                    startPage = 1;
                    endPage = 10;
                } else if (currentPage + 4 >= totalPages) {
                    startPage = totalPages - 9;
                    endPage = totalPages;
                } else {
                    startPage = currentPage - 5;
                    endPage = currentPage + 4;
                }
            }
    
            // calculate start and end item indexes
            var startIndex = (currentPage - 1) * pageSize;
            var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);
    
            // create an array of pages to ng-repeat in the pager control
            var pages = _.range(startPage, endPage + 1);
    
            // return object with all pager properties required by the view
            return {
                totalItems: totalItems,
                currentPage: currentPage,
                pageSize: pageSize,
                totalPages: totalPages,
                startPage: startPage,
                endPage: endPage,
                startIndex: startIndex,
                endIndex: endIndex,
                pages: pages
            };
        }
    }
}
},{}]},{},[1]);
