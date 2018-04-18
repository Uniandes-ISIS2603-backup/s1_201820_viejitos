(function (ng) {
    
    
    //crea en mod un modulo
    var mod = ng.module("calendarioModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/calendarios/';
            
            
            //estado por defecto
            $urlRouterProvider.otherwise("/calendarioList");
            
            
            //este es el nombre del estado a mostrar
            $stateProvider.state('calendariosList', {
                
                
                //url donde esta el estado calendariosList
                url: '/calendarios/list',
                 views: {
                    'mainView': {
                        //ruta del archivo del estado calendarioList en la carpeta
                        templateUrl: basePath + 'calendarios.list.html',
                        //nombre del controlador
                        controller: 'calendarioCtrl',
                        //tipo de controlador
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
