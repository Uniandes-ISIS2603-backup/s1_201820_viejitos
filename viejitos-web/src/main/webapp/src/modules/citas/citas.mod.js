
(function (ng)
{
    var mod = ng.module("citasModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
            $urlRouterProvider.otherwise("/citasList");
            $stateProvider.state('citas', {
                url: '/citas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'citas.html',
                        controller: 'citaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
                    .state('citasList', {
                        url: '/list',
                        parent: 'citas',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'citas.list.html'
                            }
                        }
                    })
                    .state('citasDetail', {
                        url: '/{citaId:int}/detail',
                        parent: 'citas',
                        param: {
                            citaId: null
                        },
                        views: {
                            'listView': {
                                templateUrl: basePath + 'citas.list.html',
                                controller: 'citaCtrl',
                                controllerAs: 'ctrl'
                            },
                            'detailView': {
                                templateUrl: basePath + 'citas.detail.html',
                                controller: 'citasDetailCtrl',
                                controllerAs: 'ctrl2'
                            }
                        }
                    })
                    .state('citasCreate', {
                        url: '/create',
                        parent: 'citas',
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'citas.new.html',
                                controller: 'citasNewCtrl'
                            }}});
        }
    ]);
})(window.angular);

