app.controller("indexController",function ($scope, indexService) {

    $scope.showName = function () {
        indexService.showName().success(
            function (response) {
                    $scope.username = response.loginName;

            }
        )
    }
});