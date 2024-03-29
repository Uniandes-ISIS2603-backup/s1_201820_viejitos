(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.constant("enfermerosContext","api/enfermeros/1");
    mod.constant("clientesContext","api/clientes");
    mod.controller('solicitCtrl', ['$scope', '$http', 'serviciosContext', '$state', '$rootScope',
      
        function ($scope, $http, serviciosContext, $state, $rootScope) {
                  $rootScope.edit = false;

               $scope.idcliente=sessionStorage.getItem("id");
           
              if (($scope.idcliente !== undefined) && ($scope.idcliente !== null)) {
                $http.get("api/clientes/"  + $scope.idcliente).then(function (response) {
                    $scope.cliente = response.data;
                });
              }
                   
            $http.get("api/enfermeros").then(function (response){
                $scope.enfermeros = response.data;
            });
            
                $scope.data = {};
                $scope.data.finalizado="false";
                $scope.data.descripcion=$state.params.servicioDesc;
                if($state.params.servicioTipo!==null)
                    $scope.data.tipo=($state.params.servicioTipo).toString();


                $scope.solicitServicio = function () {
                    $http.post("api/servicios", $scope.data).then(function (response) {
                        $scope.serv = response.data.id;
                 
                        $http.post("api/clientes/" + sessionStorage.getItem("id")+"/servicios/"+$scope.serv).then(function(response){
                            $http.post("api/enfermeros/"+ $scope.data.enfermero+"/servicios/"+$scope.serv).then(function(response){
                                $state.go('serviciosList', {reload: true});
                            });
                        });
                    });
                };
            
            }
    ]);
}
)(window.angular);


