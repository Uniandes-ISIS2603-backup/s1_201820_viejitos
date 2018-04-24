(function (ng) {
    //llama al modulo llamado calendarioModul y lo asigna a mod
    var mod = ng.module("calendarioModule");
    
    //no estoy seguro que hace
    mod.constant("calendarioContext", "api/trainers");
    
    //crea el controlador
    mod.controller('calendarioCtrl', ['$scope', '$http', 'calendarioContext',
        function ($scope, $http, calendarioContext) {
            //llama un get que se queda esperando y lo asigna al scope
            $http.get('data/franjas.json').then(function (response) {
                //asigna una variable del scope al data retrieved
                $scope.franjasRecords = response.data;
            });
            
        }
    ]);
}
)(window.angular);
