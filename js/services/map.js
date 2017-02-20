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