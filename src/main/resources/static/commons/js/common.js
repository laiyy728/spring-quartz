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

function showMsg(msg) {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.msg(msg, {time: 800})
    })
}

function timeFormat(date) {
    return dayjs(date).format('YYYY-MM-DD HH:mm:ss');
}

// 日期初始化
function now() {
    return dayjs().format('YYYY-MM-DD HH:mm:ss');
}

