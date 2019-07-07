app.service("goodService", function ($http){

    this.add = function (goods) {
        return $http.post("../good/save.do",goods);
    }
})