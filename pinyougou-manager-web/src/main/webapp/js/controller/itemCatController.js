app.controller("itemCatController",function ($scope,$http,$controller,itemCatService,typeTemplateService) {

    $controller("baseController",{$scope:$scope});

    $scope.parentId = 0;

    //根据上级分类查询列表
    $scope.findByParentID = function (parentId) {
        $scope.parentId = parentId;
        itemCatService.findByParentId(parentId).success(
            function (response) {
                $scope.list = response;
            }
        )
    }


    //面包屑功能实现
    $scope.grade = 1;

    $scope.setGrade = function (value) {
        $scope.grade = value;
    }

    $scope.selectList = function (p_entity) {
        if($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }
        if($scope.grade == 2) {
            $scope.entity_1 = p_entity;
            $scope.entity_2 = null;
        }
        if($scope.grade == 3) {
            $scope.entity_2 = p_entity;
        }
        $scope.findByParentID(p_entity.id);
    }

    //新增和修改商品分类
    $scope.modify = function () {
        var serviceObject = null;
        if($scope.itemCat.id != null) {
            serviceObject = itemCatService.update($scope.itemCat);
        }else {
            $scope.itemCat.parentId = $scope.parentId;
            serviceObject = itemCatService.save($scope.itemCat);
        }
        serviceObject.success(
            function (response) {
                if(response.success) {
                    $scope.findByParentID($scope.parentId);
                }else {
                    alert(response.message);
                }
            }
        )
    }
    //获取模板类型下拉
    $scope.typeTemplateLists = {data:[]};
    $scope.findTemplateList = function () {
        typeTemplateService.templateOptionList().success(
            function (response) {
                $scope.typeTemplateLists = {data:response};
            }
        );
    }

    $scope.findOneById = function (id) {
        itemCatService.findItemCatById(id).success(
            function (response) {
                $scope.itemCat = response;
            }
        )
    }
})