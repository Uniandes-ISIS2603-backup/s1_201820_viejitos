(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("enfermerosContext", "api/enfermeros");
    mod.controller('enfermeroUpdateCalCtrl', ['$scope', '$http', 'enfermerosContext', '$state', '$rootScope',

        function ($scope, $http,  $state, enfermerosContext, $rootScope) {
            $rootScope.edit = true;
             console.log($state);
            $scope.data = {};
         
            var idCalendario = $state.params.calendarioId;

        
          
            //$http.get(enfermerosContext).then(function (response) {
              
               // $scope.enfermerosRecords =response.data;
            ///});

  
            $scope.updateEnfermero = function () {
                
                $http.put(enfermerosContext +'1' +"/" +'calendariossemanales'+ idCalendario,$scope.data ).then(function (response) {
                    $state.go('enfermerosList',{reload: true});
                });
            }
        }]);
}
)(window.angular);
