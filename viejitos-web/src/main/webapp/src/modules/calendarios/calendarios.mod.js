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
                }}).state('franjasCreate', {
                url: '/createfranja',
                parent: 'calendarios',
                   params: {
                    calendarioId: null
                },
                   views: {
                    'detailView': {
                        templateUrl: basePath + 'franja.create.html',
                        controller: 'franjaNewCtrl'
                    }
                }}).state('enfermeroAsign', {
                url: '/asigntoenfermero',
                parent: 'calendarios',
                   params: {
                    calendarioId: null
                },
                   views: {
                    'detailView': {
                        templateUrl: basePath + 'enfermero.assign.html',
                        controller: 'enfermeroUpdateCalCtrl'
                    }
                }})
            ;
    }]);
})(window.angular);