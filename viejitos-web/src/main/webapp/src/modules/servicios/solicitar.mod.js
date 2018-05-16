(function(ng){
    var mod = ng.module("solicitarModule",['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            $urlRouterProvider.otherwise();
    }]);
})(window.angular);


