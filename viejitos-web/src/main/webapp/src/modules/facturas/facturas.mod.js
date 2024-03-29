/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng)
{
    var mod= ng.module("facturasModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/facturas/';
            $urlRouterProvider.otherwise("/facturasList");
            $stateProvider.state('facturasList', {
                url: '/facturas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.list.html',
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
