/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoCtrl', ['$scope', '$http', 'pagosContext',

        function ($scope, $http, pagosContext) {

            $http.get('data/pagos.json').then(function (response) {
                $scope.pagosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


