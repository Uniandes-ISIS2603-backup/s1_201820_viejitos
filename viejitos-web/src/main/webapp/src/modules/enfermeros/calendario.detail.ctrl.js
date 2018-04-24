(function(ng){
    var mod = ng.module('enfermerosModule');
    mod.constant('enfermeroContext','api/enfermeros');
    mod.controller('calendariosDetailCtrl', ['$scope', '$http', 'enfermeroContext','$state',
        function($scope, $http, enfermeroContext, $state){
             if($state.params.enfermeroId!==null &&$state.params.enfermeroId!==undefined &&$state.params.franjaId!==null &&$state.params.franjaId!==undefined)
                 $http.get('data/enfermeros.json').then(function (response){
                    $scope.currentEnfermero = response.data[$state.params.enfermeroId];
                     $scope.estesi = $state.params.enfermeroId;
                     
                   
                   var modResult = $state.params.franjaId % 10;
                   
                   var  id1= ($state.params.franjaId-modResult)/10;
                   $scope.currentFranja=$scope.currentEnfermero.calendario[id1].grupito[modResult];
                 $scope.currentClient=$scope.currentEnfermero.clientes[$state.params.franjaId];
                 $scope.currentServicio=$scope.currentClient.servicios[0];
                 });
    }]);
})(window.angular);


