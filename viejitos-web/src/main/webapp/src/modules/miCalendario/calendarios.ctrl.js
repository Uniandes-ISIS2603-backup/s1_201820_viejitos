(function(ng){
    var mod = ng.module('calendariosEnfermeroModule');
    mod.constant('calendarioContext','api/calendariossemanales');
    mod.controller('calendariosEnfermeroCtrl', ['$scope', '$http', 'calendarioContext',
        function($scope, $http, calendarioContext){
            $http.get('api/enfermeros/'+sessionStorage.getItem("id")+'/calendariossemanales/').then(function (response){
                
                $scope.calendariosRecords = response.data;
                console.log($scope);          
                $scope.currentCalendarioEnfermero=$scope.calendariosRecords;

                   // $scope.currentCalendario.franjasHorarias= $scope.calendariosRecords[j];
                   //$scope.franjasR=[];
                   $scope.franjasR = $scope.currentCalendarioEnfermero.franjasHorarias;
                                 
//$scope.franjasR=[];
                 //$scope.franjasR.push($scope.calendariosRecords.franjasHorarias);
console.log("####");
console.log($scope);
                    $scope.franjas = [];
                    //grupo es lo mas grande
                  
                      for (var i = 0; i < $scope.franjasR.length; i += 5)
                       {
                        $scope.franjas.push($scope.franjasR.slice(i, i + 5));
                        }
                        
        $scope.currentCalendarioEnfermero.franjasGanadoras=$scope.franjas;     
         $scope.currentCalendarioEnfermero.id=$scope.calendariosRecords[j].id; 
         console.log("!!!");
              console.log($scope);
            });
    }]);
})(window.angular);

