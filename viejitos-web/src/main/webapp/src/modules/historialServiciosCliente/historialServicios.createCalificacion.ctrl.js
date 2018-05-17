(function (ng) {
    var mod = ng.module("historialServiciosClienteModule");
    mod.controller('historialServiciosClienteCreateCalificacionCtrl', ['$scope', '$http', '$state', '$rootScope', '$stateParams',
        
        function ($scope, $http, $state, $rootScope, $stateParams) {
            $rootScope.edit = false;

            $scope.idDetail=$stateParams.id
            $scope.data = {};
            
            $scope.createCalificacion = function () {
                $http.post("api/enfermeros/"+ $stateParams.id+ "/calificaciones", $scope.data).then(function (response) {
                    $state.go('historialServiciosClienteList', {calificacionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


