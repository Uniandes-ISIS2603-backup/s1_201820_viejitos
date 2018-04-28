(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citaDetailCtrl', ['$scope', '$http', 'citasContext', '$state',
        function ($scope, $http, citasContext, $state) {
            if (($state.params.medicoId !== undefined) && ($state.params.medicoId !== null)) {

                $http.get(citasContext + '/' +$state.params.citaId).then(function (response) {
                    $scope.currentCita = response.data;
                });
            }
        }
    ]);
})
(window.angular);