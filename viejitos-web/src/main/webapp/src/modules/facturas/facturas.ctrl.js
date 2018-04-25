/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("facturasModule");
    mod.constant("facturasContext", "api/facturas");
    mod.controller('facturaCtrl', ['$scope', '$http', 'facturasContext',

        function ($scope, $http, facturasContext) {

            $http.get('data/facturas.json').then(function (response) {
                $scope.facturasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

