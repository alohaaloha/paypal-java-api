(function() {
'use strict';

angular
    .module('travelsafeapp')
    .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

         $stateProvider
           .state('buy', {
                  url: '/buy',
                  views: {
                      'content@': {
                          templateUrl: 'buy/buy.html',
                          controller: 'BuyController',
                          controllerAs: 'bc'
                      },
                      'navbar':{
                          templateUrl: 'navbar/navbar.html',
                          controller: 'NavbarController'
                      }
                  }
              })
    };


  })();