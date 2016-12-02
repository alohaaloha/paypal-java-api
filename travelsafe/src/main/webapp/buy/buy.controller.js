(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$scope', '$state', 'StatusService'];

    function BuyController ($scope, $state, StatusService) {


        $scope.activeOption = [true, false, false, false, false];
        $scope.activeOptionNumber = 0;

        $scope.progresBarValue=100/$scope.activeOption.length;

        $scope.setActiveOption = function (number) {
            if($scope.isOptionDisabled(number))
                return;
            $scope.activeOptionNumber = number;
            for(var i=0 ; i<5 ; i++) {
                $scope.activeOption[i] = false;
            }
            $scope.activeOption[number] = true;

            $scope.progresBarValue=($scope.activeOptionNumber+1)*(100/$scope.activeOption.length)-1;

            console.log($scope.people);
            if (number == 1){
                if($scope.insuranceCarrierIndex >= $scope.numOfPeople)  // If the number of person is decremented check if previously selected carrier person is out of scope
                    $scope.insuranceCarrierIndex = -1;
                if($scope.currentPerson >= $scope.numOfPeople)  // If the number of person is decremented check if previously selected person is out of scope
                    $scope.currentPerson = -1;
                for(var i=0 ; i<$scope.numOfPeople ; i++) {      // Initializing new person objects if not defined previously
                    if($scope.people[i] == undefined){
                        $scope.people[i] = {
                            id: i,
                            name: null,
                            surname: null,
                            pin: null,
                            carrier: false,
                            passportNumber: null,
                            address: null,
                            phoneNumber: null,
                            dateOfBirth: null
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
        $scope.insuranceCarrierIndex = -1;
        $scope.changeCurrentPerson = function (number) {
            $scope.currentPerson = number;
        }
        $scope.changeInsuranceCarrierIndex = function (newIndex) {
            $scope.insuranceCarrierIndex = newIndex;
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
            // TODO: UNCOMMENT THE IF STATEMENT BELOW. COMMENTED FOR TESTING PURPOSES SO WE DON'T NEED TO FILL
            //  FORM FOR EVERY PERSON TO BE ABLE TO GO TO NEXT OPTION
            /*if(optionNumber == 2){
                if ($scope.insuranceCarrierIndex == -1)
                    return true;
                for(var i=0 ; i<$scope.numOfPeople ; i++) {
                    if($scope.people[i] == undefined || ($scope.people[i].name == null ||
                            $scope.people[i].surname == null ||
                            $scope.people[i].pin == null ||
                            $scope.people[i].passportNumber == null ||
                            $scope.people[i].address == null ||
                            $scope.people[i].phoneNumber == null ||
                            $scope.people[i].dateOfBirth == null))
                        return true;
                }
                return false;
            }*/
            return false;
        }


        /*FINAL STEP - BUYING*/
        $scope.buyInsurance =  function(){
            //send obj
            //redirect to the link that is in response
            var insuranceObject = {};
            insuranceObject.duration='30';

            StatusService.createPayment(
               insuranceObject,
               function(res){
                    //console.log(res);
                    window.location = res.data.link.href;
               },
               function(res){
                    //console.log(res);
               }
            );
        }


    }
})();
