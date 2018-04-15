/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.controller('servicioCtrl', ['$scope', '$http', 'serviciosContext',
        /**
         * @ngdoc controller
         * @name servicios.controller:servicioCtrl
         * @description
         * Definición del controlador de Angular del módulo Servicios. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} serviciosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Servicios en el Backend.
         */
        function ($scope, $http, serviciosContext) {
            /**
             * @ngdoc function
             * @name getServicios
             * @methodOf servicios.controller:servicioCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los servicios en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los servicios o API donde se puede consultar.
             */
            $http.get('data/servicios.json').then(function (response) {
                $scope.serviciosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


