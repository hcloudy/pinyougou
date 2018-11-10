app.controller("baseController",function($scope) {
    //勾选品牌
    $scope.selectIds = [];
    $scope.selects = function($event,id) {
        //$event代表input标签的源,$event.target代表input标签,通过判断复选框的checked属性来判断是否选中
        if($event.target.checked) {
            $scope.selectIds.push(id);
        }else {
            var index = $scope.selectIds.indexOf(id);//查找id在selectIds数组中的位置
            $scope.selectIds.splice(index,1); //splice是将数组中index位置的1个数据删除
        }
    }

    $scope.reload = true;
     //分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            if(!$scope.reload) {
                return;
            }
            $scope.reloadList();
            $scope.reload = false;
            setTimeout(function () {
                $scope.reload = true;
            },200);
        }
    }
    $scope.reloadList = function() {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }
})