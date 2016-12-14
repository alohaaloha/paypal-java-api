(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$scope', '$state','$translate'];

    function NavbarController ($scope, $state, $translate) {

        $scope.changeCurrentLanguage = function(key){
            $translate.use(key);
        };

    }
})();
