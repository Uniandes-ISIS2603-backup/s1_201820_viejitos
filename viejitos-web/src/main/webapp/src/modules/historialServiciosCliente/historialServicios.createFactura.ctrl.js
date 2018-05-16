(function (ng) {
    var mod = ng.module("historialServiciosClienteModule");
    mod.controller('historialServiciosClienteCreateFacturaCtrl', ['$scope', '$http', '$state', '$rootScope', '$stateParams',
        
        function ($scope, $http, $state, $rootScope, $stateParams) {
            $rootScope.edit = false;

            $scope.idDetail=$stateParams.id
            $scope.data = {};
            
            $scope.createFactura = function () {
                $http.post("api/servicios/"+ $stateParams.id+ "/facturas", $scope.data).then(function (response) {
                    $state.go('historialServiciosClienteList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);



