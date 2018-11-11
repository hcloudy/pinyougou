app.controller("typeTemplateController",function ($scope,$controller,typeTemplateService) {
    $controller("baseController",{$scope:$scope});
    $scope.searchTypeTemplate = {};

    $scope.search = function (page, size) {
        typeTemplateService.search(page,size,$scope.searchTypeTemplate).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

})