(function(ng){
    var mod = ng.module('enfermerosModule');
    mod.constant('enfermeroContext','api/enfermeros');
    mod.controller('enfermerosCtrl', ['$scope', '$http', 'enfermeroContext',
        function($scope, $http, enfermeroContext){
            $http.get(enfermeroContext).then(function (response){
                $scope.enfermerosRecords = response.data;
            });
    }]);
})(window.angular);


