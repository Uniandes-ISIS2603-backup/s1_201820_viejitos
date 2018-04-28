(function(ng){
    var mod = ng.module('enfermerosModule');
    mod.constant('enfermeroContext','api/enfermeros');
    mod.controller('calendariosDetailCtrl', ['$scope', '$http', 'enfermeroContext','$state',
        function($scope, $http, enfermeroContext, $state){
             if($state.params.enfermeroId!==null &&$state.params.enfermeroId!==undefined &&$state.params.franjaId!==null &&$state.params.franjaId!==undefined)
                 $http.get('api/enfermeros/1/calendariossemanales/').then(function (response){
                    $scope.currentCalendario = response.data;
                    $scope.franjasHorarias=$scope.currentCalendario.franjasHorarias;

                    
                   
                   var modResult = $state.params.franjaId % 10;
                   
                   var  id1= ($state.params.franjaId-modResult)/10;
                   $scope.currentFranja=$scope.currentEnfermero.calendario[id1].grupito[modResult];
                // $scope.currentClient=$scope.currentEnfermero.clientes[$state.params.franjaId];
                  $scope.currentServicio=$scope.currentEnfermero.servicios[$state.params.franjaId];
                  $scope.currentClient= $scope.currentServicio.cliente;
                  
                  
            // $scope.currentServicio=$scope.currentClient.servicios[0];
                 });
    }]);
})(window.angular);


