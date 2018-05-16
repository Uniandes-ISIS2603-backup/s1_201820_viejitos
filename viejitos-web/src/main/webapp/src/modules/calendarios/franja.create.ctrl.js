(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("calendariosContext", "api/calendariossemanales");
    mod.controller('franjaNewCtrl', ['$scope', '$http', 'calendariosContext', '$state', '$rootScope',
        
        function ($scope, $http, calendariosContext, $state, $rootScope) {
         
            $rootScope.edit = false;
              console.log($state); 
            $scope.data = {};
          
             // $scope.idmostrar= $state.params.calendarioId;
             //var idCalendario = $state.params.calendarioId;
          
        
            $scope.createFranja = function () {
                $http.post('api/calendariossemanales/'+$state.params.calendarioId+'/franjashorarias', $scope.data).then(function (response) {
               $state.go('calendariosList', {reload: true});

                });
  };
        }
        
    ]);
}
)(window.angular);




