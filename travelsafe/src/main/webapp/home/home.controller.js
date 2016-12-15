(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state'];

    function HomeController ($scope, $state) {


    //customTheme(true);

    $scope.goToBuyState = function(){
                  //$state.go('buy');
                  //window.location = "http://localhost:8090/#/buy"
                  window.location.href = "http://localhost:8090/#/buy";

              }

    }
})();
