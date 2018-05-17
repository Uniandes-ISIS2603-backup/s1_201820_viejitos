
(function (ng) {
    var mod = ng.module("citasClienteModule");
    mod.controller('citaClienteCtrl', ['$scope', '$http',

        function ($scope, $http) {

            $http.get("api/clientes/"+ sessionStorage.getItem("id")+"/citas").then(function (response) {
                $scope.citasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

