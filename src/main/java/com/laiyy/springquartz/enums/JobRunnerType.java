package com.laiyy.springquartz.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/7 19:20
 * @description
 */
public enum JobRunnerType {

    // 已删除
    DELETED(0, "已删除"),
    // 已结束
    OVER(1, "已结束"),
    // 已取消
    CANCEL(2, "已取消"),
    // 已暂停
    PAUSE(3, "已暂停"),
    // 运行中
    RUNNING(4, "运行中");

    public List<String> names (){
        List<String> names = Lists.newArrayList();
        JobRunnerType[] values = JobRunnerType.values();
        for (JobRunnerType value : values) {
            names.add(value.name);
        }
        return names;
    }

    public

    int type;

    String name;

    JobRunnerType(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
