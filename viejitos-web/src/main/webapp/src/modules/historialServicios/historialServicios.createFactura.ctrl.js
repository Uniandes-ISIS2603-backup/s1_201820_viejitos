(function (ng) {
    var mod = ng.module("historialServiciosModule");
    mod.controller('historialServiciosCreateFacturaCtrl', ['$scope', '$http', '$state', '$rootScope', '$stateParams',
        
        function ($scope, $http, $state, $rootScope, $stateParams) {
            $rootScope.edit = false;

            $scope.idDetail=$stateParams.id
            $scope.data = {};
            
            $scope.createFactura = function () {
                $http.post("api/servicios/"+ $stateParams.id+ "/factura", $scope.data).then(function (response) {
                    $state.go('historialServiciosList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);



