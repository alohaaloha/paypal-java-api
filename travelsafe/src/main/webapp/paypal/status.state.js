'use strict';

angular.module('travelsafeapp')
    .config(function ($stateProvider, $urlRouterProvider) {

         $stateProvider
           .state('status', {
                   url: '/status',
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
    });