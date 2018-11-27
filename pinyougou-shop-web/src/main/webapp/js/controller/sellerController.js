app.controller("sellerController",function ($scope, sellerService) {
    $scope.add = function () {
        sellerService.add($scope.seller).success(
            function (response) {
                if(response.success) {
                    alert(response.message);
                    location.href = "/shoplogin.html";
                }else{
                    alert(response.message);
                }
            }
        )
    }
});