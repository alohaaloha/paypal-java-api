(function() {
   angular.module('travelsafeapp')
   .service('StatusService', function($http){
   	return{
   		createPayment: function(obj, onSuccess, onError){
   		//MOVE THIS METHOD!
   		var req = {
   		    method: 'POST',
   		    url: '/api/paypal/create',
   		    data: obj
   		}
   		$http(req).then(onSuccess, onError);
   		}
   		,
   		executePayment: function(obj, onSuccess, onError){
           		var req = {
           		    method: 'POST',
           		    url: '/api/paypal/execute',
           		    data: obj
           		}
           		$http(req).then(onSuccess, onError);
        }
   	}
   });
})();
