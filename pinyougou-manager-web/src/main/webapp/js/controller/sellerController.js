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

    $scope.findSellerById = function (sellerId) {
        sellerService.findSellerById(sellerId).success(
            function (response) {
                $scope.entity = response;
            }
        )
    }

    $scope.updateStatus = function (sellerId, status) {
        sellerService.updateStatus(sellerId,status).success(
            function (response) {
                if(response.success) {
                    $scope.reloadList();
                }else {
                    alert(response.message);
                }
            }
        )
    }
})