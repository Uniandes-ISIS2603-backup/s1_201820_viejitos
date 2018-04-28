(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("calendariosContext", "api/calendariossemanales");
    mod.controller('franjaNewCtrl', ['$scope', '$http', 'calendariosContext', '$state', '$rootScope',
      
        function ($scope, $http, calendariosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
        
        
            $scope.createFranja = function () {
                $http.post('api/calendariossemanales/71/franjashorarias', $scope.data).then(function (response) {
     $state.go('calendariosList', {reload: true});

                });
            };
        }
    ]);
}
)(window.angular);




