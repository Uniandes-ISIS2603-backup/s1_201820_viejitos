(function(ng){
    var mod = ng.module("calificacionesModule");
    mod.constant('calificacionContext','api/calificaciones');
    mod.controller('calificacionesCtrl', ['$scope', '$http', 'calificacionContext',
        function($scope, $http, calificacionContext){
            $http.get(calificacionContext).then(function (response){
                $scope.calificacionesRecords = response.data;
            });
    }]);
    
})(window.angular);

