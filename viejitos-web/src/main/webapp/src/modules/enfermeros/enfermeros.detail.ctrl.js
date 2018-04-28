(function (ng) {
    var mod = ng.module('enfermerosModule');
    mod.constant('enfermeroContext', 'api/enfermeros/1/calendariossemanales/');
    mod.controller('enfermerosDetailCtrl', ['$scope', '$http', 'enfermeroContext', '$state',
        function ($scope, $http, enfermeroContext, $state) {
            if ($state.params.enfermeroId !== null && $state.params.enfermeroId !== undefined)
                $http.get('api/enfermeros/1/calendariossemanales/').then(function (response) {
                    console.log($scope);

                    $scope.currentCalendario = response.data;

                    $scope.franjasR = $scope.currentCalendario.franjasHorarias;
                    console.log($scope);

                    $scope.franjas = [];
                    //grupo es lo mas grande

                    for (var i = 0; i < $scope.franjasR.length; i += 5)
                    {
                        $scope.franjas.push($scope.franjasR.slice(i, i + 5));

                    }


                });
        }]);
})(window.angular);



