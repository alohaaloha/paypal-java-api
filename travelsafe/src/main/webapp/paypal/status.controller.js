(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('StatusController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'TestService', 'StatusService'];

    function HomeController ($scope, $state, TestService, StatusService) {

       TestService.getStuff(
           function(res){
            console.log(res);
           },
           function(res){
            console.log(res);
           }
       );



       //1. extract id from url, extract payment and payer id from paypal req and call back to execute
       //2. update: show success or error on page instead of '...'
        StatusService.executePayment(
        "todo",
        function(){

        },
        function(){

        })

    }
})();
