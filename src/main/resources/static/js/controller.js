'use strict';

var CustomerController = function ($scope, $http) {
    $scope.customer = {};


    $scope.getCustomerList = function () {
        $http.get('client').success(function (customerList) {
            $scope.customers = customerList;
        });
    };

    $scope.saveCustomer = function (newCustomer) {
        $http.post('client/', newCustomer).success(function () {
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
        $scope.customer = {};
    };

    $scope.getCustomerList();
};