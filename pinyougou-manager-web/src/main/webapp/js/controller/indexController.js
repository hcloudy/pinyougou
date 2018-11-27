app.controller("indexController",function ($scope, indexService) {

    $scope.showName = function () {
        indexService.showName().success(
            function (response) {
                if(response.success) {
                    $scope.username = response.loginName;
                }
            }
        )
    }
});