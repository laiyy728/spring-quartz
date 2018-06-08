layui.use(['table', 'jquery'], function () {
    const table = layui.table;

    table.render({
        elem: '#test',
        url: '/pro/job',
        where: {
            groupId: 0
        }, // 接口的其他参数
        request: { // 页码参数名称修改

        },
        response: {
            dataName: 'content',
            countName: 'totalElements'
        },
        cols: [[
            {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'username', width: 80, title: '用户名'}
            , {field: 'sex', width: 80, title: '性别', sort: true}
            , {field: 'city', width: 80, title: '城市'}
            , {field: 'sign', title: '签名', minWidth: 150}
            , {field: 'experience', width: 80, title: '积分', sort: true}
            , {field: 'score', width: 80, title: '评分', sort: true}
            , {field: 'classify', width: 80, title: '职业'}
            , {field: 'wealth', width: 135, title: '财富', sort: true}
        ]],
        loading: true,
        page: true
    });

});

function addJob() {
    layui.use(['layer'], function () {
        const layer = layui.layer;
        layer.open({
            type : 2,
            title : '添加定时任务',
            area : ['100%', '100%'],
            content : '/pro/job/add'
        });
    });
}


