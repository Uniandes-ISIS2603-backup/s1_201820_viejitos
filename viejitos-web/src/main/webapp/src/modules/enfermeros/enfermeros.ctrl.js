(function(ng){
    var mod = ng.module("enfermerosModule");
    mod.constant("enfermerosContext","api/enfermeros");
    mod.controller('enfermerosCtrl', ['$scope', '$http', 'enfermerosContext', '$state',
        function($scope, $http, enfermerosContext, $state){
            $http.get(enfermerosContext).then(function (response){
                $scope.enfermerosRecords = response.data;
            });
        //    if(($state.params.enfermeroId !==undefined) && ($state.params.enfermeroId !== null)){
        //        $http.get(enfermerosContext + '/' + $state.params.clienteId).then(function (response){
        //            $scope.currentEnfermero = response.data;
        //        });
        //    }
    }]);
})(window.angular);


