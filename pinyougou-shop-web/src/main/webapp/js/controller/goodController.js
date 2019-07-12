app.controller("goodController",function ($scope,$controller, goodService, uploadService,itemCatService,typeTemplateService) {
    $controller('baseController',{$scope:$scope});//继承

    $scope.itemCatList = [];
    //根据分类ID展示对应的分类名称
    $scope.findItemCatName = function() {
        itemCatService.findAll().success(
            function (response) {
                for (var i = 0;i <response.length;i ++) {
                    $scope.itemCatList[response[i].id] = response[i].name;
                }
            }
        );
    }

    //展示当前商家的商品列表
    $scope.findPage = function(page,size) {
        goodService.findPage(page,size).success(
            function (response) {
                $scope.goods = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
    $scope.searchTbGoods = {};
    //查询
    $scope.search = function(page,size) {
        goodService.search($scope.searchTbGoods,page,size).success(
            function (response) {
                $scope.goods = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
    $scope.auditStatusName = ['未申请','申请中','审核通过','已驳回'];
    //添加商品
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
    $scope.goods = {tbGoods:{}, tbGoodsDesc:{itemImages:[],specificationItems:[]} };
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
        typeTemplateService.findSpecList(newValue).success(
            function (response) {
                $scope.specList = response;
            }
        )
    })

    //选择规格属性的操作
    $scope.updateSpecAttribute = function ($event,name,value) {
        var object = $scope.searchObjectByKey($scope.goods.tbGoodsDesc.specificationItems,'attributeName',name);
        if (null != object) {
            if($event.target.checked) {
                object.attributeValue.push(value);
            }else{
                object.attributeValue.splice(object.attributeValue.indexOf(value),1);
                //如果选项都取消了，将此条记录移除
                if(object.attributeValue.length==0){
                    $scope.goods.tbGoodsDesc.specificationItems.splice($scope.goods.tbGoodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else{
            $scope.goods.tbGoodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
        }
    }

    //创建sku列表
    $scope.createItemList = function () {
        //初始化列表
        $scope.goods.tbItems = [{spec:{},price:'0',num:'9999',isDefault:'0',status:'0'}];
        var items = $scope.goods.tbGoodsDesc.specificationItems;
        for (var i = 0;i < items.length; i ++) {
            $scope.goods.tbItems = addColumn($scope.goods.tbItems,items[i].attributeName,items[i].attributeValue);
        }
    }
    addColumn = function (list, columnName, columnValue) {
        var newList = [];
        for (var i = 0; i < list.length; i ++) {
            var oldRow = list[i];
            for (var j=0; j < columnValue.length; j ++) {
                var newRow = JSON.parse(JSON.stringify(oldRow));
                newRow.spec[columnName] = columnValue[j];
                newList.push(newRow);
            }
        }
        return newList;
    }

})