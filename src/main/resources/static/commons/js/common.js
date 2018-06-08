
layui.use(['jquery'], function () {
    // 导航栏 iframe 跳转
    var $ = layui.jquery;
    $(".layui-nav-child a").click(function () {
        $("#iframe").attr('data-url', '');
        var dataUrl = $(this).attr('data-url');
        $("#iframe").attr('src', dataUrl);
        $("#iframe").attr('data-url', dataUrl)
    });

});

// 日期初始化
function now() {
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth(); //得到月份
    var date = now.getDate(); //得到日期
    var day = now.getDay(); //得到周几
    var hour = now.getHours(); //得到小时
    var minu = now.getMinutes(); //得到分钟
    var sec = now.getSeconds(); //得到秒
    var MS = now.getMilliseconds(); //获取毫秒
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    if (MS < 100) MS = "0" + MS;
    var time = "";
    time = year + "-" + month + "-" + date + " " + hour + ":" + minu + ":" + sec;
    return time;
}