(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("calendariosContext", "api/calendariossemanales");
    mod.controller('calendarioNewCtrl', ['$scope', '$http', 'calendariosContext', '$state', '$rootScope',
      
        function ($scope, $http, calendariosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
        
        
            $scope.createCalendario = function () {
                $http.post(calendariosContext, $scope.data).then(function (response) {
                   // $state.go('calendariosList', {calendarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);





