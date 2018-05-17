(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies       
        'viejitosModule',
        'serviciosModule',
        'pagosModule',
        'clienteModule',
        'calificacionesModule',
        'enfermerosModule',
        'citasModule',
        'medicosModule',
        'calendariosModule',
        'historialServiciosModule',
        'loginModule',
        'historialServiciosClienteModule',
        'historialServiciosEnfermeroModule',
        'inicioModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
   
     app.run(['$rootScope',"$state", function ($rootScope,$state) {
             
          
           
                 
               
                var requireLogin = false;
                     //   $state.current.data.requireLogin
                var roles = []
                //$state.current.data.roles
 
                /**
                 * @ngdoc function
                 * @name isAuthenticated
                 * @methodOf mainApp.module:mainApp
                 * @description Esta función define si el usuario se encuentra
                 * dentro de su cuenta.
                 * @returns {Boolean} Verdadero si está dentro de su cuenta.
                 */
                $rootScope.isAuthenticated = function () {

                    if (sessionStorage.getItem("username") != null) {
                        $rootScope.currentUser = sessionStorage.getItem("name");
                        return true;
                    } else {
                        return false;
                    }
                };
              
                $rootScope.hasPermissions = function () {
                    if (($rootScope.isAuthenticated) && (roles.indexOf(sessionStorage.getItem("rol")) > -1)) {
                        return true;
                    } else {
                        return false;
                    }
                };
                
                $rootScope.isAdmin=function(){
                    if(($rootScope.isAuthenticated) && (sessionStorage.getItem("rol")=='admin')){
                        return true;
                    }
                    else{
                        return false;
                                     }
                };


                $rootScope.isCliente=function(){
                    if(($rootScope.isAuthenticated) && (sessionStorage.getItem("rol")=='cliente')){
                        return true;
                    }
                    else{
                        return false;
                                     }
                };
                $rootScope.isMedico=function(){
                    if(($rootScope.isAuthenticated) && (sessionStorage.getItem("rol")=='medico')){
                        return true;
                    }
                    else{
                        return false;
                                     }
                };
                $rootScope.isEnfermero=function(){
                    if(($rootScope.isAuthenticated) && (sessionStorage.getItem("rol")=='enfermero')){
                        return true;
                    }
                    else{
                        return false;
                                     }
                };



                if (requireLogin && (sessionStorage.getItem("username") === null)) {
                    event.preventDefault();
                    $state.go('login', $state.params);
                }

            

        }]);   
 
    
})(window.angular);

