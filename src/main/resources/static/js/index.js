$(function () {
    $("#publishBtn").click(publish);
});

function publish() {
    $("#publishModal").modal("hide");
    //获取标题和内容
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    //发送异步请求给controller层
    $.post(//1.访问路径 2.发送的参数 3.处理返回的结果，结果为字符串需要转成object类型。JSON的定义就是把服务端的字符串转到页面上成为object类型
        CONTEXT_PATH + "/discuss/add",
        {"title": title, "content": content},
        //处理返回的结果，把字符串转成json类型
        function (data) {
            data = $.parseJSON(data);
            //在html提示框中显示返回消息，即controller中的msg
            $("#hintBody").text(data.msg);
            //把输入标题和内容框隐藏
            $("#hintModal").modal("show");
            setTimeout(function () {
                //显示提示框
                $("#hintModal").modal("hide");
                //如果code为0表示成功，则刷新页面
                if (data.code == 0){
                    window.location.reload();
                }
            }, 2000);
        }
    )

}