
(function (ng)
{
    var mod= ng.module("citasModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
            $urlRouterProvider.otherwise("/citasList");
            $stateProvider.state('citasList', {
                url: '/citas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'citas.list.html',
                        controller: 'citaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

