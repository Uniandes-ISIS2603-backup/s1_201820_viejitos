(function (ng){
    var mod=ng.module("quejasModule")
    
    mod.controller('quejaDetailCtrl', ["$scope", "$stateParams", "$http", function($scope, $stateParams, $http){
            
            $http.get("http://localhost:8080/viejitos-web/api/quejas/"+$stateParams.id)
                    .then(function bien (response){
                        $scope.quejaActual=response.data;
            })
    }])
    
})(window.angular)

