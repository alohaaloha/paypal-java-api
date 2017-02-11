(function() {

   angular
       .module('travelsafeapp')
       .factory('PriceService', PriceService);

   PriceService.$inject = ['$http'];

   function PriceService($http){

        return{
            fetchPrice: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/TravelInsurances/price',
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
