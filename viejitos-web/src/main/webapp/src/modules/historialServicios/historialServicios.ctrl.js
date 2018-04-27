
(function (ng) {
    var mod = ng.module("historialServiciosModule");
    mod.constant("historialServiciosContext", "api/historialServicios");
    mod.controller('historialServiciosCtrl', ['$scope', '$http', 'historialServiciosContext',
        
        function ($scope, $http, historialServiciosContext) {
            
                $http.get('http://localhost:8080/viejitos-web/api/servicios').then(function (response) {
                $scope.historialServiciosRecords = response.data;
            
            });
        }
    ]);
}
)(window.angular);

