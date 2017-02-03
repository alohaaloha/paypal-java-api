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
            },
            getOrderById: function(orderId, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/TravelInsurances/'+orderId
                }
                $http(req).then(onSuccess, onError);
            }
        }

   }

})();
