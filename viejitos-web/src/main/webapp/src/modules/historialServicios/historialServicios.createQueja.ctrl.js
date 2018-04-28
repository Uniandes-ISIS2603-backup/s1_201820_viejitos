(function (ng) {
    var mod = ng.module("historialServiciosModule");
    mod.controller('historialServiciosCreateQuejaCtrl', ['$scope', '$http', '$state', '$rootScope', '$stateParams',
        
        function ($scope, $http, $state, $rootScope, $stateParams) {
            $rootScope.edit = false;

            $scope.idDetail=$stateParams.id
            $scope.data = {};
            
            $scope.createQueja = function () {
                $http.post("api/servicios/"+ $stateParams.id+ "/quejas", $scope.data).then(function (response) {
                    $state.go('historialServiciosList', {servicoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);