/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng)
{
    //Definición del modulo
    var mod= ng.module("serviciosModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/servicios/';
            // Mostrar la lista de servicios será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/serviciosList");
            // Definición del estado 'serviciosList' donde se listan los autores
            $stateProvider.state('serviciosList', {
                // Url que aparecerá en el browser
                url: '/servicios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'servicios.list.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

