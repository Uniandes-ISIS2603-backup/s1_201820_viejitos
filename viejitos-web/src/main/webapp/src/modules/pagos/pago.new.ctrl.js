(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoNewCtrl', ['$scope', '$http', 'pagosContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name pagos.controller:pagoNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Pagos. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} pagosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Pagos en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, pagosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createPago
             * @methodOf pagos.controller:pagoNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el pago.
             * @param {Object} cliente Objeto con la nueva del pago.
             */
            $scope.createPago = function () {
                $http.post(pagosContext, $scope.data).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


