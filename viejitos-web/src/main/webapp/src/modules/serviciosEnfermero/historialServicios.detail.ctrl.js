(function (ng){
    var mod=ng.module("historialServiciosEnfermeroModule");
    
    mod.controller('historialServiciosEnfermeroDetailCtrl', ["$scope", "$stateParams", "$http", function($scope, $stateParams, $http){
            
            
            
           
 if((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null) ){ 
                $http.get('api/enfermeros/'+sessionStorage.getItem("id")+"/servicios").then(function (response) {
                $scope.historialServiciosRecordsEnfermero = response.data;
            });
        }
            $http.get("api/servicios/"+$stateParams.id)
                    .then(function (response){
                        $scope.historialActualEnfermero=response.data;
            });
            
    }]);
    
})(window.angular);
