/**
 * Created by CK on 08-04-2017.
 */

var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http){

    $scope.refreshCart = function () {
        console.log("inside refreshCart");
        $http.get('/emusicstore/rest/cart/'+$scope.cartId).success(function (data) {
            console.log("success data",data);
            console.log("data.cartItem",data.cartItem);
            $scope.cart=data;
        });
    };

    $scope.clearCart = function () {
        $http.delete('/emusicstore/rest/cart/'+$scope.cartId).success($scope.refreshCart());
    };

    $scope.initCartId = function (cartId) {
        console.log("inside initCartId",cartId);
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId) {
        console.log('productid:: ',productId)
        $http.put('/emusicstore/rest/cart/add/'+productId).success(function (data) {

            alert("Product successfully added to the cart!")
        });
    };

    $scope.removeFromCart = function (productId) {
        $http.put('/emusicstore/rest/cart/remove/'+productId).success(function (data) {
            $scope.refreshCart();
        });
    };

    $scope.calGrandTotal= function(){
        var grandTotal=0;

        for(var i=0; i<$scope.cart.cartItem.length; i++){
            grandTotal+=$scope.cart.cartItem[i].totalPrice;
        }
        return grandTotal;
    }
});