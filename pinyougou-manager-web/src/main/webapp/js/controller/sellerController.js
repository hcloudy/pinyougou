app.controller("sellerController",function ($scope,$http,$controller, sellerService) {

    $controller("baseController",{$scope:$scope})

    $scope.sellerSearch = {};
    $scope.search = function (page, size) {
        sellerService.search(page,size,$scope.sellerSearch).success(
            function (response) {
               $scope.list = response.rows;
               $scope.paginationConf.totalItems = response.total;
            }
        )
    }
})