(function (ng){
    var mod=ng.module("historialServiciosModule")
    
    mod.controller('historialServiciosDetailCtrl', ["$scope", "$stateParams", "$http", function($scope, $stateParams, $http){
            
            $http.get("'http://localhost:8080/viejitos-web/api/servicios/"+$stateParams.id)
                    .then(function bien (response){
                        $scope.historialActual=response.data;
            })
            
    }])
    
})(window.angular)

