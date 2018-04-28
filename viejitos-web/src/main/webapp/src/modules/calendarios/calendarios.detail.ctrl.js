(function (ng) {
    var mod = ng.module("calendariosModule");
    mod.constant("calendariosContext", "api/authors");
    mod.controller('calendariosDetailCtrl', ['$scope', '$http', 'calendariosContext', '$state',
        /**
         * @ngdoc controller
         * @name authors.controller:authorDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo Autores. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} booksContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, calendariosContext, $state) {
            if (($state.params.calendarioId !== undefined) && ($state.params.calendarioId !== null)) {
                /**
                 * @ngdoc function
                 * @name getAuthorID
                 * @methodOf authors.controller:authorDetailCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentra el autor por ID en formato JSON.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * del autor o API donde se puede consultar.
                 */
                $http.get(calendariosContext + '/' + $state.params.calendarioId).then(function (response) {
                    $scope.currentCalendario = response.data;
                   
                });
            }
        }
    ]);
}
)(window.angular);

