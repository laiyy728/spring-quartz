layui.use(['layer', 'form','laydate'], function () {
    const layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;

    form.render('select')
    laydate.render({
        elem : '#startDate',
        type : 'datetime',
        value : now()
    })




});