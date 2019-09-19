<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量导入学生</title>
    <script src="../plugins/jquery/jquery-3.4.0.js"></script>
</head>
<body>
<form id="fileUploadForm" enctype="multipart/form-data">
    <h3>学生信息Excel</h3>
    <input type="file" name="file" required>
    <button type="submit">上传</button>
</form>
</body>
<script>
    $(function () {
        console.log("file upload");
        $("#fileUploadForm").submit(function (ev) {
            //阻止默认的事件行为
            ev.preventDefault();
            //发送ajax文件上传请求
            $.ajax({
                url:"../student/file/upload",
                type:"post",
                cache:false,
                //传入原生表单对象构建一个FormData对象
                data:new FormData($("#fileUploadForm")[0]),
                processData:false,
                contentType:false,
                //成功回调方法
                success:function (data) {
                    if(data.status === 1){
                        alert(data.data);
                    }else if(data.status === -1){
                        alert(data.data);
                    }
                },
                //ajax请求出现错误回调方法
                error:function (err) {
                    console.log(err);
                }
            });
        });
    })
</script>
</html>