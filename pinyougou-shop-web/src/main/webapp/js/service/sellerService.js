app.service("sellerService",function ($http) {
    this.add = function (seller) {
        return $http.post("/seller/add.do",seller);
    }
});