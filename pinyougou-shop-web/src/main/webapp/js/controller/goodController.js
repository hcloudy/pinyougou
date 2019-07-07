app.controller("goodController",function ($scope, goodService, uploadService,itemCatService,typeTemplateService) {
    $scope.add = function () {
        $scope.goods.tbGoodsDesc.introduction = editor.html();//获取富文本编辑器html代码
        goodService.add($scope.goods).success(
            function (response) {
                if(response.success) {
                    alert(response.message);
                    $scope.goods = {};
                    editor.html("");//清空富文本编辑器
                }else {
                    alert(response.message);
                }
            }
        )
    }
/*    $scope.image_entity.url = "";*/
    //上传图片
    $scope.upload = function () {
        uploadService.uploadFile().success(
            function (response) {
                if(response.success) {
                    $scope.image_entity.url = response.message;
                }else {
                    alert(response.message);
                }
            }
        )
    }
    //声明数据结构
    $scope.goods = {tbGoods:{}, tbGoodsDesc:{itemImages:[]} };
    //把图片添加到集合中
    $scope.add_image_list = function () {
        $scope.goods.tbGoodsDesc.itemImages.push($scope.image_entity);
    }
    //把图片从集合中删除
    $scope.remove_image = function (index) {
        $scope.goods.tbGoodsDesc.itemImages.splice(index,1);
    }

    //获取一级分类列表
    $scope.findItemCat1List = function () {
        itemCatService.findByParentId(0).success(
            function (response) {
                $scope.itemCatList = response;
            }
        )
    }

    //根据一级分类联动获取耳机分类
    $scope.$watch('goods.tbGoods.category1Id',function (newValue, oldValue) {
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCatList2 = response;
            }
        )
    })

    //根据二级分类联动获取耳机分类
    $scope.$watch('goods.tbGoods.category2Id',function (newValue, oldValue) {
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCatList3 = response;
            }
        )
    })

    //显示模板Id，即type_id
    $scope.$watch('goods.tbGoods.category3Id',function (newValue, oldValue) {
        itemCatService.findItemCatById(newValue).success(
            function (response) {
                $scope.goods.tbGoods.typeTemplateId = response.typeId;
            }
        );
    })

    //显示品牌下拉列表
    $scope.$watch('goods.tbGoods.typeTemplateId',function (newValue, oldValue) {
        typeTemplateService.findTypeTemplateById(newValue).success(
            function (response) {
                $scope.typeTemplate = response;
                $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
                //扩展属性
                $scope.goods.tbGoodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            }
        )
    })
})