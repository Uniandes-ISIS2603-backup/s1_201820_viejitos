(function (ng) {
    var mod = ng.module("medicosModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicosDetailCtrl', ['$scope', '$http', 'medicosContext', '$state',
        function ($scope, $http, medicosContext, $state) {
            if (($state.params.medicoId !== undefined) && ($state.params.medicoId !== null)) {

                $http.get(medicosContext + '/' + $state.params.medicoId).then(function (response) {
                    $scope.currentMedico = response.data;
                });
            }
        }
    ]);
})(window.angular);