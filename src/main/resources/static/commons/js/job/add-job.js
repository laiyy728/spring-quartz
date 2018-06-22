layui.use(['layer', 'jquery', 'form', 'laydate'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        form = layui.form;

    form.render('select')
    laydate.render({
        elem: '#startDate',
        type: 'datetime',
        value: now()
    });

    // 自定义规则验证
    form.verify({
        year: function (value) {
            if (value && (value <= 2017 || value > 9999)) {
                return "请输入正确的年份";
            }
        },
        month: function (value) {
            if (value && (value <= 0 || value > 12)) {
                return "请输入正确的月份";
            }
        },
        day: function (value) {
            if (value && (value <= 0 || value > 31)) {
                return "请输入正确的日期";
            }
        },
        hour: function (value) {
            if (value && (value < 0 || value > 23)) {
                return "请输入正确的小时";
            }
        },
        minute: function (value) {
            if (value && (value < 0 || value > 59)) {
                return "请输入正确的分钟"
            }
        },
        second: function (value) {
            if (value && (value < 0 || value > 59)) {
                return "请输入正确的秒";
            }
        }
    });

    // 任务组渲染
    form.on('submit(addJobSubmit)', function (data) {
        console.log(data.field)
    });

});

