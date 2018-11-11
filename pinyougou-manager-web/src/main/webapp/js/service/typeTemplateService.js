app.service("typeTemplateService",function ($http) {

    this.search = function (page,size,searchTypeTemplate) {
        return $http.post("../typeTemplate/search.do?page="+page+"&size="+size,searchTypeTemplate);
    }

})