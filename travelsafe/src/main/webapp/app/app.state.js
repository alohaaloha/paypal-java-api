(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/home');
        $stateProvider
        .state('home', {
                url:'/home',
                views: {
                    'navbar@': {
                        templateUrl: 'navbar/navbar.html',
                        controller: 'NavbarController'
                    },
                    'content@':{
                        templateUrl: 'home/home.html',
                        controller: 'HomeController'
                    }
                }
        });

    }

})();
