(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$scope', '$state', '$uibModal','StatusService'];

    function BuyController($scope, $state, $uibModal, StatusService) {

        var $buyController = this;
        //customTheme(false);

        $scope.activeOption = [true, false, false, false, false];
        $scope.activeOptionNumber = 0;

        $scope.progresBarValue=100/$scope.activeOption.length;

        $scope.setActiveOption = function (number) {
        console.log($scope.numOfPeople);
            if($scope.isOptionDisabled(number))
                return;
            $scope.activeOptionNumber = number;
            for(var i=0 ; i<5 ; i++) {
                $scope.activeOption[i] = false;
            }
            $scope.activeOption[number] = true;

            $scope.progresBarValue=($scope.activeOptionNumber+1)*(100/$scope.activeOption.length)-1;

            if (number == 1){
                if($scope.insuranceCarrierIndex >= $scope.numOfPeople)  // If the number of person is decremented check if previously selected carrier person is out of scope
                    $scope.insuranceCarrierIndex = -1;
                if($scope.currentPerson >= $scope.numOfPeople)  // If the number of person is decremented check if previously selected person is out of scope
                    $scope.currentPerson = -1;
                for(var i=0 ; i<$scope.numOfPeople ; i++) {      // Initializing new person objects if not defined previously
                    if($scope.people[i] == undefined){
                        $scope.people[i] = {
                            idx: i,
                            name: null,
                            surname: null,
                            pin: null,
                            carrier: false,
                            passportNumber: null,
                            address: null,
                            phoneNumber: null,
                            dateOfBirth: null
                        };
                        $scope.peopleFormValid[i] = false;
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
        $scope.peopleFormValid = []
        $scope.currentPerson = -1;
        $scope.insuranceCarrierIndex = -1;
        $scope.changeCurrentPerson = function (number) {
            $scope.currentPerson = number;
        }
        $scope.changeInsuranceCarrierIndex = function (newIndex) {
            $scope.insuranceCarrierIndex = newIndex;
        }
        $scope.openPersonDetailsModal = function(personIndex){
            var modalInstance = $uibModal.open({
                  ariaLabelledBy: 'modal-title',
                  ariaDescribedBy: 'modal-body',
                  templateUrl: 'buy/edit-person-details-dialog.template.html',
                  controller: 'EditPersonDetailsController',
                  resolve: {
                    person: function () {
                      return $scope.people[personIndex];
                    }
                  }
                });

            modalInstance.result.then(function (result) {
                $scope.people[personIndex] = result.person;
                $scope.peopleFormValid[personIndex] = result.isFormValid;
            }, function () {
                // Probably nothing to do
            });
        }


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
            // TODO: Uncomment the IF statement below. Commented for testing purposes so we don't need to fill everything everytime
            //  FORM FOR EVERY PERSON TO BE ABLE TO GO TO NEXT OPTION
            if(optionNumber == 2){
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
            }
            return false;
        }


        /*FINAL STEP - BUYING*/
        $scope.buyInsurance =  function(){
            //send obj
            //redirect to the link that is in response
            var insuranceObject = {};
            insuranceObject.duration='30';

            $scope.travelInsurance = {};
            $scope.travelInsurance.duration = $scope.duration;
            $scope.travelInsurance.region = $scope.region;
            $scope.travelInsurance.amount = $scope.amount;
            $scope.travelInsurance.numberOfPeople = $scope.numOfPeople;
            $scope.travelInsurance.participantInInsurances = $scope.people;
            $scope.travelInsurance.homeInsurances = $scope.homeInsurances;
            $scope.travelInsurance.carInsurances = $scope.carInsurances;

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
