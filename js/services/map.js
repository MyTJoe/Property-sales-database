 module.exports = {
     name: 'MapService', 
     func: () => {
         
         return {
            // no longer needed
            //  initMap: (lat, lng) => {
            //      console.log(`initMap func: ${coordinates}`);
            //      console.log(`logging coordinates in initMap: ${lati}`);

            // },
            locate: (lat, lng) => {
                let coordinates = [lat, lng]
               return coordinates;
            },
            //this is to get info for all counties from listing controller. might not need
            // allCounties: (cts) => {
            //     let counties = cts;
            //     return counties;
            //     }
            }


         },  
     };
 
