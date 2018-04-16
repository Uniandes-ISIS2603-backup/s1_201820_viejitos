/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng)
{
    //Definición del modulo
    var mod= ng.module("quejasModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/quejas/';
            // Mostrar la lista de quejas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/quejasList");
            // Definición del estado 'quejasList' donde se listan las quejas
            $stateProvider.state('quejasList', {
                // Url que aparecerá en el browser
                url: '/quejas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'quejas.list.html',
                        controller: 'quejaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


