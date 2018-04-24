
(function (ng)
{
    var mod = ng.module("medicosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/medicos/';
            $urlRouterProvider.otherwise("/list");
            $stateProvider
                    .state('medicos', {
                url: '/medicos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicos.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
                    .state('medicosList', {
                url: '/list',
                parent: 'medicos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'medicos.list.html'
                    }
                }
            })
                    .state('medicosDetail', {
                        url: '/{medicoId:int}/detail',
                        parent: 'medicos',
                        param: {
                            medicoId: null
                        },
                        views: {
                            'listView': {
                                templateUrl: basePath + 'medicos.list.html',
                                controller: 'medicoCtrl',
                                controllerAs: 'ctrl'
                            },
                            'detailView': {
                                templateUrl: basePath + 'medicos.detail.html',
                                controller: 'medicosDetailCtrl',
                                controllerAs: 'ctrl2'
                            }
                        }
            })
                    .state('medicosCreate', {
                        url: '/create',
                        parent: 'medicos',
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'medicos.new.html',
                                controller: 'medicoNewCtrl'
                            }
                        }})
                    ;
            }]);
        });
(window.angular);

