'use strict';

var CustomerController = function ($scope, $http) {
    $scope.customer = {};
    $scope.error = false;
    $scope.errorMessage = '';


    $scope.getOneCustomer = function (c) {
        $http.get('client/searchBy/' + c).success(function (customerList) {
            $scope.error = false;
            $scope.customers = customerList;
        }).error(function () {
            $scope.error = true;
            $scope.errorMessage = 'The customer does not exist';
        });
    };

    $scope.getCustomerList = function () {
        $http.get('client').success(function (customerList) {
            $scope.error = false;
            $scope.customers = customerList;
        });
    };

    $scope.saveCustomer = function (newCustomer) {
        $http.post('client/', newCustomer).success(function () {
            $scope.error = false;
            $scope.getCustomerList();
            $scope.customer = {};
        });
    };

    $scope.removeCustomer = function (id) {
        $http.delete('/client/' + id).success(function () {
            $scope.getCustomerList();
        });
    };

    $scope.editCustomer = function (customer) {
        $scope.customer = customer;
    };

    $scope.resetForm = function () {
        $scope.error = false;
        $scope.customer = {};
    };

    $scope.getCustomerList();
};