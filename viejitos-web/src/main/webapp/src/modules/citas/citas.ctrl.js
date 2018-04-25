
(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citaCtrl', ['$scope', '$http', 'citasContext',

        function ($scope, $http, citasContext) {

            $http.get(citasContext).then(function (response) {
                $scope.citasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


