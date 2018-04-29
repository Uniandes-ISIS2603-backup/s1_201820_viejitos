/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng)
{
    var mod= ng.module("pagosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $urlRouterProvider.otherwise("/pagosList");
            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html',
                    }
                }
            }).state('pagosCreate', {
                url: '/create',
                
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.new.html',
                        controller: 'pagoNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }});
        }
    ]);
})(window.angular);


