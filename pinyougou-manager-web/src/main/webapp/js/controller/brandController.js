//controller层
app.controller("brandController",function($scope,$http,brandService){
    $scope.findAll = function() {
        brandService.findAll().success(function(response) {
            $scope.list = response;
        });
    }
    //分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();
        }
    }
    $scope.reloadList = function() {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }
    $scope.findPage = function(page,size) {
        brandService.findPage(page,size).success(function(response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
    //新增
    $scope.add = function() {
        var object = null;
        if($scope.entity.id != null) {
            object = brandService.update($scope.entity);
        }else {
            object = brandService.add($scope.entity);
        }
        object.success(
            function(response) {
                if(response.success) {
                    $scope.reloadList();
                }else{
                    alert(response.message);
                }
            }
        );
    }
    //根据品牌id查询品牌信息
    $scope.findById = function(id) {
        brandService.findById(id).success(
            function(response) {
                $scope.entity = response;
            }
        );
    }
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
    //删除
    $scope.del = function() {
        brandService.del($scope.selectIds).success(
            function(response) {
                if(response.success){
                    $scope.reloadList();
                }else {
                    alert(response.message);
                }
            }
        );
    }
    //查询
    $scope.searchEntity = {};
    $scope.search = function(page,size) {
        brandService.search(page,size,$scope.searchEntity).success(
            function(response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }
});