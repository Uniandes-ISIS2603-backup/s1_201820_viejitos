
(function (ng) {
    var mod = ng.module("historialServiciosClienteModule");
    mod.constant("historialServiciosContext", "api/historialServiciosCliente");
    mod.controller('historialServiciosClienteCtrl', ['$scope', '$http', 'historialServiciosContext',
        
        function ($scope, $http, historialServiciosContext) {
            if((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null) ){ 
                $http.get('api/clientes/'+sessionStorage.getItem("id")+"/servicios").then(function (response) {
                $scope.historialServiciosRecordsCliente = response.data;
                console.log($scope.historialServiciosRecordsCliente);
            });
        }
    }
    ]);
}
)(window.angular);

