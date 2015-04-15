'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', ['ngRoute', 'angularUtils.directives.dirPagination']);
/*app.config(['$routeProvider', function($routeProvider) {
 $routeProvider.when('/',{
 templateUrl: 'templates/lista.html',
 controller: 'listaController'
 })
 }]);
 app.config(function(paginationTemplateProvider) {
 paginationTemplateProvider.setPath('dirPagination.tpl.html');
 });*/

app.controller('listaController', function ($scope, beneficiariosService) {
    $scope.currentPage = 1;
    $scope.pageSize = 10;
    $scope.beneficiarios = [];

    $scope.totalBeneficiarios = 0;
    $scope.beneficiariosPerPage = 20;
    $scope.apellido = null;
    $scope.pagination = {
        current: 1
    };
    $scope.busqueda = false;
    
    
    $scope.buscarBeneficiarios = function () {
        
        if (verificarApellido()) {
            $scope.busqueda = true;
            listarBeneficiarios($scope.apellido, 1);
            obtenerTotalBeneficiarios($scope.apellido);
        }else{
            $scope.busqueda = false;
             $scope.beneficiarios = [];
             $scope.totalBeneficiarios = 0;
        }

    }

    $scope.pageChanged = function (newPage) {
        listarBeneficiarios($scope.apellido, newPage);
    };

    function listarBeneficiarios(apellido, pageNumber) {
        beneficiariosService.getBeneficiarios(apellido, pageNumber)
                .success(function (data, status, headers) {
                    console.log(data);
                    $scope.beneficiarios = data;
                    //$scope.totalBeneficiarios = 200;
                }).error(function (data, status, headers) {
            console.log('error al cargar los datos');
        });
    }
    
    function obtenerTotalBeneficiarios(apellido){
        beneficiariosService.countBeneficiarios(apellido)
                    .success(function (data, status, headers) {
                        console.log(data);
                        $scope.totalBeneficiarios = data;
                    }).error(function (data, status, headers) {
                console.log('error al cargar los datos');
            });
    }
    
    function verificarApellido(){
        return $scope.apellido !== null && $scope.apellido!=='';
    }
    
    $scope.verificar = function (){
        return $scope.busqueda;
    }
    
    
});


app.service('beneficiariosService', function ($http) {
    var getBeneficiarios = function (apellido, pageNumber) {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/resources/beneficiarios/' + apellido + '/' + pageNumber + '/'
        });
    };

    var countBeneficiarios = function (apellido) {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/resources/beneficiarios/count/' + apellido
        });
    };
    return {
        getBeneficiarios: getBeneficiarios,
        countBeneficiarios: countBeneficiarios
    }
});
