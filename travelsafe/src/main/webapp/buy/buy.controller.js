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



        // OPTION 1 RELATED INFO
        $scope.numOfPeople = 0;
        $scope.duration = 0;
        $scope.region = "";
        $scope.coverage = null;
        $scope.setCoverage = function (amount) {
            $scope.coverage = amount;
        }

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
        $scope.isHomeWanted = false;
        $scope.hi = { };
        $scope.hi.address = "";
        $scope.hi.age = 0;
        $scope.hi.surfaceArea = 0;
        $scope.hi.estimatedValue = 0;
        $scope.hi.ownersName = "";
        $scope.hi.ownersSurname = "";
        $scope.hi.ownersPIN = "";
        $scope.hitiDurationEquals = true;
        $scope.hiMaxYear = new Date().getFullYear();
        $scope.changeHIDuration = function() {
            if($scope.hitiDurationEquals)
                $scope.hi.duration = $scope.duration;
        }
        $scope.hiFlood = false;
        $scope.hiBurglary = false;
        $scope.hiFire = false;
        $scope.triggerHICoverage = function (type) {
            switch (type) {
                case "fire":
                    $scope.hiFire = !$scope.hiFire;
                    break;
                case "flood":
                    $scope.hiFlood = !$scope.hiFlood;
                    break;
                case "burglary":
                    $scope.hiBurglary = !$scope.hiBurglary;
                    break;
            }
        }

        // GENERAL
        $scope.isOptionDisabled = function(optionNumber) {
            //return false; //TODO: Delete this line... Only for development purposes
            if (optionNumber != 0 && $scope.isOptionDisabled(optionNumber-1))    // Recursive check if previous option is disabled
                return true;                                                    // If so, next one is also disabled
            if (optionNumber == 1 && $scope.firstForm.$invalid) {
                return true;
            }
            //  FORM FOR EVERY PERSON TO BE ABLE TO GO TO NEXT OPTION
            if (optionNumber == 2) {
                if ($scope.insuranceCarrierIndex == -1)
                    return true;
                for (var i=0 ; i<$scope.numOfPeople ; i++) {
                    if(!$scope.peopleFormValid[i])
                        return true;
                }
            }
            if (optionNumber == 3 && $scope.isHomeWanted && $scope.homeInsuranceForm.$invalid) {
                return true;
            }
            return false;
        }
        $scope.setActiveOption = function (number) {
            if ($scope.isOptionDisabled(number))
                return;
            $scope.activeOptionNumber = number;
            for (var i=0 ; i<5 ; i++) {
                $scope.activeOption[i] = false;
            }
            $scope.activeOption[number] = true;

            $scope.progresBarValue = ($scope.activeOptionNumber + 1) * (100 / $scope.activeOption.length) - 1;

            if (number == 1) {
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
            if (number == 2) {
                $scope.hi.duration = $scope.duration;
            }
        }
        $scope.goToNextOption = function() {
            if($scope.activeOptionNumber != 4)
                $scope.setActiveOption($scope.activeOptionNumber + 1);
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
            $scope.travelInsurance.homeInsurances = $scope.hi;
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
