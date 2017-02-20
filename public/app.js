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
        $scope.coord = (lat, lng) => {
            MapService.locate(lat, lng);
            $state.go('map');
        }
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
                 lat: -35.667,
                 lng: 145.667,
            },
            zoom: 10
        });

        // let marker = new google.maps.Marker({
        //     position: {
        //         // lat: -34.397,
        //         // lng: 150.644,
        //     },
        //     map: kings_map
        // });
    };

    initMap();
    



        console.log('map controller');
        $scope.locate = () => {
            console.log('scope locate was clicked, activated in map controller');
            MapService.locate();
        }
    }

};
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
        return {
            getLoc: (county) => {
                console.log(`getLoc func in ListingsService: ${county}`);
                return $http.get
                (`https://countycrasher.herokuapp.com/${county}`)
                    .then(function (response) {
                        console.log(`data ${response.data}`);
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
