(function(ng){
    var mod = ng.module('calendariosModule');
    mod.constant('calendarioContext','api/calendariossemanales');
    mod.controller('calendariosCtrl', ['$scope', '$http', 'calendarioContext',
        function($scope, $http, calendarioContext){
            $http.get('api/calendariossemanales').then(function (response){
                $scope.calendariosRecords = response.data;
                
                $scope.currentCalendario=[];
                        for(var j in $scope.calendariosRecords )
                {
                $scope.currentCalendario.push($scope.calendariosRecords[j]);

                   // $scope.currentCalendario.franjasHorarias= $scope.calendariosRecords[j];
                    
                   $scope.franjasR = $scope.currentCalendario[j].franjasHorarias;
              

                    $scope.franjas = [];
                    //grupo es lo mas grande

                      for (var i = 0; i < $scope.franjasR.length; i += 5)
                       {
                        $scope.franjas.push($scope.franjasR.slice(i, i + 5));
                        }
  
        $scope.currentCalendario[j].franjasGanadoras=$scope.franjas;     
         $scope.currentCalendario[j].id=$scope.calendariosRecords[j].id;      
                    }
                
            });
    }]);
})(window.angular);

