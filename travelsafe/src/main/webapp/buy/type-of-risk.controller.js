/**
 * Created by Dorian on 2/3/2017.
 */
(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('TypeOfRiskController', TypeOfRiskController);

    TypeOfRiskController.$inject = ['$scope', '$uibModalInstance', 'person', 'typeOfRisks', '$translate', 'risks'];

    function TypeOfRiskController($scope, $uibModalInstance, person, typeOfRisks, $translate, risks) {
        $scope.person = person;
        console.log("Modal controller dobio person:");
        console.log(person);

        $scope.typeOfRisks = typeOfRisks;
        console.log("Modal controller dobio typeOfRisks:");
        console.log(typeOfRisks);

        if(risks == undefined)
            $scope.risks = {};
        else
            $scope.risks = risks;


        // Closing and returning result
        $scope.dismissModal = function() {
            console.log("Closing modal...");
            console.log("Giving back person object:");
            console.log($scope.person);
            console.log("Giving back form validation:");

            var choosenRisks = [];

            typeOfRisks.forEach(sort);

            function sort(item, index){
                console.log($scope.risks[item.id]);
                if($scope.risks[item.id] != undefined) {
                    for(var i = 0; i < $scope.risks[item.id].length; i++){
                        choosenRisks.push(($scope.risks[item.id])[i]);
                    }

                }
            }
            $scope.person.items = choosenRisks;
            var result = {
                person: $scope.person,
                risks: $scope.risks
            }
            $uibModalInstance.close(result);
        }

        if($translate.use() == "sr")
            $scope.riskDisplayName = "name_srb";
        else if ($translate.use() == "en")
            $scope.riskDisplayName = "name_en";
    }
})();

