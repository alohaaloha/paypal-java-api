 (function() {
     'use strict';

     angular
         .module('travelsafeapp')
         .config(stateConfig);

        stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$translateProvider'];

        function stateConfig($stateProvider, $urlRouterProvider, $translateProvider) {

        var eng =
        {
            about : "About us",
            address : "Address: ",
            addressPlchld : "Address ",
            amount : "Amount: ",
            amountTooltip : "Maximum amount of money that the insurance will cover.",
            buyInsurance : "Buy insurance",
            carInsurance : "Car insurance",
            chooseInsuranceCarrier : "Choose insurance carrier",
            choosePerson : "Choose person",
            chooseYourInsurance : "Choose your insurance",
            close : "Close",
            contact : "Contact",
            dateOfBirth : "Date of birth: ",
            dateOfBirthPlchld : "Date of birth ",
            editDetails : "Edit details",
            english : "English",
            fieldRequired : "This field is required.",
            firstName : "First name: ",
            firstNamePlchld : "First name ",
            home : "Home",
            homeInsurance : "Home insurance",
            informationForPerson : "Enter information for person ",
            insuranceDuration : "Insurance duration: ",
            insuranceDurationMinError : "The minimum duration is 1 day.",
            insuranceDurationTooltip : "Duration of an insurance. Number of days.",
            insurancePeopleDetailsText : "Insert travellers details:",
            insurances : "Insurances",
            invalidDateErrorMessage : "Invalid date format (yyyy/mm/dd or mm/dd/yyyy)",
            lastName : "Last name: ",
            lastNamePlchld : "Last name ",
            next : "Next",
            noCarrierChoosen : "No carrier choosen",
            numberOfPeople : "Number of people: ",
            numOfPeopleMinError : "The minimum number of people insured is 1.",
            numOfPeopleTooltip : "Number of people that will be covered by this insurance.",
            passportNumber : "Passport number: ",
            passportNumberPlchld : "Passport number ",
            person: "Person",
            personDetailsNotValidError: "Person's details are incomplete",
            personalID : "Personal ID: ",
            personalIDPlchld : "Personal ID ",
            pinMinLengthError : "Minimum pin length is 3",
            phoneNumber : "Phone number: ",
            phoneNumberPlchld : "Phone number ",
            region : "Region: ",
            regionPlchld : "e.g. Bahami",
            regionTooltip : "Region that you're traveling to.",
            serbian : "Serbian",
            step1 : "1",
            step2 : "2",
            step3 : "3",
            step4 : "4",
            step5 : "5",
            testing : "Testing",
            travelSafe : "Travel safe",
            travelSafeWithUs : "Travel safe with us",
            withUs : "with us!"
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
            contact: "Kontakt",
            editDetails: "Izmeni detalje",
            insurancePeopleDetailsText : "Unesite detalje o osiguranim putnicima:",
            close : "Zatvori",
            regionTooltip : "Region u koji putujete.",
            numOfPeopleTooltip : "Broj osoba koji će biti pokriveni osiguranjem.",
            amountTooltip: "Maksimalna količina novca koje će osiguranje pokriti.",
            insuranceDurationTooltip : "Trajanje putovanja. Broj dana za koje će osiguranje trajati.",
            fieldRequired : "Polje je obavezno.",
            insuranceDurationMinError : "Minimalno trajanje putovanja je 1.",
            numOfPeopleMinError : "Minimalan broj osiguranika je 1.",
            pinMinLengthError : "Minimalan broj karaktera pin-a je 3",
            invalidDateErrorMessage : "Neispravan format datuma (dd.mm.gggg)",
            personDetailsNotValidError: "Podaci o osobi su nepotpuni"
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