package com.laiyy.springquartz.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/6/7 19:20
 * @description 任务运行状态
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

    public static Map<Integer, String> names (){
        Map<Integer, String> names = Maps.newLinkedHashMap();
        for (JobRunnerType jobRunnerType : values()) {
            names.put(jobRunnerType.type, jobRunnerType.name);
        }
        return names;
    }

    public static boolean hasRunnerType(int type){
        for (JobRunnerType value : values()) {
            if (value.type == type) {
                return true;
            }
        }
        return false;
    }

    public static String runnerName(int runnerType) {
        for (JobRunnerType jobRunnerType : values()) {
            if (jobRunnerType.type == runnerType) {
                return jobRunnerType.name;
            }
        }
        return "";
    }

    public int type(){
        return this.type;
    }

    public String value(){
        return this.name;
    }

    int type;

    String name;

    JobRunnerType(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
