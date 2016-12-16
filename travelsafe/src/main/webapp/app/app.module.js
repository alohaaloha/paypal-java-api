(function() {
    'use strict';

    angular.module('travelsafeapp',
        ['ngResource',
        'ui.router',
        'angularModalService',
        'ngSanitize',
        'ui.bootstrap',
        'pascalprecht.translate'])
        .run(run);


      run.$inject = ['$rootScope', '$state'];

      function run($rootScope, $state) {

      }


 })();