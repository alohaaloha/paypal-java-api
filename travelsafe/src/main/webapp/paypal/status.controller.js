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
       //$stateParams.orderId);
       //$location.search().paymentId;
       //$location.search().PayerID;
       //-----------------------------------------------------------------------------------------------------------------------

       //what to be shown
       $scope.showValue=0;

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
                  $state.go("home");

            },
            function(res){
                  console.log(res);
                  alertify.error('Hmmm, there is an error. Check your PayPal balance.');
            })
        }

        $scope.cancel=function(){
            //alert("TODO)");
            $state.go("home");
        }



    }
})();
