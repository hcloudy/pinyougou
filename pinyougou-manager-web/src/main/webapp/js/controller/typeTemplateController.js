app.controller("typeTemplateController",function ($scope,$controller,typeTemplateService,brandService,specificationService) {
    $controller("baseController",{$scope:$scope});
    $scope.searchTypeTemplate = {};

    $scope.search = function (page, size) {
        typeTemplateService.search(page,size,$scope.searchTypeTemplate).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

    $scope.brandLists = {data:[]};

    $scope.findBrandOptionList = function () {
        brandService.brandOptionList().success(
            function (response) {
                $scope.brandLists = {data:response};
            }
        );
    }

    $scope.specificationLists = {data:[]};
    $scope.findSpecificationLists = function () {
        specificationService.selectSpecificationLists().success(
            function (response) {
                $scope.specificationLists = {data:response};
            }
        );
    }

    //添加属性行
    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({});
    }

    //删除属性行
    $scope.deleteTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index,1);
    }

    //保存模板或者更新模板
    $scope.add = function () {
        var object = null;
        if($scope.entity.id != null) {
            object = typeTemplateService.update($scope.entity);
        }else {
            object = typeTemplateService.save($scope.entity);
        }
        object.success(
            function (response) {
                if(response.success) {
                    $scope.reloadList();
                    alert(response.message);
                }else {
                    alert(response.message);
                }
            }
        )
    }
    //根据模板Id获取模板信息
    $scope.findTypeTemplateById = function (id) {
        typeTemplateService.findTypeTemplateById(id).success(
            function (response) {
                $scope.entity = response;
                //转换成json
                $scope.entity.specIds = JSON.parse($scope.entity.specIds);
                $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
                $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
            }
        )
    }

    //删除模板
    $scope.del = function () {
        if(confirm("确定要删除吗？")) {
            typeTemplateService.del($scope.selectIds).success(
                function (response) {
                    if(response.successs) {
                        $scope.reloadList();
                        alert(response.message);
                    }else {
                        alert(response.message);
                    }
                }
            )
        }
    }
})