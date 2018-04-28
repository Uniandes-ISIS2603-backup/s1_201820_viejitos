(function(ng){
    var mod = ng.module('calendariosModule');
    mod.constant('calendarioContext','api/calendariossemanales');
    mod.controller('calendariosCtrl', ['$scope', '$http', 'calendarioContext',
        function($scope, $http, calendarioContext){
            $http.get(calendarioContext).then(function (response){
                $scope.calendariosRecords = response.data;
            });
    }]);
})(window.angular);

