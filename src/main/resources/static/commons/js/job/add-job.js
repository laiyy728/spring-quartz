layui.use(['layer', 'form','laydate'], function () {
    var layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;

    form.render('select')
    laydate.render({
        elem : '#startDate',
        type : 'datetime',
        value : now()
    })

    // 任务组渲染



});