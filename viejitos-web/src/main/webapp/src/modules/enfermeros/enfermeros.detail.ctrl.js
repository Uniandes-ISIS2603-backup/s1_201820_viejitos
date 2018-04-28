(function(ng){
    var mod = ng.module('enfermerosModule');
    mod.constant('enfermeroContext','api/enfermeros');
    mod.controller('enfermerosDetailCtrl', ['$scope', '$http', 'enfermeroContext','$state',
        function($scope, $http, enfermeroContext, $state){
             if($state.params.enfermeroId!==null && $state.params.enfermeroId!==undefined)
                 $http.get(enfermeroContext + '/' + $state.params.enfermeroId).then(function (response){
                    $scope.currentEnfermero = response.data[$state.params.enfermeroId];
            });
    }]);
})(window.angular);



