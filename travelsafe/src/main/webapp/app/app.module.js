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
    })
    .run(function($rootScope,  $state) {

 });