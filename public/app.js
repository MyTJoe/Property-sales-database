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
        require('./controllers/county'),
    ];
    for (let i = 0; i < controllers.length; i++) {
        app.controller(controllers[i].name, controllers[i].func)
    };
    // components loop
    const components = [
        require('./components/listings'),
        require('./components/map'),
        require('./components/county'),
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
},{"./components/county":2,"./components/listings":3,"./components/map":4,"./controllers/county":5,"./controllers/listings":6,"./controllers/map":7,"./routers":8,"./services/listings":9,"./services/map":10}],2:[function(require,module,exports){
module.exports = {
    name: 'county',
    object: {
        controller: 'CountyController',
        templateUrl: 'templates/county.html',
    },
};
},{}],3:[function(require,module,exports){
module.exports = {
    name: 'listings',
    object: {
        controller: 'ListingsController',
        templateUrl: 'templates/listings.html',
    },
};
},{}],4:[function(require,module,exports){
module.exports = {
    name: 'map',
    object: {
        controller: 'MapController',
        templateUrl: 'templates/map.html',
    },
};
},{}],5:[function(require,module,exports){
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
},{}],6:[function(require,module,exports){
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

},{}],7:[function(require,module,exports){
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
},{}],8:[function(require,module,exports){
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
    {
        name: 'county',
        url: '/county',
        component: 'county',
    },

]
},{}],9:[function(require,module,exports){
module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        return {
            getLoc: (county) => {
                console.log(`getLoc func in ListingsService: ${county}`);
                return $http.get
                (`https://countycrasher.herokuapp.com/harnett`)
                    .then(function (response) {
                        console.log(`data ${response.data}`);
                        return response.data;
                    });
            },
        }
    },
};

},{}],10:[function(require,module,exports){
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
