(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.constant("enfermerosContext","api/enfermeros/1");
    mod.constant("clientesContext","api/clientes")
    mod.controller('solicitCtrl', ['$scope', '$http', 'serviciosContext', '$state', '$rootScope',
      
        function ($scope, $http, serviciosContext, $state, $rootScope) {
                  $rootScope.edit = false;


          
  
       ///                 $scope.data.finalizado=false;
       
   
    // $http.get(enfermerosContext).then(function (response) {
      //          $scope.enfermero = response.data;
        //    });
            
            
             
     $http.get(clientesContext+$rootScope.currentId).then(function (response) {
                $scope.cliente = response.data;
          });
            
            
            
            ///$scope.data.enfermero=$scope.enfermero;
     console.log($rootScope.currentUser);
     console.log($rootScope.currentId);
     console.log($scope.cliente);
    
  
         


            
            
            $scope.solicitServicio = function () {
                                      

                $http.post("api/servicios", $scope.data).then(function (response) {
                    
  
                    $state.go('seviciosList',  {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


