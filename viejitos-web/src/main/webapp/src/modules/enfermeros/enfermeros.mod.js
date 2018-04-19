(function(ng){
    var mod = ng.module("enfermerosModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        var basePath = "src/modules/enfermeros/";
        
        $urlRouterProvider.otherwise("/enfermerosList");
        
        $stateProvider.state("enfermerosList",{
            url: '/enfermeros/list',
            views:{
                'mainView': {
                        templateUrl: basePath + 'enfermeros.list.html',
                        controller: 'enfermerosCtrl',
                        controllerAs: 'ctrl'
                    }
            }
        });
    }]);
})(window.angular);

