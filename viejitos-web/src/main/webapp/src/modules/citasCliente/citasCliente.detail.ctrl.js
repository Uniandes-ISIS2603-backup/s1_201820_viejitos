(function (ng) {
    var mod = ng.module("citasClienteModule");
    mod.controller('citasClienteDetailCtrl', ['$scope', '$http', 'citasContext', '$state',
        function ($scope, $http, $state) {
            if (($state.params.citaId !== undefined) && ($state.params.citaId !== null)) {

                $http.get("api/clientes/"+ sessionStorage.getItem("id")+"/citas" + '/' +$state.params.citaId).then(function (response) {
                    $scope.currentCita = response.data;
                });
            }
        }
    ]);
})
(window.angular);