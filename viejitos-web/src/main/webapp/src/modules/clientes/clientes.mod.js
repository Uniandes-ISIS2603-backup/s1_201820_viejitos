/**
 * @ngdoc overview
 * @name clientes.module:clienteModule
 * @description
 * Definición del módulo de Angular de Cliente. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con el Cliente 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los clientes en la 
 * URL: 'localhost:8080/clientes/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar clientes), el controlador y la vista 
 * correspondiente.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("clienteModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/clientes/';
            // Mostrar la lista de clientes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/clientesList");
            // Definición del estado 'clientesList' donde se listan los clientes
            $stateProvider.state('clientes', {
                url: '/clientes',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'clientes.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clientesList', {
                url: '/list',
                parent: 'clientes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clienteDetail', {
                url: '/{clienteId:int}/detail',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'clientes.list.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'cliente.detail.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('clientesCreate', {
                url: '/create',
                parent: 'clientes',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'clientes.new.html',
                        controller: 'clienteNewCtrl'
                    }
                }});
        
        }]);
})(window.angular);



