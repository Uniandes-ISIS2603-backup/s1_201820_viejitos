(function ( ng ) {
    var mod = ng.module( 'viejitosModule', [] );
    mod.constant( 'viejitosContext', 'api/viejitos' );
    mod.config( [ '$stateProvider', '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider ) {
        var basePath = 'src/modules/viejitos/';
        $urlRouterProvider.otherwise( '/viejitosList' );

        $stateProvider.state( 'viejitosList', {
            url: '/viejitos',
            views: {
                'mainView': {
                    controller: 'viejitosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'viejitos.list.html'
                }
            }
        } ).state( 'viejitosCreate', {
            url: '/viejitos/create',
            views: {
                'mainView': {
                    controller: 'viejitosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'viejitos.create.html'
                }
            }

        } ).state( 'viejitosEdit', {
            url: '/viejitos/:viejitoId',
            param: {
                viejitoId: null
            },
            views: {
                'mainView': {
                    controller: 'viejitosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'viejitos.create.html'
                }
            }
        } );
    } ] );

})( window.angular );

