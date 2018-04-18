(function(ng){
    var mod = ng.module("calificacionesModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        var basePath = "src/modules/calificaciones/";
        
        $urlRouterProvider.otherwise("/calificacionesList");
        
        $stateProvider.state("calificacionesList",{
            url: '/calificaciones/list',
            views:{
                'mainView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl'
                    }
        }
    });
            
    }]);
 })(window.angular);

