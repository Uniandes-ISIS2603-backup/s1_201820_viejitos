(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clienteContext", "api/clientes");
    mod.controller('clienteCtrl', ['$scope', '$http', 'clienteContext', '$state',
        /**
         * @ngdoc controller
         * @name clientes.controller:clienteCtrl
         * @description
         * Definición del controlador de Angular del módulo Cliente. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} clienteContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Clientes en el Backend.
         */
        function ($scope, $http, clienteContext, $state) {
            /**
             * @ngdoc function
             * @name getClientes
             * @methodOf clientes.controller:clienteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los clientes en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los clientes o API donde se puede consultar.
             */
            $http.get('data/clientes.json').then(function (response) {
                $scope.clientesRecords = response.data;
            });
            if (($state.params.clienteId !== undefined) && ($state.params.clienteId !== null)) {
                $http.get(clienteContext + '/' + $state.params.clienteId).then(function (response) {
                    $scope.currentCliente = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


