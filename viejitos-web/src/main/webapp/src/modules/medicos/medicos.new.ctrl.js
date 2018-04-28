(function (ng) {
    var mod = ng.module("medicosModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicoNewCtrl', ['$scope', '$http', 'medicosContext', '$state', '$rootScope',

        function ($scope, $http, medicosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createMedico = function () {
                $http.post(medicosContext, $scope.data).then(function (response) {
                    $state.go('medicosList', {medicoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);