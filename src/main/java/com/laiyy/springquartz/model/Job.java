package com.laiyy.springquartz.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

/**
 * @author laiyy
 * @date 2018/6/7 19:03
 * @description
 */
@Entity
@Table(name = "t_job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 任务 key
     */
    @Column(name = "job_key")
    private String jobKey = "";

    /**
     * 运行任务的 class 名称
     */
    @Column(name = "runner_class_name")
    private String runnerClassName = "";

    /**
     * 任务所属任务组 id
     */
    @Column(name = "group_id", columnDefinition = "INT DEFAULT 0")
    private int groupId;

    /**
     * 任务所属任务组名称
     */
    @Transient
    private String groupName;

    /**
     * 任务创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 任务开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 任务运行次数：
     * -1：永远运行
     * 0：运行一次
     * other：固定运行次数
     */
    private int times;

    /**
     * cron 表达式（可选）
     */
    private String cron;

    /**
     * 任务运行状态：
     * 0：已删除，
     * 1：已结束，
     * 2：已取消，
     * 3：已暂停，
     * 4：执行中，
     */
    @Column(name = "runner_type", columnDefinition = "INT DEFAULT 0")
    private int runnerType;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobKey='" + jobKey + '\'' +
                ", runnerClassName='" + runnerClassName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", times=" + times +
                ", cron='" + cron + '\'' +
                ", runnerType=" + runnerType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                groupId == job.groupId &&
                times == job.times &&
                runnerType == job.runnerType &&
                Objects.equals(jobKey, job.jobKey) &&
                Objects.equals(runnerClassName, job.runnerClassName) &&
                Objects.equals(groupName, job.groupName) &&
                Objects.equals(createDate, job.createDate) &&
                Objects.equals(startDate, job.startDate) &&
                Objects.equals(cron, job.cron);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobKey, runnerClassName, groupId, groupName, createDate, startDate, times, cron, runnerType);
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getRunnerClassName() {
        return runnerClassName;
    }

    public void setRunnerClassName(String runnerClassName) {
        this.runnerClassName = runnerClassName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public int getRunnerType() {
        return runnerType;
    }

    public void setRunnerType(int runnerType) {
        this.runnerType = runnerType;
    }
}
