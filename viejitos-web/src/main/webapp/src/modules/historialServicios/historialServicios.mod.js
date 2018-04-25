(function (ng)
{
    //Definición del modulo
    var mod= ng.module("historialServiciosModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/historialServicios/';
            // Mostrar la lista de quejas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/historialServiciosList");
            // Definición del estado 'quejasList' donde se listan las quejas
            $stateProvider
                    .state('historialServiciosList', {
                        // Url que aparecerá en el browser
                        url: '/list',
                        parent: 'historialServicios',
                        views: {
                            'historialServiciosList': {
                                templateUrl: basePath + 'historialServicios.list.html',
                                controller: 'historialServiciosCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                        }
                     
                    )
                    .state('historialServicios',{
                        url: '/historialServicios',
                        abstract: true,
                        
                        views:{
                            'mainView':{
                                templateUrl: basePath +'historialServicios.html'
                            }
                        }
                    })
                    
                    .state('historialServiciosDetail',{
                        url:'/:id',
                        parent:'historialServicios',
                        params:{
                            id:null
                        },
                        
                        views:{
                            'historialServiciosDetail':{
                                templateUrl: basePath + 'historialServicios.detail.html',
                                controller: 'historialServiciosDetailCtrl'
                            }
                            
                        }
                    });
        }
    ]);
})(window.angular);


