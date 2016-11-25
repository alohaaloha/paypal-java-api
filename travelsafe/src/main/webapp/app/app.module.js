'use strict';

angular.module('travelsafeapp',
    ['ngResource', 'ui.router', 'angularModalService','ngSanitize', 'ui.bootstrap'])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/home');
         $stateProvider
           .state('home', {
                   url: '/home',
                   views: {
                       'content@': {
                           templateUrl: 'home/home.html',
                           controller: 'HomeController'
                       },
                       'navbar':{
                           templateUrl: 'navbar/navbar.html',
                           controller: 'NavbarController'
                       }
                   }
               })
           .state('buy', {
                   parent: 'home',
                   url: '/buy',
                   views: {
                       'content@': {
                           templateUrl: 'buy/buy.html',
                           controller: 'BuyController',
                           controllerAs: 'bc'
                       }
                   }
               })
    })
    .run(function($rootScope,  $state) {

 });