(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies       
        'viejitosModule',
        'serviciosModule',
        'quejasModule',
        'pagosModule',
        'clienteModule',
        'calificacionesModule',
        'enfermerosModule',
         'citasModule',
        'medicosModule',
        'calendarioModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

