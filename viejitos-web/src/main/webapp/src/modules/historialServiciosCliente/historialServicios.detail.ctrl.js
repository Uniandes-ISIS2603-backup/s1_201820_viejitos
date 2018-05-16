(function (ng){
    var mod=ng.module("historialServiciosClienteModule");
    
    mod.controller('historialServiciosClienteDetailCtrl', ["$scope", "$stateParams", "$http", function($scope, $stateParams, $http){
            
            
            
           
 if((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null) ){ 
                $http.get('api/clientes/'+sessionStorage.getItem("id")+"/servicios").then(function (response) {
                $scope.historialServiciosRecordsCliente = response.data;
            });
        }
            $http.get("api/servicios/"+$stateParams.id)
                    .then(function (response){
                        $scope.historialActualCliente=response.data;
            });
            
    }]);
    
})(window.angular);
