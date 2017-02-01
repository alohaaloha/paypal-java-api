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
            accomodation : "Accomodation",
            address : "Address: ",
            addressPlchld : "Address ",
            allTravelTime : "All travel time",
            amount : "Amount: ",
            amountTooltip : "Maximum amount of money that the insurance will cover.",
            buyInsurance : "Buy insurance",
            carDetailsText : "Car related information",
            carInsurance : "Car insurance",
            carInsuranceDetailsText : "Road assistance details",
            carrier : "Carrier",
            carrierTooltip : "Insurance must have one person who is insurance carrier",
            chooseInsuranceCarrier : "Choose insurance carrier",
            choosePerson : "Choose person",
            chooseYourInsurance : "Choose your insurance",
            ciBrand : "Vehicle brand",
            ciChassisNumber : "Chassis number",
            ciOwnerFirstName : "Owner's first name",
            ciOwnerLastName : "Owner's last name",
            ciOwnerPin : "Owner's personal ID",
            ciRegistrationNumber : "Registration number",
            ciType : "Vehicle type",
            ciYear : "Year of manufacture ",
            ciYearMinError : "Not covering vehicles manufactured before 1900",
            ciYearMaxError : "Not covering not yet manufactured vehicles",
            clear : "Clear",
            close : "Close",
            contact : "Contact",
            coverage : "Coverage",
            dateOfBirth : "Date of birth: ",
            dateOfBirthMaxError : "The person must be born",    // Je l' ovo ok vreme na engleskom? :D
            dateOfBirthPlchld : "Date of birth ",
            editDetails : "Edit details",
            english : "English",
            fieldRequired : "This field is required.",
            firstName : "First name: ",
            firstNamePlchld : "First name ",
            hiAddress : "Property address",
            hiDurationMaxError : "Home insurance cannot last longer than travel duration",
            hiEstimatedValue : "Estimated value",
            hiEstimatedValueMin : "Estimated value must be greater than 100 (scraped radishes)", //TODO: What should we do about scraped radishes?
            hiOwnerFirstName : "Owner's first name",
            hiOwnerLastName : "Owner's last name",
            hiOwnerPin : "Owner's personal ID",
            hiSurfaceArea : "Surface area",
            hiSurfaceAreaMinError : "Minimum surface area is 10 m^2",
            hiYear : "Year of construction",
            hiYearMinError : "Not covering properties built before 1800",
            hiYearMaxError : "Not covering not yet built properties",
            home : "Home",
            homeInsurance : "Home insurance",
            homeInsuranceDetailsText : "Home insurance details",
            informationForPerson : "Enter information for person ",
            insuranceDuration : "Insurance duration: ",
            insuranceDurationMinError : "The minimum duration is 1 day.",
            insuranceDurationTooltip : "Duration of an insurance. Number of days.",
            insurancePeopleDetailsText : "Insert travellers details:",
            insurances : "Insurances",
            invalidDateErrorMessage : "Invalid date format (yyyy/mm/dd or mm/dd/yyyy)",
            iWantHomeInsurance : "I want home insurance",
            iWantCarInsurance : "I want road assistance",
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
            personDetailsNotValidError: "*Person's details are incomplete",
            personalID : "Personal ID: ",
            personalIDPlchld : "Personal ID ",
            pinMinLengthError : "Minimum pin length is 3",
            phoneNumber : "Phone number: ",
            phoneNumberPlchld : "Phone number ",
            region : "Region: ",
            regionPlchld : "e.g. Bahami",
            regionTooltip : "Region that you're traveling to.",
            repair : "Repair",
            serbian : "Serbian",
            step1 : "1",
            step2 : "2",
            step3 : "3",
            step4 : "4",
            step5 : "5",
            testing : "Testing",
            today : "Today",
            towing : "Towing",
            transport : "Transport",
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
            carrier : "Nosilac", //TODO: Nosilac, ugovarac, ili ... ?
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
            personDetailsNotValidError: "*Podaci o osobi su nepotpuni",
            carrierTooltip : "Jedna osoba mora biti nosilac osiguranja",
            dateOfBirthMaxError : "Osoba mora biti rođena",
            iWantHomeInsurance : "Želim da osiguram kuću/stan",
            coverage : "Pokriće",
            clear : "Očisti",
            today : "Danas",
            allTravelTime : "Sve vreme puta",
            hiDurationMaxError : "Kućno osiguranje ne može trajati duže od puta",
            homeInsuranceDetailsText : "Unesite detalje za kućno/stambeno osiguranje",
            hiSurfaceArea : "Površina stana",
            hiSurfaceAreaMinError : "Minimalna površina stana/kuće je 10 m^2",
            hiYear : "Godina gradnje",
            hiYearMinError : "Osiguranje ne pokriva objekte starije od 1800. godine",
            hiYearMaxError : "Osiguranje ne pokriva neizgrađene objekte",
            hiEstimatedValue : "Procenjena vrednost",
            hiEstimatedValueMin : "Procenjena vrednost mora biti viša od 100 (rotkvica struganih)", //TODO: Šta ćemo za rotkvice stugane?
            hiOwnerFirstName : "Ime vlasnika",
            hiOwnerLastName : "Prezime vlasnika",
            hiOwnerPin : "JMBG vlasnika",
            hiAddress : "Adresa objekta",
            iWantCarInsurance : "Želim mogućnost pružanja pomoći na putu",
            carInsuranceDetailsText : "Podaci u vezi osiguranja pružanja pomoći na putu",
            carDetailsText : "Podaci o vozilu",
            towing : "Šlepovanje",
            repair : "Popravka",
            transport : "Transport",
            accomodation : "Smeštaj",
            ciBrand : "Marka vozila",
            ciType : "Tip vozila",
            ciYear : "Godina proizvodnje",
            ciYearMinError : "Osiguranje ne pokriva vozila starija od 1900. godine",
            ciYearMaxError : "Osiguranje ne pokriva još uvek neproizvedena vozila",
            ciRegistrationNumber : "Registarski broj",
            ciChassisNumber : "Broj šasije",
            ciOwnerFirstName : "Ime vlasnika",
            ciOwnerLastName : "Prezime vlasnika",
            ciOwnerPin : "JMBG vlasnika"
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