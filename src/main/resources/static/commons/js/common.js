
layui.use(['jquery'], function () {
    var $ = layui.jquery;
    $(".layui-nav-child a").click(function () {
        $("#iframe").attr('data-url', '');
        var dataUrl = $(this).attr('data-url');
        $("#iframe").attr('src', dataUrl);
        $("#iframe").attr('data-url', dataUrl)
    });
});