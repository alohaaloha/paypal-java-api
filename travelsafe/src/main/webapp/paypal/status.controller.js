(function() {
    'use strict';

    angular
        .module('travelsafeapp')
        .controller('StatusController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'StatusService', '$stateParams', '$location', 'ItemService','$translate'];

    function HomeController ($scope, $state, StatusService, $stateParams, $location, ItemService, $translate) {

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

        initData();

        function initData(){
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
            });

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
        }

       //what to be shown - defalt: cancel page
       $scope.showValue=0;

       //you came from paypal site where you choose to continue - now you have 2 options - to cancel or execute payment
       //your order review will be displayed and buttons to confirm or cancel
       if($location.search().PayerID && $location.search().paymentId){
            $scope.showValue=1;
            StatusService.getOrderById(
                $stateParams.orderId,
                $location.search().paymentId,
                $location.search().PayerID,
                function(res){
                    $scope.travelInsurance = res.data;
                    console.log($scope.travelInsurance);

                    if(res.data.homeInsurances != null) {
                        if(res.data.homeInsurances.length != 0) {
                            for (var i = 0; i < res.data.homeInsurances[0].insuranceDescriptions.length; i++) {
                                $scope.insuranceDescResults[res.data.homeInsurances[0].insuranceDescriptions[i].id] = true;
                            }
                        }
                    }

                    if(res.data.carInsurances != null){
                        if(res.data.carInsurances.length != 0){
                            for(var i = 0; i < res.data.carInsurances[0].carPackagesItems.length; i++){
                                $scope.packagesResults[res.data.carInsurances[0].carPackagesItems[i].id] = true;
                            }
                        }
                    }

                    $scope.filteredParticipants = [];

                    for(var i = 0; i < res.data.participantInInsurances.length; i++){
                        var flag = false;
                        for(var j = 0; j < $scope.filteredParticipants.length; j++){
                            if(res.data.participantInInsurances[i].id == $scope.filteredParticipants[j].id)
                                flag = true;
                        }
                        if(!flag)
                            $scope.filteredParticipants.push(res.data.participantInInsurances[i]);
                    }

                },
                function(res){

                }
            );
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

            StatusService.cancel(
            $stateParams.orderId,
            $location.search().paymentId,
            $location.search().PayerID,
            function(res){
                $state.go("home");
            },
            function(res){

            }
            );
        }


$scope.click=0;
$scope.clickf = function(){
    $scope.click++;
}


    }
})();
