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
            $stateProvider.state('pagosList', {
                url: '/pagos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.list.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


