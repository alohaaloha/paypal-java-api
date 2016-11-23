(function() {
   angular.module('travelsafeapp')
   .service('TestService', function($http){
   	return{
   		getStuff: function(onSuccess, onError){
   		var req = {
   		    method: 'GET',
   		    url: 'http://gturnquist-quoters.cfapps.io/api/random',
   		    data: {}
   		}
   		$http(req).then(onSuccess, onError);
   		}
   	}
   });
})();
