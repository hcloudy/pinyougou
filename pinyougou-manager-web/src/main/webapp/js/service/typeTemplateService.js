app.service("typeTemplateService",function ($http) {

    this.search = function (page,size,searchTypeTemplate) {
        return $http.post("../typeTemplate/search.do?page="+page+"&size="+size,searchTypeTemplate);
    }
    
    this.save = function (entity) {
        return $http.post("../typeTemplate/add.do",entity);
    }

    this.findTypeTemplateById = function (id) {
        return $http.get("../typeTemplate/findTypeTemplateById.do?id="+id);
    }

    this.update = function (entity) {
        return $http.post("../typeTemplate/update.do",entity);
    }
    this.del = function(ids) {
        return $http.get("../typeTemplate/delete.do?ids="+ids);
    }
})