var curr = 1;

layui.use(['table', 'laypage', 'jquery', 'layer'], function () {

    var cols =  [[
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
        {
            field: 'times', title: '运行次数', templet: function (data) {
                var times = parseInt(data.times);
                if (times === -1) {
                    return "永远运行";
                } else if (times === 0) {
                    return "运行一次";
                } else {
                    return "运行 " + times + "次";
                }
            }
        },
        {field: 'cron', title: 'cron 表达式'},
        {
            field: '', title: '任务运行状态', templet: function (data) {
                var runnerType = data.runnerType;
                switch (runnerType) {
                    case 1:
                        return "已结束";
                    case 2:
                        return "已取消";
                    case 3:
                        return "已暂停";
                    case 4:
                        return "运行中";
                    default:
                        break
                }
            }
        },
        {
            field: '', title: '操作', align: 'center', width: 450, templet: function (data) {
                var option = '<a class="layui-btn layui-btn-xs" onclick="ttl(' + data.id + ')">剩余执行时间</a>';
                option += '<a class="layui-btn layui-btn-xs" onclick="now(' + data.id + ')">立即执行</a>';
                option += '<a class="layui-btn layui-btn-xs" onclick="pause(' + data.id + ')">暂停</a>';
                option += '<a class="layui-btn layui-btn-xs" onclick="resume(' + data.id + ')">恢复执行</a>';
                option += '<a class="layui-btn layui-btn-xs" onclick="cancel(' + data.id + ')">取消执行</a>';
                option += '<a class="layui-btn layui-btn-xs" onclick="del(' + data.id + ')">删除</a>';
                return option;
            }
        }
    ]];

    tableRender('job_list', '/api/job', 'get', {groupId:0}, cols, curr)
});


function ttl(id) {
    $.ajax({
        url: '/api/job/ttl/' + id,
        type: 'GET',
        success: function (res) {
            if (res.code === 200) {
                reload(res, res.data);
            } else {
                showMsg(res.msg)
            }
        }
    })
}

function now(id) {
    showMsg(id + "--------立即执行")
}

function pause(id) {
    $.ajax({
        url :'/api/job/pause/'+ id,
        type : 'PUT',
        success: function (res) {
            reload(res);
        }
    })
}

function resume(id) {
    $.ajax({
        url :'/api/job/resume/'+id,
        type:'PUT',
        success: function (res) {
            reload(res);
        }
    })
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

function reload(res, msg) {
    layui.use(['layer', 'table'], function () {
        var layer = layui.layer;
        if (!msg) {
            msg = "成功";
        }
        layer.msg(msg, {time: 800}, function () {
            tableReload('job_list', '/api/job', 'get', {groupId: 0},curr)
        })

    });
}



