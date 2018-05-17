(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasDetailCtrl', ['$scope', '$http', 'citasContext', '$state',
        function ($scope, $http, citasContext, $state) {
            if (($state.params.citaId !== undefined) && ($state.params.citaId !== null)) {

                $http.get(citasContext + '/' + $state.params.citaId).then(function (response) {
                    $scope.currentCita = response.data;
                });
            }
        }
    ]);
})(window.angular);