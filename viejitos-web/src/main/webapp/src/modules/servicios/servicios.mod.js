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
            // Definición del estado 'serviciosList' donde se listan los servicios
            $stateProvider.state('servicios',{
                    
                     url: '/servicios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'servicios.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['admin', 'cliente','assistant']
                }
            
            }).state('serviciosList', {
                
                
                url: '/servicios/list',
                     data: {
                    requireLogin: false,
                    roles: ['admin','cliente']
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'servicios.list.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('serviciosSolicit', {
                
                
                url: '/servicios/solicit',
                parent:'servicios',
                        
                    data: {
                    requireLogin: true,
                    roles: ['cliente']
                },
            
                params: {
                    servicioTipo: null,
                    servicioDesc: null
                },
                views: {
                     'detailView': {
                        templateUrl: basePath + 'servicios.list.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    },
                    'listView':{
                  templateUrl: basePath + 'solicit/servicios.solicit.html',
                        controller: 'solicitCtrl'
                    }
                    
                    
                }
            });
        }
    ]);
})(window.angular);

