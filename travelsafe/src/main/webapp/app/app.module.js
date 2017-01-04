(function() {
    'use strict';

    angular.module('travelsafeapp',
        ['ui.router',
        'ngMessages',
        'ngResource',
        'ngSanitize',
        'pascalprecht.translate',
        'ui.bootstrap',])
        .run(run);


      run.$inject = ['$rootScope', '$state'];

      function run($rootScope, $state) {

      }


 })();