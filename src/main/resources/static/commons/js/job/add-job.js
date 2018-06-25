layui.use(['layer', 'jquery', 'form', 'laydate'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        form = layui.form;

    form.render('select')
    laydate.render({
        elem: '#startDate',
        type: 'datetime'
    });


    // 任务组渲染
    form.on('submit(addJobSubmit)', function (data) {
        if (checkParams(data)) {
            $.ajax({
                url: '/api/job',
                type: 'POST',
                data: data.field,
                success: function (res) {
                    var code = res.code;
                    if (code === 200) {
                        layer.msg('成功', {time : 800}, function () {
                           layer.closeAll();
                        })
                    } else {
                        layer.msg(res.msg, {time: 800})
                    }
                }
            })
        }
    });


    form.on('radio(time)', function (data) {
        var value = parseInt(data.value)
        if (value === 1) {
            // 固定次数
            $("#times").show();
            $(".times").val(0);
            $("#cycle").show();
        } else if (value === -1) {
            // 永远运行
            $("#times").hide();
            $(".times").val(-1);
            $("#cycle").show();
        } else if (value === 0) {
            // 运行一次
            $("#times").hide();
            $(".times").val(0);
            $("#cycle").hide();
        }
    });

    // 自定义规则验证
    form.verify({
        year: function (value) {
            if (value < 0 || value > 9999) {
                return "请输入正确的年份";
            }
        },
        month: function (value) {
            if (value < 0 || value > 12) {
                return "请输入正确的月份";
            }
        },
        day: function (value) {
            if (value < 0 || value > 31) {
                return "请输入正确的日期";
            }
        },
        hour: function (value) {
            if (value < 0 || value > 23) {
                return "请输入正确的小时";
            }
        },
        minute: function (value) {
            if (value < 0 || value > 59) {
                return "请输入正确的分钟"
            }
        },
        second: function (value) {
            if (value < 0 || value > 59) {
                return "请输入正确的秒";
            }
        }
    });


    function checkParams(data) {
        var times = data.field.times;
        var year = data.field.year;
        var month = data.field.month;
        var day = data.field.day;
        var hour = data.field.hour;
        var minute = data.field.minute;
        var second = data.field.second;
        if (parseInt(times) !== 0) {
            // 如果不是运行一次
            if (parseInt(year) === 0 && parseInt(month) === 0 && parseInt(day) === 0 && parseInt(hour) === 0 && parseInt(minute) === 0 && parseInt(second) === 0) {
                layer.msg('请填写运行周期', {time: 800});
                return false;
            }
            if (parseInt(year) < 0 || parseInt(year) > 9999) {
                layer.msg('请填写正确的年份', {time: 800});
                return false;
            }
            if (parseInt(month) < 0 || parseInt(month) > 12) {
                layer.msg('请填写正确的月份', {time: 800});
                return false;
            }
            if (parseInt(day) < 0 || parseInt(day) > 31) {
                layer.msg('请输入正确的天数', {time: 800});
                return false;
            }
            if (parseInt(hour) < 0 || parseInt(hour) > 24) {
                layer.msg('请输入正确的小时', {time: 800});
                return false;
            }
            if (parseInt(minute) < 0 || parseInt(minute) > 60) {
                layer.msg('请输入正确的分钟数', {time: 800});
                return false;
            }
            if (parseInt(second) < 0 || parseInt(second) > 60) {
                layer.msg('请输入正确的秒数', {time: 800});
                return false;
            }
        }
        return true;
    }

});

