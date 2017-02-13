(function() {

   angular
       .module('travelsafeapp')
       .factory('PriceService', PriceService);

   PriceService.$inject = ['$http'];

   function PriceService($http){

        return{
            fetchPrice: function(obj, lt18, btw1865, gt65, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/TravelInsurances/price/' + lt18 + '/' + btw1865 + '/' + gt65,
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            }
        }

   }

})();
