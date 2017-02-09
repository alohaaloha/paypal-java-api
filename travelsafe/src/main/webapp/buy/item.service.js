/**
 * Created by Dorian on 2/9/2017.
 */
(function() {

    angular
        .module('travelsafeapp')
        .factory('ItemService', ItemService);

    ItemService.$inject = ['$http'];

    function ItemService($http){

        return{
            getItemByTypeOfRiskCode: function(risk, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/ItemsByTypeOfRisk/'+risk,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            }
        }

    }

})();

