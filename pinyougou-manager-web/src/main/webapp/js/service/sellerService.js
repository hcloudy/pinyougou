app.service("sellerService",function ($http) {

    this.search = function (page,size,sellerSearch) {
        return $http.post("../seller/search.do?page="+page+"&size="+size,sellerSearch);
    }
})