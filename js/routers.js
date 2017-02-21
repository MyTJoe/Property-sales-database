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