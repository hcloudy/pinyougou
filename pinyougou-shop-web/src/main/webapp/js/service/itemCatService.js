app.service("itemCatService",function ($http) {

    this.findByParentId = function (parentId) {
        return $http.get("../itemCat/findByParentId.do?parentId="+parentId);
    }

    //新增
    this.save = function (itemCat) {
        return $http.post("../itemCat/add.do",itemCat);
    }
    //修改
    this.update = function (itemCat) {
       return $http.post("../itemCat/update.do",itemCat);
    }

    this.findItemCatById = function (id) {
        return $http.get("../itemCat/findItemCat.do?id=" + id);
    }

    //获取分类列表
    this.findItemCatList = function (id) {
        return $http.get("../itemCat/findByParentId.do?parentId="+id);
    }
    //获取所有分类
    this.findAll = function () {
        return $http.get("../itemCat/findAll.do")
    }
})