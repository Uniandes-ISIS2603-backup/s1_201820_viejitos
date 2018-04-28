(function(ng){
    var mod = ng.module("calendariosModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        var basePath = "src/modules/calendarios/";
        
        $urlRouterProvider.otherwise("/calendariosList");
        
        $stateProvider.state("calendarios",{
                url: '/calendarios',
                abstract: true,
                views:{
                    'mainView': {
                            templateUrl: basePath + 'calendarios.html',
                            controller: 'calendariosCtrl',
                            controllerAs: 'ctrl'
                        }
                }
        }).state("calendariosList",{
                url:'/list',
                parent:'calendarios',
                views:{
                    'listView':{
                        templateUrl: basePath + 'calendarios.list.html',
                        controller:'calendariosCtrl',
                        controllerAs:'ctrl'
                        
                    }
                }
        }).state('calendariosCreate', {
                url: '/create',
                parent: 'calendarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calendario.create.html',
                        controller: 'calendarioNewCtrl'
                    }
                }});
    }]);
})(window.angular);