(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$scope', '$state'];

    function BuyController ($scope, $state) {
        $scope.activeOption = [true, false, false, false, false];
        $scope.activeOptionNumber = 0;

        $scope.setActiveOption = function (number) {
            if($scope.isOptionDisabled(number))
                return;
            $scope.activeOptionNumber = number;
            for(var i=0 ; i<5 ; i++) {
                $scope.activeOption[i] = false;
            }
            $scope.activeOption[number] = true;
            console.log($scope.people);
            if (number == 1){
                if($scope.currentPerson >= $scope.numOfPeople)  // If the number of person is decremented check if previously selected person is out of scope
                    $scope.currentPerson = -1;
                for(var i=0 ; i<$scope.numOfPeople ; i++) {      // Initializing new person objects if not defined previously
                    if($scope.people[i] == undefined){
                        var dateOfBirth = new Date();
                        dateOfBirth.setDate(dateOfBirth.getDate()-7);
                        $scope.people[i] = {
                            id: i,
                            name: "",
                            surname: "",
                            ageGroup: "",
                            dateOfBirth: dateOfBirth
                        };
                    }
                }
                if($scope.people.length > $scope.numOfPeople)   // If the number of person is decremented splice the curent people array
                    $scope.people.splice($scope.numOfPeople, $scope.people.length - $scope.numOfPeople);
            }
        }
        $scope.goToNextOption = function() {
            if($scope.activeOptionNumber != 4)
                $scope.setActiveOption($scope.activeOptionNumber + 1);
        }

        // OPTION 1 RELATED INFO
        $scope.numOfPeople = 0;
        $scope.duration = 0;
        $scope.region = "";
        $scope.amount = 0;

        // OPTION 2 RELATED INFO
        $scope.people = [];
        $scope.currentPerson = -1;
        $scope.changeCurrentPerson = function (number) {
            $scope.currentPerson = number;
        }
        $scope.popupDOBP = {
            opened: false
        };
        $scope.popupDOBP.opened = false;
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];
        $scope.altInputFormats = ['M!/d!/yyyy'];
        $scope.dateOptions = {
            disabled: false,
            formatYear: 'yy',
            startingDay: 1
          };
        $scope.openDateOfBirthPicker = function() {
            $scope.popupDOBP.opened = true;
        };


        // OPTION 3 RELATED INFO
        $scope.isOptionDisabled = function(optionNumber) {
            if(optionNumber != 0 && $scope.isOptionDisabled(optionNumber-1))    // Recursive check if previous option is disabled
                return true;                                                    // If so, next one is also disabled
            if(optionNumber == 1){
                if($scope.numOfPeople == 0 || $scope.duration == 0 || $scope.region=="" || $scope.amount <= 0)
                    return true;
                else
                    return false;
            }
            return false;
        }
    }
})();
