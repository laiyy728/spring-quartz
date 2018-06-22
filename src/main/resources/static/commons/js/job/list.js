layui.use(['table', 'laypage', 'jquery'], function () {
    var table = layui.table;

    table.render({
        elem: '#job_list',
        url: '/api/job',
        method: 'get',
        where: {
            groupId: 0
        }, // 接口的其他参数
        response: {
            statusCode: 200
        },
        cols: [[
            {field: 'id', title: 'ID'},
            {field: 'jobKey', title: '任务 key'},
            {field: 'groupName', title: '任务组名称'},
            {
                field: '', title: '创建时间', templet: function (data) {
                    return timeFormat(data.createDate);
                }
            },
            {
                field: '', title: '开始时间', templet: function (data) {
                    return timeFormat(data.startDate);
                }
            },
            {field: 'times', title: '运行次数'},
            {field: 'cron', title: 'cron 表达式'},
            {field: 'runnerType', title: '任务运行状态'},
            {
                field: '', title: '操作', align: 'center', width: 450, templet: function (data) {
                    var option = '<a class="layui-btn layui-btn-xs" onclick="show(' + data.id + ')">查看</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="edit(' + data.id + ')">编辑</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="ttl(' + data.id + ')">剩余执行时间</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="now(' + data.id + ')">立即执行</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="pause(' + data.id + ')">暂停</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="cancel(' + data.id + ')">取消执行</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="del(' + data.id + ')">删除</a>';
                    return option;
                }
            }
        ]],
        loading: true,
        page: true
    });

});

function show(id) {
    showMsg(id + '-------查看')
}


function edit(id) {
    showMsg(id + " ---- 编辑")
}

function ttl(id) {
    showMsg(id + "----- 剩余时间")
}

function now(id) {
    showMsg(id + "--------立即执行")
}

function pause(id) {
    showMsg(id + "------暂停")
}

function cancel(id) {
    showMsg(id + "----------取消")
}

function del(id) {
    showMsg(id + "-------- 删除")
}

function addJob() {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.open({
            type: 2,
            title: '添加定时任务',
            area: ['100%', '100%'],
            content: '/view/job/add'
        });
    });
}


