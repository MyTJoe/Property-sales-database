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
