
(function (ng) {
    var mod = ng.module("historialServiciosEnfermeroModule");
    mod.constant("historialServiciosContext", "api/historialServiciosEnfermero");
    mod.controller('historialServiciosEnfermeroCtrl', ['$scope', '$http', 'historialServiciosContext',
        
        function ($scope, $http, historialServiciosContext) {
            if((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null) ){ 
                $http.get('api/enfermeros/'+sessionStorage.getItem("id")+"/servicios").then(function (response) {
                $scope.historialServiciosRecordsEnfermero = response.data;
            });
        }
    }
    ]);
}
)(window.angular);

