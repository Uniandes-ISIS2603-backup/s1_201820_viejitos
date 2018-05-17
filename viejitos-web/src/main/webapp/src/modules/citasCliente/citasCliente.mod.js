
(function (ng)
{
    var mod = ng.module("citasClienteModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citasCliente/';
            $urlRouterProvider.otherwise("/citasCList");
            $stateProvider.state('citasC', {
                url: '/citasC',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'citasCliente.html',
                        controller: 'citaClienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
                    .state('citasCList', {
                        url: '/list',
                        parent: 'citasC',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'citasCliente.list.html'
                            }
                        }
                    })
                    .state('citasCDetail', {
                        url: '/{citaClienteId:int}/detail',
                        parent: 'citasC',
                        param: {
                            citaId: null
                        },
                        views: {
                            'listView': {
                                templateUrl: basePath + 'citasCliente.list.html',
                                controller: 'citaClienteCtrl',
                                controllerAs: 'ctrl'
                            },
                            'detailView': {
                                templateUrl: basePath + 'citasCliente.detail.html',
                                controller: 'citasClienteDetailCtrl',
                                controllerAs: 'ctrl2'
                            }
                        }
                    }) 
                      .state('citasCCreate', {
                        url: '/create',
                        parent: 'citasC',
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'citasCliente.new.html',
                                controller: 'citasClienteNewCtrl'
                            }}})
                      .state('cancelarCitaCliente', {
                        url: '/ClienteCitaCancel',
                        params: {
                            id: null
                        },
                        views: {
                            'mainView': {
                                templateUrl:  'src/modules/CitasCliente/delete/citasCliente.delete.html',
                                controller: 'citasClienteCancelCtrl',
                                controllerAs: 'ctrl'
                            }
                        }});
        }
    ]);
})(window.angular);

