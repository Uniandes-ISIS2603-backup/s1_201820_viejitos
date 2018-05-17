(function (ng)
{
    //Definición del modulo
    var mod = ng.module("historialServiciosEnfermeroModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/serviciosEnfermero/';
            $urlRouterProvider.otherwise("/historialServiciosEnfermeroList");
            $stateProvider
                    .state('historialServiciosEnfermeroList', {
                        // Url que aparecerá en el browser
                        url: '/Enfermerolist',
                        parent: 'historialServiciosEnfermero',
                        views: {
                            'historialServiciosList': {
                                templateUrl: basePath + 'historialServicios.list.html',
                                controller: 'historialServiciosEnfermeroCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                    }

                    )
                    .state('historialServiciosEnfermero', {
                        url: '/historialServiciosEnfermero',
                        abstract: true,
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'historialServicios.html'
                            }
                        }
                    })

                    .state('historialServiciosEnfermeroDetail', {
                        url: '/:id',
                        parent: 'historialServiciosEnfermero',
                        params: {
                            id: null
                        },

                        views: {
                            'listView': {
                                templateUrl: basePath + 'historialServicios.list.html',
                                controller: 'historialServiciosEnfermeroCtrl',
                                controllerAs: 'ctrl'
                            },
                            'historialServiciosDetail': {
                                templateUrl: basePath + 'historialServicios.detail.html',
                                controller: 'historialServiciosEnfermeroDetailCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
                    })
                    .state('EnfermerocalificacionCreate', {
                        url: '/EnfermerocalificacionCreate',
                        params: {
                            id: null
                        },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'historialServicios.createCalificacion.html',
                                controller: 'historialServiciosEnfermeroCreateCalificacionCtrl',
                                controllerAs: 'ctrl'
                            }
                        }})
                            .state('cancelarServicioEnfermero', {
                        url: '/EnfermeroServicioCancel',
                        params: {
                            id: null
                        },
                        views: {
                            'mainView': {
                                templateUrl:  'src/modules/serviciosEnfermero/delete/historialServicios.delete.html',
                                controller: 'serviciosEnfermeroCancelCtrl',
                                controllerAs: 'ctrl'
                            }
                        }})
           
            
            ;
        }
    ]);
})(window.angular);
