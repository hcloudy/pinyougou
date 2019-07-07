app.service("uploadService",function ($http) {

    this.uploadFile = function () {
        var formdata = new FormData();
        formdata.append("file",file.files[0]); //file 文件上传框的name

        return $http({
            url: "../upload.do",
            method: "post",
            data: formdata,
            headers: {"Content-Type": undefined},
            //对表单进行二进制转化
            transformRequest: angular.identity
        });
    }
})