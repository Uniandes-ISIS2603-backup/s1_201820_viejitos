(function(ng){
    var mod = ng.module("calendariosEnfermeroModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        var basePath = "src/modules/miCalendario/";
        
        $urlRouterProvider.otherwise("/calendariosEnfermeroList");

            $stateProvider.state("calendariosEnfermero",{
                url: '/calendariosEnfermero',
                abstract: true,
               data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                views:{
                    'mainView': {
                            templateUrl: basePath + 'calendarios.html',
                            controller: 'calendariosEnfermeroCtrl',
                            controllerAs: 'ctrl'
                        }
                }
        }).state("calendariosEnfermeroList",{
                url:'/list',
                parent:'calendariosEnfermero',
                 data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                views:{
                    'listView':{
                        templateUrl: basePath + 'calendarios.list.html',
                        controller:'calendariosEnfermeroCtrl',
                        controllerAs:'ctrl'
                        
                    }
                }
        })
    }]);
})(window.angular);