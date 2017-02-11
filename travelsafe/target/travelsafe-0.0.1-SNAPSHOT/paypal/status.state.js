(function() {
'use strict';

angular
     .module('travelsafeapp')
    .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

         $stateProvider
           .state('status', {
                   url: '/status/{orderId}',
                   views: {
                       'content@': {
                           templateUrl: 'paypal/status.html',
                           controller: 'StatusController'
                       },
                       'navbar':{
                           templateUrl: 'navbar/navbar.html',
                           controller: 'NavbarController'
                       }
                   }
               })
    }

 })();