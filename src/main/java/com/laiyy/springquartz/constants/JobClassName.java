package com.laiyy.springquartz.constants;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author laiyy
 * @date 2018/6/25 14:02
 * @description
 */
public class JobClassName {

    public static final Map<String, String> JOB_CLASS_MAPS = Maps.newConcurrentMap();

    static {
        JOB_CLASS_MAPS.put("定时打印", "com.laiyy.springquartz.quartz.job.PrintJob");
        JOB_CLASS_MAPS.put("数值累加", "com.laiyy.springquartz.quartz.job.NumberPlusJob");
    }

}
