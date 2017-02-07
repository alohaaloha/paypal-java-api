(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$scope', '$state', '$uibModal','StatusService', 'PriceService', '$http', '$q', '$translate', '$timeout'];

    function BuyController($scope, $state, $uibModal, StatusService, PriceService, $http, $q, $translate, $timeout) {

        var $buyController = this;
        //customTheme(false);

        $scope.travelInsurance = {};
        $scope.travelInsurance.totalPrice = 0;
        $scope.activeOption = [true, false, false, false, false];
        $scope.activeOptionNumber = 0;
        $scope.progresBarValue=100/$scope.activeOption.length;

        // OPTION 1 RELATED INFO
        $scope.travelInsurance.numberOfPeople = 0;
        $scope.travelInsurance.duration = 0;
        $scope.travelInsurance.maxAmount = null;
        $scope.setCoverage = function (amount) {
            $scope.travelInsurance.maxAmount = amount;
        }

        //Risks
        $scope.risks = {};

        // If user have already chosen that he wants home insurance/road assistance and have chosen it's duration to be the same as travel insurance's
        // and if he afterwards change the duration of travel, we need to change home insurance/road assistance duration also because he will probably not select the duration to be the same as travel's duratio.
        $scope.durationChanged = function () {
            if ($scope.isHomeWanted && $scope.hitiDurationEquals)
                $scope.hi.duration = $scope.travelInsurance.duration;
            if ($scope.isCarWanted && $scope.citiDurationEquals)
                $scope.ci.duration = $scope.travelInsurance.duration;
            PriceService.fetchPrice($scope.travelInsurance, $scope.refreshPrice, function(response){
                console.log("Unsuccessful try for fetching price");
            })
        }
        $scope.refreshPrice = function (response) {
            console.log("Successful fetched price: " + response.data);
            $scope.travelInsurance.totalPrice = response.data;
        }
        // These two functions are required for angucomplete component for selecting region
        $scope.remoteUrlRequestFn = function(searchCriteria) {
            var language = "";
            if ($translate.use() == "sr")
                language = "ser";
            else
                language = "en";
            return {searchCriteria: searchCriteria, language: language}
        }
        $scope.remoteUrlResponseFn = function(response) {
            for (var i=0 ; i<response.length ; i++) {
                response[i].flag = "website_theme/custom/images/regions/" + $translate.use() + "/" + response[i].name + ".png"
            }
            return {regions: response};
        }
        $scope.regionSelectedCallback = function(selected) {
            if (selected)
                $scope.travelInsurance.region = selected.originalObject;
        }

        // OPTION 2 RELATED INFO
        $scope.travelInsurance.participantInInsurances = [];
        $scope.peopleFormValid = []
        $scope.currentPerson = -1;
        $scope.insuranceCarrierIndex = -1;
        $scope.changeCurrentPerson = function (number) {
            $scope.currentPerson = number;
        }
        $scope.changeInsuranceCarrierIndex = function (newIndex) {
            for (var i = 0 ; i<$scope.travelInsurance.participantInInsurances.length ; i++){
                $scope.travelInsurance.participantInInsurances[i].carrier = false;
            }
            $scope.travelInsurance.participantInInsurances[newIndex].carrier = true;
        }
        $scope.openPersonDetailsModal = function(personIndex){
            var modalInstance = $uibModal.open({
                  ariaLabelledBy: 'modal-title',
                  ariaDescribedBy: 'modal-body',
                  templateUrl: 'buy/edit-person-details-dialog.template.html',
                  controller: 'EditPersonDetailsController',
                  resolve: {
                    person: function () {
                      return $scope.travelInsurance.participantInInsurances[personIndex];
                    }
                  }
                });

            modalInstance.result.then(function (result) {
                $scope.travelInsurance.participantInInsurances[personIndex] = result.person;
                $scope.peopleFormValid[personIndex] = result.isFormValid;
            }, function () {
                // Probably nothing to do
            });
        }

        $scope.openRisksModal = function(personIndex){
            var modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'buy/type-of-risks-dialog.template.html',
                controller: 'TypeOfRiskController',
                resolve: {
                    person: function () {
                        return $scope.travelInsurance.participantInInsurances[personIndex];
                    },
                    typeOfRisks: function(){
                        var req = {
                            method : 'GET',
                            url : '/api/TypeOfRisks/1'
                        };
                        var defer = $q.defer();
                        $http(req).then(function(data) {
                            console.log(data);
                            defer.resolve(data.data);
                        }, function() {
                            console.log("FAILED GET RISKS");
                        });
                        return defer.promise;
                    },
                    risks: function(){
                        return $scope.risks;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                $scope.travelInsurance.participantInInsurances[personIndex].items = result.person.items;
                $scope.risks = result.risks;
                //$scope.peopleFormValid[personIndex] = result.isFormValid;
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
                $scope.hi.duration = $scope.travelInsurance.duration;
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

        // OPTION 4 RELATED INFO
        $scope.isCarWanted = false;
        $scope.ci = { };
        $scope.ci.duration = 0;
        $scope.citiDurationEquals = true;
        $scope.changeCIDuration = function() {
            if($scope.citiDurationEquals)
                $scope.ci.duration = $scope.travelInsurance.duration;
        }
        $scope.ci.brand = "";
        $scope.ci.type = "";
        $scope.ci.yearOfProduction = 0;
        $scope.ciMaxYearOfProduction = new Date().getFullYear();
        $scope.ci.registrationNumber = "";
        $scope.ci.chassisNumber = "";
        $scope.ci.ownersName = "";
        $scope.ci.ownersSurname = "";
        $scope.ci.ownersPIN = "";
        $scope.ciTowing = false;
        $scope.ciAccomodation = false;
        $scope.ciRepair = false;
        $scope.ciTransport = false;
        $scope.triggerCICoverage = function (type) {
            switch (type) {
                case "towing":
                    $scope.ciTowing = !$scope.ciTowing;
                    break;
                case "accomodation":                                        // rename to accommodation
                    $scope.ciAccomodation = !$scope.ciAccomodation;
                    break;
                case "repair":
                    $scope.ciRepair = !$scope.ciRepair;
                    break;
                case "transport":
                    $scope.ciTransport = !$scope.ciTransport;
                    break;
            }
        }

        // GENERAL
        $scope.isOptionDisabled = function(optionNumber) {
            return false; //TODO: Delete this line... Only for development purposes
            if (optionNumber != 0 && $scope.isOptionDisabled(optionNumber-1))    // Recursive check if previous option is disabled
                return true;                                                    // If so, next one is also disabled
            if (optionNumber == 1 && $scope.firstForm.$invalid) {
                return true;
            }
            if (optionNumber == 2) {
                if ($scope.insuranceCarrierIndex == -1)
                    return true;
                for (var i=0 ; i<$scope.travelInsurance.numberOfPeople ; i++) {
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
                if($scope.insuranceCarrierIndex >= $scope.travelInsurance.numberOfPeople)  // If the number of person is decremented check if previously selected carrier person is out of scope
                    $scope.insuranceCarrierIndex = -1;
                if($scope.currentPerson >= $scope.travelInsurance.numberOfPeople)  // If the number of person is decremented check if previously selected person is out of scope
                    $scope.currentPerson = -1;
                for(var i=0 ; i<$scope.travelInsurance.numberOfPeople ; i++) {      // Initializing new person objects if not defined previously
                    if($scope.travelInsurance.participantInInsurances[i] == undefined){
                        $scope.travelInsurance.participantInInsurances[i] = {
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
                if($scope.travelInsurance.participantInInsurances.length > $scope.travelInsurance.numberOfPeople)   // If the number of person is decremented splice the curent people array
                    $scope.travelInsurance.participantInInsurances.splice($scope.travelInsurance.numberOfPeople, $scope.travelInsurance.participantInInsurances.length - $scope.travelInsurance.numberOfPeople);
            }
            if (number == 2) {
                if ($scope.hitiDurationEquals)
                    $scope.hi.duration = $scope.travelInsurance.duration;
            }
            if (number == 3) {
                if ($scope.citiDurationEquals)
                    $scope.ci.duration = $scope.travelInsurance.duration;
            }
        }
        $scope.goToNextOption = function() {
            if($scope.activeOptionNumber != 4)
                $scope.setActiveOption($scope.activeOptionNumber + 1);
        }

        /*FINAL STEP - BUYING*/
        $scope.buyInsurance =  function(){
            $scope.travelInsurance.homeInsurances = [ $scope.hi ];
            $scope.travelInsurance.carInsurances = [ $scope.ci ];
            $scope.travelInsurance.region = $scope.region.title; //TODO: Change this to be json representation of an item with region type of risk

            /*  Travel insurance rest test
            var req = {
                method: 'POST',
                url: '/api/TravelInsurances',
                data: $scope.travelInsurance
            }
            $http(req).then(function() {
                console.log("TRAVEL INSURANCE SUCCESSFULLY POSTED");
            }, function() {
                console.log("FAILED POSTING TRAVEL INSURANCE");
            });
            */

            //send obj
            //redirect to the link that is in response
            StatusService.createPayment(
               $scope.travelInsurance,
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
