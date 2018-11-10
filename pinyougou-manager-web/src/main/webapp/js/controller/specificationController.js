app.controller("specificationController",function($scope,$controller,specificationService) {
    $controller("baseController",{$scope:$scope});

    $scope.searchSpecification = {};
    $scope.search = function(page,size) {
        specificationService.search(page,size,$scope.searchSpecification).success(
            function(response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

    // $scope.specification = {specification:{},specificationOptionList:[]}
    //增加规格参数行
    $scope.addTableRow = function () {
        $scope.spec.specificationOptionList.push({});
    }
    //删除规格参数行
    $scope.delTableRow = function (index) {
        $scope.spec.specificationOptionList.splice(index,1);
    }
    //添加规格名称和规格属性
    $scope.save = function () {
        var object;
        if($scope.spec.specification.id != null) {
            object = specificationService.update($scope.spec);
        }else {
            object = specificationService.add($scope.spec);
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

    //根据规格项id查询规格项和明细
    $scope.findSpecById = function (id) {
        specificationService.findSpecById(id).success(
            function (response) {
                $scope.spec = response;
            }
        )
    }

    //删除、批量删除
    $scope.del = function () {
        if(confirm("确定要删除吗？")) {
            specificationService.del($scope.selectIds).success(
                function (response) {
                    if(response.success) {
                        $scope.reloadList();
                    }else {
                        alert(response.message);
                    }
                }
            )
        }
    }
})