(function() {
    'use strict';

    angular.module('travelsafeapp',
        ['ngResource',
        'ui.router',
        'angularModalService',
        'ngSanitize',
        'ui.bootstrap',
        'pascalprecht.translate'])
        .run(run);


      run.$inject = ['$rootScope', '$state'];

      function run($rootScope, $state) {

      }


 })();
 (function() {
     'use strict';

     angular
         .module('travelsafeapp')
         .config(stateConfig);

        stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$translateProvider'];

        function stateConfig($stateProvider, $urlRouterProvider, $translateProvider) {

        var eng =
        {
            testing : "Testing",
            english : "English",
            serbian : "Serbian",
            home : "Home",
            insurances : "Insurances",
            buyInsurance : "Buy insurance",
            about : "About us",
            travelSafe : "Travel safe",
            travelSafeWithUs : "Travel safe with us",
            withUs : "with us!",
            chooseYourInsurance : "Choose your insurance",
            step1 : "1",
            step2 : "2",
            step3 : "3",
            step4 : "4",
            step5 : "5",
            informationForPerson : "Enter information for person ",
            insuranceDuration : "Insurance duration: ",
            region : "Region: ",
            numberOfPeople : "Number of people: ",
            amount : "Amount: ",
            choosePerson : "Choose person",
            chooseInsuranceCarrier : "Choose insurance carrier",
            firstName : "First name: ",
            lastName : "Last name: ",
            personalID : "Personal ID: ",
            dateOfBirth : "Date of birth: ",
            passportNumber : "Passport number: ",
            address : "Address: ",
            phoneNumber : "Phone number: ",
            homeInsurance : "Home insurance",
            carInsurance : "Car insurance",
            next : "Next",
            person: "Person",
            regionPlchld : "e.g. Bahami",
            firstNamePlchld : "First name ",
            lastNamePlchld : "Last name ",
            personalIDPlchld : "Personal ID ",
            dateOfBirthPlchld : "Date of birth ",
            passportNumberPlchld : "Passport number ",
            addressPlchld : "Address ",
            phoneNumberPlchld : "Phone number ",
            noCarrierChoosen : "No carrier choosen",
            contact: "Contact"
        };

        var ser =
        {
            testing : "Testiranje",
            english : "Engleski",
            serbian : "Srpski",
            home : "Početna",
            insurances : "Osiguranja",
            buyInsurance : "Kupi osiguranje",
            about : "O nama",
            travelSafe : "Putuj bezbedno",
            travelSafeWithUs : "Putujte bezbedno sa nama",
            withUs : "sa nama!",
            chooseYourInsurance : "Izaberite vaše osiguranje",
            step1 : "1",
            step2 : "2",
            step3 : "3",
            step4 : "4",
            step5 : "5",
            informationForPerson : "Unesite informacije za osobu ",
            insuranceDuration : "Trajanje osiguranja: ",
            region : "Region: ",
            numberOfPeople : "Broj osoba: ",
            amount : "Iznos: ",
            choosePerson : "Izaberite osobu",
            chooseInsuranceCarrier : "Izaberite nosioca osiguranja",
            firstName : "Ime: ",
            lastName : "Prezime: ",
            personalID : "JMBG: ",
            dateOfBirth : "Datum rođenja: ",
            passportNumber : "Broj pasoša: ",
            address : "Adresa: ",
            phoneNumber : "Broj telefona: ",
            homeInsurance : "Osiguranje kuće",
            carInsurance : "Osiguranje vozila",
            next : "Dalje",
            person : "Osoba",
            regionPlchld : "npr. Bahami",
            firstNamePlchld : "Ime ",
            lastNamePlchld : "Prezime ",
            personalIDPlchld : "JMBG ",
            dateOfBirthPlchld : "Datum rođenja ",
            passportNumberPlchld : "Broj pasoša ",
            addressPlchld : "Adresa ",
            phoneNumberPlchld : "Broj telefona ",
            noCarrierChoosen : "Nosilac nije izabran",
            contact: "Kontakt"
        };

        $translateProvider.translations('en',eng);
        $translateProvider.translations('sr',ser);
        $translateProvider.preferredLanguage('sr');
        $translateProvider.useSanitizeValueStrategy('escaped');

        $urlRouterProvider.otherwise('/home');

         $stateProvider
           .state('home', {
                   url: '/home',
                   views: {
                       'content': {
                           templateUrl: 'home/home.html',
                           controller: 'HomeController'
                       },
                       'navbar':{
                           templateUrl: 'navbar/navbar.html',
                           controller: 'NavbarController'
                       }
                   }
               });

    }


    })();
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

(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['$scope', '$state', 'StatusService'];

    function BuyController($scope, $state, StatusService) {

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

(function() {
'use strict';

angular
    .module('travelsafeapp')
    .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

         $stateProvider
           .state('buy', {
                  url: '/buy',
                  views: {
                      'content@': {
                          templateUrl: 'buy/buy.html',
                          controller: 'BuyController',
                          controllerAs: 'bc'
                      },
                      'navbar':{
                          templateUrl: 'navbar/navbar.html',
                          controller: 'NavbarController'
                      }
                  }
              })
    };


  })();
(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('StatusController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'StatusService', '$stateParams', '$location'];

    function HomeController ($scope, $state, StatusService, $stateParams, $location) {

       //-----------------------------------------------------------------------------------------------------------------------
       //RETURN SAMPLE
       //www.our_site.com/#/status/1?paymentId=PAY-1GP340808B396890ALA5TFWI&token=EC-1AT34617287816440&PayerID=YQPUVR6JEW8PC
       //OR
       //www.our_site.com/#/status/1?token=EC-1AT34617287816440

       //params:
       //$stateParams.orderId;
       //$location.search().paymentId;
       //$location.search().PayerID;
       //-----------------------------------------------------------------------------------------------------------------------

       //what to be shown - defalt: cancel page
       $scope.showValue=0;

       //you came from paypal site and you choose to continue
       //your order review will be displayed and buttons to confirm or cancel
       if($location.search().PayerID && $location.search().paymentId){
            $scope.showValue=1;
       }

       $scope.execute=function(){
            StatusService.executePayment(
            $stateParams.orderId,
            $location.search().paymentId,
            $location.search().PayerID,
            function(res){
                  console.log(res);
                  alertify.success('YAAAY YOU BOUGHT IT SUCCESSFULLY ');
                  //$state.go("home");
                  $scope.showValue=2;
            },
            function(res){
                  console.log(res);
                  alertify.error('Hmmm, there is an error. Check your PayPal balance.');
                  //TODO -  what next? display buy button again
            })
        }

        $scope.cancel=function(){
            //alert("TODO)");
            $state.go("home");
        }


$scope.click=0;
$scope.clickf = function(){
    $scope.click++;
}


    }
})();

(function() {

   angular
       .module('travelsafeapp')
       .factory('StatusService', StatusService);

   StatusService.$inject = ['$http'];

   function StatusService($http){

        return{
            createPayment: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/paypal/create',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            }
            ,
            executePayment: function(orderId, paymentId, payerId, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/paypal/execute/'+orderId+"/"+paymentId+"/"+payerId
                }
                $http(req).then(onSuccess, onError);
            }
        }

   }

})();

(function() {
'use strict';

angular
     .module('travelsafeapp')
    .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

         $stateProvider
           .state('status', {
                   url: '/status/{orderId}',
                   views: {
                       'content@': {
                           templateUrl: 'paypal/status.html',
                           controller: 'StatusController'
                       },
                       'navbar':{
                           templateUrl: 'navbar/navbar.html',
                           controller: 'NavbarController'
                       }
                   }
               })
    }

 })();