(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('EditPersonDetailsController', EditPersonDetailsController);

    EditPersonDetailsController.$inject = ['$scope', '$uibModalInstance', 'person'];

    function EditPersonDetailsController($scope, $uibModalInstance, person) {
        $scope.person = person;
        console.log("Mocal controller dobio person:");
        console.log(person);

        $scope.popupDOBP = {
            opened: false
        };
        $scope.popupDOBP.opened = false;
        $scope.formats = ['yyyy/MM/dd', 'dd-MMMM-yyyy', 'dd-MM-yyyy', 'shortDate', 'd!.M!.yyyy', 'M!/d!/yyyy'];
        $scope.altInputFormats = $scope.formats;
        $scope.format = $scope.formats[0];
        $scope.maxBornDate = new Date();
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            showWeeks: false,
            maxDate: new Date()
          };
        $scope.openDateOfBirthPicker = function() {
            $scope.popupDOBP.opened = true;
        };

        // Closing and returning result
        $scope.dismissModal = function(personDetailsForm) {
            console.log("Closing modal...");
            console.log("Giving back person object:");
            console.log($scope.person);
            console.log("Giving back form validation:");
            console.log(personDetailsForm.$valid);
            var result = {
                person: $scope.person,
                isFormValid: personDetailsForm.$valid
            }
            $uibModalInstance.close(result);
        }

    }
})();
