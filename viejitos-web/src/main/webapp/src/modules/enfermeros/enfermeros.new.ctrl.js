(function (ng) {
    var mod = ng.module("enfermerosModule");
    mod.constant("enfermerosContext", "api/enfermeros");
    mod.controller('enfermerosNewCtrl', ['$scope', '$http', 'enfermerosContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name enfermeros.controller:'enfermerosNewCtrl'
         * @description
         * Definición del controlador auxiliar para crear Enfermeros. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} enfermerosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Enfermeros en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, enfermerosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createEnfermero
             * @methodOf enfermeros.controller:'enfermerosNewCtrl'
             * @description
             * Esta función utiliza el protocolo HTTP para crear el enfermero.
             * @param {Object} enfermero Objeto con la nueva del enfermero.
             */
            $scope.createEnfermero = function () {
                $http.post(enfermerosContext, $scope.data).then(function (response) {
                    $state.go('enfermerosList', {enfermeroId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);




