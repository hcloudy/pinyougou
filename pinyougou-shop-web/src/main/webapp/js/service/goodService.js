app.service("goodService", function ($http){

    //商品添加
    this.add = function (goods) {
        return $http.post("../good/save.do",goods);
    }
    //商品列表，只展示当前商家自己的
    this.findPage = function (page,size) {
        return $http.get("../good/findPage.do?page="+page+"&size="+size);
    }
    //查询
    this.search = function (tbGoods,page,size) {
        return $http.post("../good/search.do?page="+page+"&size="+size,tbGoods);
    }
});