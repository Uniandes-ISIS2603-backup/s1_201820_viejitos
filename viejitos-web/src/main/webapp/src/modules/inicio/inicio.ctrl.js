(function(ng){
    var mod = ng.module("inicioModule");
    mod.controller('calificacionesCtrl', ['$scope',
        function($scope){
            $http.get(calificacionContext).then(function (response){
                $scope.calificacionesRecords = response.data;
            });
    }]);
    
})(window.angular);




