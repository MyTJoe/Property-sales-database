(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
const app = angular.module('PropData', [
        'ui.router',        
        'ngMaterial',
        'ngAnimate',
        'ngAria',
    ]);
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

},{}],5:[function(require,module,exports){

module.exports = {
    name: 'MapController',
    // $stateParams is how you pull values out of the route (URL)
    func: function ($scope, MapService, ListingsService, $stateParams) {
        let county = ListingsService.passCounty();
        let mainLat = 35.3579;
        let mainLong = -78.8836;
        
        if (county === null || county === undefined) {
            county = 'harnett';
        } else {county === 'franklin'} 

        let map = new google.maps.Map(document.querySelector('#map'), {
            center: {
                lat: 35.3579,
                lng: -78.8836,
            },
            zoom: 10,
            scrollwheel: false,
        });
        // show single map view
        ListingsService.getLoc(county).then((result) => {
            for (let i = 0; i < result.length; i++) {
                if (result[i].propertyId === $stateParams.pid) {
                    const lat = parseFloat(result[i].latitude);
                    const long = parseFloat(result[i].longitude);
                    let hybridMap = new google.maps.Map(document.querySelector('#map'), {
                        center: {
                            lat: lat,
                            lng: long,
                        },
                        zoom: 17,
                        mapTypeId: 'hybrid', 
                        scrollwheel: false,
                    });             
                    let marker = new google.maps.Marker({
                        position: {
                            lat: lat,
                            lng: long,
                        },
                        map: hybridMap,
                    });
                //shows all markers on road map 
                } else if ($stateParams.pid === '' || $stateParams.pid === undefined) {
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
            }
        });
    },
};
},{}],6:[function(require,module,exports){
module.exports = [
    {
        name: 'listings',
        url: '',
        component: 'listings',
    },
    {
        name: 'map',
        url: '/map/:pid', // colon indicates a 'route parameter'
        component: 'map',
    },
    {
        name: 'allmap',
        url: '/map/',
        component: 'map',
        params: {
            pid: undefined,
        }
    },


]
},{}],7:[function(require,module,exports){
module.exports = {
    name: 'ListingsService',
    func: ($http) => {
        let thisCounty = null;
        return {
            getLoc: (county) => {
                thisCounty = county;
                return $http.get
                (`https://countylink.herokuapp.com/${county}`)
                    .then((response) => {
                        return response.data;
                    });
            },
            passCounty: () => {
                return thisCounty;
            },
        }
    },
};

},{}],8:[function(require,module,exports){
module.exports = {
     name: 'MapService', 
     func: () => {
         return {
            locate: (lat, lng) => {
                coordinates = [lat, lng]
               return coordinates;
                },
            }  
        },
 };

},{}]},{},[1]);
