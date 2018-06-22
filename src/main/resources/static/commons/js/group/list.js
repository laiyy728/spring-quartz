layui.use(['table', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery;

    table.render({
        elem: '#group_list',
        url: '/api/group',
        method: 'get',
        response: {
            statusCode: 200
        },
        cols: [[
            {field: 'id', title: 'ID'},
            {field: 'name', title: '组名称'},
            {
                field: '', title: '创建时间', templet: function (data) {
                    return timeFormat(data.createDate);
                }
            },
            {
                field: '', title: '组状态', templet: function (data) {
                    var status = data.status;
                    var option;
                    if (status === 1) {
                        // 禁用状态
                        option = '<input type="checkbox" name="status" lay-filter="status" value="1" lay-data="' + data.id + '" lay-skin="switch" lay-text="开启|关闭">'
                    } else {
                        option = '<input type="checkbox" name="status" lay-filter="status" value="2" lay-data="' + data.id + '" lay-skin="switch" lay-text="开启|关闭" checked>'
                    }
                    return option;
                }
            },
            {
                field: '', title: '操作', align: 'center', width: 450, templet: function (data) {
                    var option = '<a class="layui-btn layui-btn-xs" onclick="edit(' + data.id + ')">编辑</a>';
                    option += '<a class="layui-btn layui-btn-xs" onclick="del(' + data.id + ')">删除</a>';
                    return option;
                }
            }
        ]],
        loading: true,
        page: true
    });

    // 监听开关
    form.on('switch(status)', function (data) {
        if (this.checked) {
            $(this).val(2);
            $(this).attr('checked', 'true');
        } else {
            $(this).val(1);
            $(this).removeAttr('checked');
        }
        var id = $(this).attr('lay-data');
        $.ajax({
            url: '/api/group/status/' + id + "/" + $(this).val(),
            type: 'PUT',
            dataType : 'JSON',
            success : function (res) {
                console.log(res)
            }
        })
    });

});

function edit(id) {
    showMsg(id + " ---- 编辑")
}

function del(id) {
    showMsg(id + "-------- 删除")
}


function addGroup() {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.open({
            type: 2,
            title: '添加定时任务组',
            area: ['100%', '100%'],
            content: '/view/group/add'
        });
    });
}


