//controller层
app.controller("brandController",function($scope,$http,$controller,brandService){
    //$scope:$scope 将两个scope相等。
    $controller("baseController",{$scope:$scope});

    $scope.findAll = function() {
        brandService.findAll().success(function(response) {
            $scope.list = response;
        });
    }

    $scope.findPage = function(page,size) {
        brandService.findPage(page,size).success(function(response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
    //新增
    $scope.add = function() {
        var object = null;
        if($scope.entity.id != null) {
            object = brandService.update($scope.entity);
        }else {
            object = brandService.add($scope.entity);
        }
        object.success(
            function(response) {
                if(response.success) {
                    $scope.reloadList();
                }else{
                    alert(response.message);
                }
            }
        );
    }
    //根据品牌id查询品牌信息
    $scope.findById = function(id) {
        brandService.findById(id).success(
            function(response) {
                $scope.entity = response;
            }
        );
    }

    //删除
    $scope.del = function() {
        if(confirm("确定要删除吗?")){
            brandService.del($scope.selectIds).success(
                function(response) {
                    if(response.success){
                        alert(response.message);
                        $scope.reloadList();
                    }else {
                        alert(response.message);
                    }
                }
            );
        }
    }
    //查询
    $scope.searchBrand = {};
    $scope.search = function(page,size) {
        brandService.search(page,size,$scope.searchBrand).success(
            function(response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }
});