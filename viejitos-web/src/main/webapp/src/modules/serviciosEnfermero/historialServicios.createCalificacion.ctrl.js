(function (ng) {
    var mod = ng.module("historialServiciosEnfermeroModule");
    mod.controller('historialServiciosEnfermeroCreateCalificacionCtrl', ['$scope', '$http', '$state', '$rootScope', '$stateParams',
        
        function ($scope, $http, $state, $rootScope, $stateParams) {
            $rootScope.edit = false;

            $scope.idDetail=$stateParams.id
            $scope.data = {};
            
            $scope.createCalificacion = function () {
                $http.post("api/clientes/"+ $stateParams.id+ "/calificaciones", $scope.data).then(function (response) {
                    $state.go('historialServiciosEnfermeroList', {calificacionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


