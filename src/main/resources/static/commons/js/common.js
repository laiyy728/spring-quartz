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

function tableRender(elem, url, method, params, cols, curr) {
    layui.use(['table'], function () {
        var table = layui.table;
        table.render({
            elem: '#'+ elem,
            url: url,
            method: method,
            where: params, // 接口的其他参数
            response: {
                statusCode: 200
            },
            cols: cols,
            loading: true,
            page: {
                curr : curr
            },
            done:function (result, page, size) {
                curr = page;
            }
        })
    });
}

function tableReload(elem, url, method, params, curr) {
    layui.use('table', function () {
        var table = layui.table;
        table.reload(elem, {
            url : url,
            method: method,
            where : params,
            response: {
                statusCode: 200
            },
            loading: true,
            page: {
                curr : curr
            }
        })
    });
}
