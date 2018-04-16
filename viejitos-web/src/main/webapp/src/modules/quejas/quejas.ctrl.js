/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("quejasModule");
    mod.constant("quejasContext", "api/quejas");
    mod.controller('quejaCtrl', ['$scope', '$http', 'quejasContext',
        
        function ($scope, $http, quejasContext) {
            
                $http.get('data/quejas.json').then(function (response) {
                $scope.quejasRecords = response.data;
            
            });
        }
    ]);
}
)(window.angular);


