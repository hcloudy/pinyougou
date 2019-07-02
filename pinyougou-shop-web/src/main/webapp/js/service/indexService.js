app.service("indexService",function ($http) {

    this.showUserName = function () {
        return $http.get("../index/showUserName.do")
    }
})