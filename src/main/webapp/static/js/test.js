//发送ajax请求测试
$(function () {
    $("#btn").click(function (){
       $.ajax({
           url:"test/test",
           contentType:"application/json;charset=UTF-8",
           data:'{"key":"value","key2":"value2"}',
           dataType:"json",
           type:"post",
           success:function(data){
                //数据解析
           }
       });
    });
    //$("#send").click(sendIt());
    $("#send").click(function() {
        $.ajax({
            url:"./test/getMsg",
            data:"type=1",
            type:"get",
            success:function (data) {
                alert("success");
            }
        });
    });
    $("#error").click(function () {
        $.get("./test/exception",function (data,status) {
            if (status){
                alert("查询成功");
            }else {
                alert("查询失败");
            }

        });
    });
});
function send() {
    $.get("./test/exception",function (data,status) {
        if (status){
            alert("查询成功")
        }
        
    });
}

