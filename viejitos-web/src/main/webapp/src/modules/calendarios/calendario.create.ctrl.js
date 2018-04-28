(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("calendariosContext", "api/calendarios");
    mod.controller('calendarioNewCtrl', ['$scope', '$http', 'calendariosContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name clientes.controller:clienteNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Clientes. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} clientesContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Clientes en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, clientesContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createCliente
             * @methodOf clientes.controller:clienteNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el cliente.
             * @param {Object} cliente Objeto con la nueva del cliente.
             */
            $scope.createCalendario = function () {
                $http.post(calendariosContext, $scope.data).then(function (response) {
                    $state.go('calendariosList', {calendarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);





