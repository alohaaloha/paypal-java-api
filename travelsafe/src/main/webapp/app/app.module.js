(function() {
    'use strict';

    angular.module('travelsafeapp',
        ['ui.router',
        'btorfs.multiselect',
        'pascalprecht.translate',
        "angucomplete-alt",
        'ui.bootstrap',])
        .run(run);


      run.$inject = ['$rootScope', '$state'];

      function run($rootScope, $state) {

      }


 })();