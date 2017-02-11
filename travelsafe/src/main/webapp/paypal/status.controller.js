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
