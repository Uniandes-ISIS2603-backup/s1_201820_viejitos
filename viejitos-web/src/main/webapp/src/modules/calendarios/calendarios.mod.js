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
                        templateUrl: basePath + 'calendarios.list.html'
                    }
                }
        }).state("calendariosDetail",{
                url:'/{calendarioId:int}/detail',
                parent:'calendarios',
                param:{
                    enfermeroId:null
                },
                views:{
                    'listView':{
                        templateUrl: basePath + 'enfermeros.list.html',
                        controller: 'enfermerosDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    detailView:{
                        templateUrl: basePath + 'enfermero.detail.html',
                        controller: 'enfermerosDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
        }).state("enfermeroFranja",{
            url:'/{enfermeroId:int}/{franjaId:int}/detailer',
            parent:'enfermeros',
            param:{
                franjaId:null,
                enfermeroId:null
                
                },
            views:{
                'listView':{
                    templateUrl:basePath+'enfermeros.list.html',
                    controller:'calendariosDetailCtrl',
                    controllerAs:'ctrl'
                },
                detailView:{
                    templateUrl:basePath+'enfermero.detail.html',
                     controller:'calendariosDetailCtrl',
                    controllerAs:'ctrl'
                },
                franjaDetailView:{
                      templateUrl:basePath+'calendario.detail.html',
                     controller:'calendariosDetailCtrl',
                    controllerAs:'ctrl'
                    
                }
            }
            
            
        }
        
            );
    }]);
})(window.angular);