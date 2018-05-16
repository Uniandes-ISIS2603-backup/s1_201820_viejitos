(function (ng)
{
    //Definición del modulo
    var mod = ng.module("historialServiciosClienteModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/historialServiciosCliente/';
            // Mostrar la lista de quejas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/historialServiciosClienteList");
            // Definición del estado 'quejasList' donde se listan las quejas
            $stateProvider
                    .state('historialServiciosClienteList', {
                        // Url que aparecerá en el browser
                        url: '/Clientelist',
                        parent: 'historialServiciosCliente',
                        views: {
                            'historialServiciosList': {
                                templateUrl: basePath + 'historialServicios.list.html',
                                controller: 'historialServiciosClienteCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                    }

                    )
                    .state('historialServiciosCliente', {
                        url: '/historialServiciosCliente',
                        abstract: true,
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'historialServicios.html'
                            }
                        }
                    })

                    .state('historialServiciosClienteDetail', {
                        url: '/:id',
                        parent: 'historialServiciosCliente',
                        params: {
                            id: null
                        },

                        views: {
                            'listView': {
                                templateUrl: basePath + 'historialServicios.list.html',
                                controller: 'historialServiciosClienteCtrl',
                                controllerAs: 'ctrl'
                            },
                            'historialServiciosDetail': {
                                templateUrl: basePath + 'historialServicios.detail.html',
                                controller: 'historialServiciosClienteDetailCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                    })
                     .state('ClientequejaCreate', {
                        url: '/ClientequejaCreate',
                        params: {
                            id: null
                        },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'historialServicios.createQueja.html',
                                controller: 'historialServiciosClienteCreateQuejaCtrl',
                                controllerAs: 'ctrl'
                            }
                        }})
                    .state('ClientefacturaCreate', {
                        url: '/ClientefacturaCreate',
                        params: {
                            id: null
                        },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'historialServicios.createFactura.html',
                                controller: 'historialServiciosClienteCreateFacturaCtrl',
                                controllerAs: 'ctrl'
                            }
                        }});
        }
    ]);
})(window.angular);