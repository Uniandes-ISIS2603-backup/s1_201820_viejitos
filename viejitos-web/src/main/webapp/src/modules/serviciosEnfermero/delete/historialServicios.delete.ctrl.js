(function (ng) {
    var mod = ng.module("historialServiciosEnfermeroModule");
   
    mod.controller('serviciosEnfermeroCancelCtrl', ['$scope', '$http', '$state',
        /**
         * @ngdoc controller
         * @name authors.controller:authorDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Autores. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} authorsContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http,  $state) {
            var id = $state.params.id;
            console.log($state.params);
            /**
             * @ngdoc function
             * @name deleteAuthor
             * @methodOf authors.controller:authorDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar el autor.
             * @param {String} id El ID del autor a eliminar.
             */
            
            $scope.cancelServicio = function () {
                $http.delete('api/servicios/'+ id, {}).then(function (response) {
                    $state.go('historialServiciosEnfermeroList',  {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
