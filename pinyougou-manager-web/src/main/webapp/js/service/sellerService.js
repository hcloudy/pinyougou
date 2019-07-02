app.service("sellerService",function ($http) {

    this.search = function (page,size,sellerSearch) {
        return $http.post("../seller/search.do?page="+page+"&size="+size,sellerSearch);
    }

    this.findSellerById = function (sellerId) {
        return $http.get("../seller/findSellerById.do?sellerId="+sellerId);
    }

    this.updateStatus = function (sellerId, status) {
        return $http.get("../seller/updateStatus.do?sellerId=" + sellerId + "&status=" + status);
    }
})