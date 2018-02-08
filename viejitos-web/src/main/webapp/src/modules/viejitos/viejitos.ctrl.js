(function (ng) {

    var mod = ng.module("viejitosModule");

    mod.controller("viejitosCtrl", ['$scope', '$state', '$stateParams', '$http', 'viejitosContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de entidades de losViejitos está vacio
            $scope.records = {};
            // carga las entidades de losViejitos
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });

            // el controlador recibió un viejitoId ??
            // revisa los parámetros (ver el :viejitoId en la definición de la ruta)
            if ($stateParams.viejitoId !== null && $stateParams.viejitoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.viejitoId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un viejitoId
            } else {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('viejitosList');
                            });

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('viejitosList');
                            });
                }
                ;
            };

            this.deleteRecord = function (id) {
                $http.delete(context + "/" + id);
                $state.reload('viejitosList');

            };

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

