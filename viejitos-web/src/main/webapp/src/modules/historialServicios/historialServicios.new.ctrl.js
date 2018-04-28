(function (ng) {
    var mod = ng.module("historialServiciosModule");
    mod.controller('historialServiciosNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        
        function ($scope, $http, $state, $rootScope) {

            //$rootScope.edit = false;

            //$scope.data = {};
            
            //$scope.createServicio = function () {
             //   $http.post("api/servicios", $scope.data).then(function (response) {
              //      $state.go('historialServiciosList', {servicoId: response.data.id}, {reload: true});
               // });
            //};
        }
    ]);
}
)(window.angular);
