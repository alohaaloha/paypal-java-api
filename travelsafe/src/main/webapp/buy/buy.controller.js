(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$rootScope','$scope', '$state', '$uibModal','StatusService', 'PriceService', '$http', '$q', '$translate', '$timeout', 'ItemService'];

    function BuyController($rootScope, $scope, $state, $uibModal, StatusService, PriceService, $http, $q, $translate, $timeout, ItemService) {

        var $buyController = this;
        //customTheme(false);

        initData();
        $rootScope.$on('$translateChangeSuccess', function (){
            console.log("TRANSLATE CHANGED SUCCESSFULLY. NEW LANGUAGE:");
            console.log($translate.use());
            var newLanguageCode = $translate.use();
            for (var i = 0 ; i < $scope.ageTypeOfRiskItems.length ; i++){
                if(newLanguageCode == "sr")
                    $scope.ageTypeOfRiskItems[i].text = $scope.ageTypeOfRiskItems[i].name_srb;
                else if (newLanguageCode == "en")
                    $scope.ageTypeOfRiskItems[i].text = $scope.ageTypeOfRiskItems[i].name_en;
            }
            for(var i = 0; i < $scope.homeInsuranceDesc.length; i++){
                if ($translate.use() == "sr")
                    $scope.homeInsuranceDesc[i].text = $scope.homeInsuranceDesc[i].name_srb;
                else if ($translate.use() == "en")
                    $scope.homeInsuranceDesc[i].text = $scope.homeInsuranceDesc[i].name_en;
            }
            for(var i = 0; i < $scope.carPackages.length; i++){
                if ($translate.use() == "sr")
                    $scope.carPackages[i].text = $scope.carPackages[i].name_srb;
                else if ($translate.use() == "en")
                    $scope.carPackages[i].text = $scope.carPackages[i].name_en;
            }
            if ($translate.use() == "sr")
                $scope.nameColumnByLanguage = "name_srb";
            else if ($translate.use() == "en")
                $scope.nameColumnByLanguage = "name_en";
        });

        $scope.travelInsurance = {};
        $scope.travelInsurance.totalPrice = 0;
        $scope.activeOption = [true, false, false, false, false];
        $scope.activeOptionNumber = 0;
        $scope.progresBarValue=100/$scope.activeOption.length;

        // OPTION 1 RELATED INFO
        $scope.travelInsurance.numberOfPeople = 0;
        $scope.numberOfAdultPeople = 0;
        $scope.travelInsurance.duration = 0;
        $scope.travelInsurance.maxAmount = null;
        $scope.setMaxAmount = function (maxAmount) {
            $scope.travelInsurance.maxAmount = maxAmount;
        }
        //Risks
        $scope.risks = {};
        $scope.refreshNumberOfPeople = function () {
            var length = $scope.ageTypeOfRiskItems.length;
            var numberOfPeople = 0;
            $scope.numberOfAdultPeople = 0;
            for (var i = 0 ; i < length ; i++){
                var code = $scope.ageTypeOfRiskItems[i].code;
                if ($scope.ageTypeOfRiskItems[i].number != undefined){
                    numberOfPeople += $scope.ageTypeOfRiskItems[i].number;
                    if (code == "btw_18_65" || code == "gt_65")
                        $scope.numberOfAdultPeople += $scope.ageTypeOfRiskItems[i].number;
                }
            }
            $scope.travelInsurance.numberOfPeople = numberOfPeople;
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
                        dateOfBirth: null,
                        items: []
                    };
                    $scope.peopleFormValid[i] = false;
                    var j = i;
                    var watchDelegate = (function(itemDelegate){
                        return function () {
                            return itemDelegate.items;
                        };
                    })($scope.travelInsurance.participantInInsurances[i]);

                    $scope.$watch(watchDelegate, $scope.calculatePrice);

                }
            }
            if($scope.travelInsurance.participantInInsurances.length > $scope.travelInsurance.numberOfPeople)   // If the number of person is decremented splice the curent people array
                $scope.travelInsurance.participantInInsurances.splice($scope.travelInsurance.numberOfPeople, $scope.travelInsurance.participantInInsurances.length - $scope.travelInsurance.numberOfPeople);
        }
        function initData(){
            // Geting actual max ammount risk items
            ItemService.getItemByTypeOfRiskCode("max_amount_ti", function(response) {
                $scope.maxAmountTypeOfRiskItems = response.data;
                for (var i = 0 ; i < $scope.maxAmountTypeOfRiskItems.length ; i++){
                    $scope.maxAmountTypeOfRiskItems[i].number = 0;
                    $scope.maxAmountTypeOfRiskItems[i].typeOfRisk = null;
                    if ($translate.use() == "sr")
                        $scope.maxAmountTypeOfRiskItems[i].text = $scope.maxAmountTypeOfRiskItems[i].name_srb;
                    else if ($translate.use() == "en")
                        $scope.maxAmountTypeOfRiskItems[i].text = $scope.maxAmountTypeOfRiskItems[i].name_en;
                }
            }, function (response) {
                console.log("Unsuccessful try to get max amount risks.");
            });

            // Geting actual age risk items
            ItemService.getItemByTypeOfRiskCode("age_ti", function(response) {
                $scope.ageTypeOfRiskItems = response.data;
                for (var i = 0 ; i < $scope.ageTypeOfRiskItems.length ; i++){
                    $scope.ageTypeOfRiskItems[i].number = 0;
                    if ($translate.use() == "sr")
                        $scope.ageTypeOfRiskItems[i].text = $scope.ageTypeOfRiskItems[i].name_srb;
                    else if ($translate.use() == "en")
                        $scope.ageTypeOfRiskItems[i].text = $scope.ageTypeOfRiskItems[i].name_en;
                }
            }, function (response) {
                console.log("Unsuccessful try to get age risks.");
            });
            if ($translate.use() == "sr")
                $scope.nameColumnByLanguage = "name_srb";
            else if ($translate.use() == "en")
                $scope.nameColumnByLanguage = "name_en";

            // Geting actual car insurance items
            ItemService.getItemByTypeOfRiskCode("car_package_ci", function (data) {
                $scope.carPackages = data.data;

                var map = {};

                for(var i = 0; i < $scope.carPackages.length; i++){
                    map[$scope.carPackages[i].id] = false;
                    if ($translate.use() == "sr")
                        $scope.carPackages[i].text = $scope.carPackages[i].name_srb;
                    else if ($translate.use() == "en")
                        $scope.carPackages[i].text = $scope.carPackages[i].name_en;
                }

                $scope.packagesResults = map;
            }, function () {
                console.log("Unsuccessful try to get car package risks.");
            });

            // Geting actual home insurance items
            ItemService.getItemByTypeOfRiskCode("insurance_desc_hi",function(data){
                $scope.homeInsuranceDesc = data.data;

                var map = {};

                for(var i = 0; i < $scope.homeInsuranceDesc.length; i++){
                    map[$scope.homeInsuranceDesc[i].id] = false;
                    if ($translate.use() == "sr")
                        $scope.homeInsuranceDesc[i].text = $scope.homeInsuranceDesc[i].name_srb;
                    else if ($translate.use() == "en")
                        $scope.homeInsuranceDesc[i].text = $scope.homeInsuranceDesc[i].name_en;
                }

                $scope.insuranceDescResults = map;
            },function(){
                console.log("Unsuccessful try to get home insurance risk items.");
            })

        };

        // If user have already chosen that he wants home insurance/road assistance and have chosen it's duration to be the same as travel insurance's
        // and if he afterwards change the duration of travel, we need to change home insurance/road assistance duration also because he will probably not select the duration to be the same as travel's duratio.
        $scope.durationChanged = function () {
            if ($scope.isHomeWanted && $scope.hitiDurationEquals)
                $scope.hi.duration = $scope.travelInsurance.duration;
            if ($scope.isCarWanted && $scope.citiDurationEquals)
                $scope.ci.duration = $scope.travelInsurance.duration;
        }

        // These three functions are required for angucomplete component for selecting region
        $scope.remoteUrlRequestFn = function(searchCriteria) {
            return {searchCriteria: searchCriteria, language: $translate.use()};
        }
        $scope.remoteUrlResponseFn = function(response) {
            for (var i=0 ; i<response.length ; i++) {
                response[i].flag = "assets/custom/images/regions/" + response[i].code + ".png"
            }
            return {regions: response};
        }
        $scope.regionSelectedCallback = function(selected) {
            if (selected){
                $scope.travelInsurance.region = selected.originalObject;
                $scope.travelInsurance.region.typeOfRisk = null;
            }
        }
        $scope.regionInputTextChanged = function () {
            $scope.travelInsurance.region = null;
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
                $scope.travelInsurance.participantInInsurances[i].email = null;
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
                        return $scope.risks[personIndex];
                    }
                }
            });

            modalInstance.result.then(function (result) {
                $scope.travelInsurance.participantInInsurances[personIndex].items = result.person.items;
                $scope.risks[personIndex] = result.risks;
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
        $scope.hi.insuranceDescriptions = [];
        $scope.hitiDurationEquals = true;
        $scope.hiMaxYear = new Date().getFullYear();
        $scope.changeHIDuration = function() {
            if($scope.hitiDurationEquals)
                $scope.hi.duration = $scope.travelInsurance.duration;
        }

        $scope.triggerHICoverage = function (id){
            $scope.insuranceDescResults[id] = !$scope.insuranceDescResults[id];
            for(var i = 0; i < $scope.hi.insuranceDescriptions.length; i++){
                if(id === $scope.hi.insuranceDescriptions[i].id) {
                    $scope.hi.insuranceDescriptions.splice(i, 1);
                    $scope.calculatePrice();
                    return;
                }
            }
            for(var i = 0; i < $scope.homeInsuranceDesc.length; i++){
                if($scope.homeInsuranceDesc[i].id === id) {
                    var tempObj = $scope.homeInsuranceDesc[i];
                    tempObj.typeOfRisk = null;
                    $scope.hi.insuranceDescriptions.push(tempObj);
                }
            }
            $scope.calculatePrice();
        };

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
        $scope.ci.carPackagesItems = [];

        $scope.triggerCICoverage = function(id){
            $scope.packagesResults[id] = !$scope.packagesResults[id];
            for(var i = 0; i < $scope.ci.carPackagesItems.length; i++){
                if(id === $scope.ci.carPackagesItems[i].id) {
                    $scope.ci.carPackagesItems.splice(i, 1);
                    $scope.calculatePrice();
                    return;
                }
            }
            for(var i = 0; i < $scope.carPackages.length; i++){
                if($scope.carPackages[i].id === id) {
                    var tempObj = $scope.carPackages[i];
                    tempObj.typeOfRisk = null;
                    $scope.ci.carPackagesItems.push(tempObj);
                }
            }
            $scope.calculatePrice();
        }

        // GENERAL
        $scope.isOptionDisabled = function(optionNumber) {
            return false; //TODO: Delete this line... Only for development purposes
            if (optionNumber != 0 && $scope.isOptionDisabled(optionNumber-1))    // Recursive check if previous option is disabled
                return true;                                                    // If so, next one is also disabled
            if (optionNumber == 1) {
                if ($scope.numberOfAdultPeople == 0 || $scope.firstForm.$invalid)
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
        var lastCalculatePriceCall = -1;
        $scope.calculatePrice = function () {
            var currentTimeMillis = new Date().getTime();
            if (lastCalculatePriceCall != -1 && currentTimeMillis < lastCalculatePriceCall + 100){
                console.log("Not calling price, it would be to frequent");
                return;
            }
            lastCalculatePriceCall = currentTimeMillis;
            $scope.travelInsurance.homeInsurances = $scope.isHomeWanted == false ? null : [ $scope.hi ];
            $scope.travelInsurance.carInsurances = $scope.isCarWanted == false ? null : [ $scope.ci ];
            var lt18 = 0;
            var btw1865 = 0;
            var gt65 = 0;
            if($scope.ageTypeOfRiskItems != undefined){
                var length = $scope.ageTypeOfRiskItems.length;
                for (var i = 0 ; i < length ; i++){
                    var code = $scope.ageTypeOfRiskItems[i].code;
                    if ($scope.ageTypeOfRiskItems[i].number != undefined){
                        if(code == "lt_18")
                            lt18 = $scope.ageTypeOfRiskItems[i].number;
                        else if (code == "btw_18_65")
                            btw1865 = $scope.ageTypeOfRiskItems[i].number;
                        else if (code == "gt_65")
                            gt65 = $scope.ageTypeOfRiskItems[i].number;
                    }
                }
            }
            console.log("Calling price");
            PriceService.fetchPrice($scope.travelInsurance, lt18, btw1865, gt65, $scope.refreshPrice, function(response){
                console.log("Unsuccessful try for fetching price");
            })
        }
        $scope.refreshPrice = function (response) {
            console.log("Successful fetched price: " + response.data);
            $scope.travelInsurance.totalPrice = response.data;
        }
        /*FINAL STEP - BUYING*/
        var afterLoading = function () {
            $scope.$watch('travelInsurance.duration', $scope.calculatePrice);
            $scope.$watch('travelInsurance.maxAmount', $scope.calculatePrice);
            $scope.$watch('travelInsurance.region', $scope.calculatePrice);
            $scope.$watch('travelInsurance.numberOfPeople', $scope.calculatePrice);
            $scope.$watch('ci.duration', $scope.calculatePrice);
            $scope.$watch('hi.duration', $scope.calculatePrice);
            $scope.$watch('hi.surfaceArea', $scope.calculatePrice);
            $scope.$watch('hi.age', $scope.calculatePrice);
            $scope.$watch('hi.estimatedValue', $scope.calculatePrice);
            $scope.$watch('isHomeWanted', $scope.calculatePrice);
            $scope.$watch('isCarWanted', $scope.calculatePrice);
        }
        $scope.buyInsurance =  function(){

            if(!$scope.isHomeWanted)
                $scope.travelInsurance.homeInsurances = null;
            else
                $scope.travelInsurance.homeInsurances = [ $scope.hi ];

            if(!$scope.isCarWanted)
                $scope.travelInsurance.carInsurances = null;
            else
                $scope.travelInsurance.carInsurances = [ $scope.ci ];
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
        afterLoading();
    }
})();
