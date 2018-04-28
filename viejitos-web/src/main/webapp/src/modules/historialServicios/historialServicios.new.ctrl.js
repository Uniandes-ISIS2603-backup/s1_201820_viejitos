(function (ng) {
    var mod = ng.module("historialServiciosModule");
    mod.constant("historialServiciosContext", "api/servicios");
    mod.controller('historialServiciosNewCtrl', ['$scope', '$http', 'historialServiciosContext', '$state', '$rootScope',
        
        function ($scope, $http, historialServiciosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            $scope.createServicio = function () {
                $http.post(historialServiciosContext, $scope.data).then(function (response) {
                    $state.go('historialServiciosList', {Id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
