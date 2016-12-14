(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state'];

    function HomeController ($scope, $state) {

      /* TestService.getStuff(
           function(res){
           console.log(res);
           },
           function(res){
           console.log(res);
           }
       );*/

    }
})();
