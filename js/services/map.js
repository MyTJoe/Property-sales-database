 module.exports = {
     name: 'MapService', 
     func: () => {
         let coordinates = [];
         let lati;
         let long;

         return {

             initMap: (lat, lng) => {
                 console.log(`initMap func: ${coordinates}`);
                 console.log(`logging coordinates in initMap: ${lati}`);

},
locate: (lat, lng) => {
                coordinates = [lat, lng]
                lati = lat;
                long = lng;
                console.log(`my map was clicked ${lati}, ${long}`);
               return coordinates;
            },

         }  
     },
 };
