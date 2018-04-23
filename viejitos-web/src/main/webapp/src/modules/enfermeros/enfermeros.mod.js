(function(ng){
    var mod = ng.module("enfermerosModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        var basePath = "src/modules/enfermeros/";
        
        $urlRouterProvider.otherwise("/enfermerosList");
        
        $stateProvider.state("enfermeros",{
                url: '/enfermeros',
                abstract: true,
                views:{
                    'mainView': {
                            templateUrl: basePath + 'enfermeros.html',
                            controller: 'enfermerosCtrl',
                            controllerAs: 'ctrl'
                        }
                }
        }).state("enfermerosList",{
                url:'/list',
                parent:'enfermeros',
                views:{
                    'listView':{
                        templateUrl: basePath + 'enfermeros.list.html'
                    }
                }
        }).state("enfermerosDetail",{
                url:'/{enfermeroId:int}/detail',
                parent:'enfermeros',
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

