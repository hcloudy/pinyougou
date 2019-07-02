app.controller("indexController",function ($scope, indexService) {

    $scope.showLoginName = function () {
        indexService.showUserName().success(
            function (response) {
                $scope.loginName = response.username;
            }
        )
    }
})