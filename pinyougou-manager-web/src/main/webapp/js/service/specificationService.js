app.service("specificationService",function($http) {

    this.search = function(page,size,searchSpecification) {
        return $http.post("../specification/search.do?page="+page+"&size="+size,searchSpecification);
    }

    this.add = function(spec) {
        return $http.post("../specification/add.do",spec);
    }
    this.findSpecById = function (id) {
        return $http.get("../specification/findSpecById.do?id="+id);
    }

    this.update = function(spec) {
        return $http.post("../specification/update.do",spec);
    }

    this.del = function (ids) {
        return $http.get("../specification/delete.do?ids="+ids);
    }
    this.selectSpecificationLists = function () {
        return $http.get("../specification/selectSpecificationLists.do");
    }
});