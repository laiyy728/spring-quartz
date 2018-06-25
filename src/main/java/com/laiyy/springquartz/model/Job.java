package com.laiyy.springquartz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author laiyy
 * @date 2018/6/7 19:03
 * @description
 */
@Entity
@Table(name = "t_job")
public class Job implements Serializable {

    private static final long serialVersionUID = 3276342691112652104L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 任务 key
     */
    @Column(name = "job_key")
    @NotBlank(message = "任务 key 不能为空")
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
     * 任务创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 任务开始时间
     */
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

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int year;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int month;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int day;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int hour;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int minute;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int second;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private int status;

    /**
     * 任务所属任务组名称
     */
    @Transient
    private String groupName;

    @Transient
    private String time;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobKey='" + jobKey + '\'' +
                ", runnerClassName='" + runnerClassName + '\'' +
                ", groupId=" + groupId +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", times=" + times +
                ", cron='" + cron + '\'' +
                ", runnerType=" + runnerType +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", status=" + status +
                ", groupName='" + groupName + '\'' +
                ", time='" + time + '\'' +
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
                year == job.year &&
                month == job.month &&
                day == job.day &&
                hour == job.hour &&
                minute == job.minute &&
                second == job.second &&
                status == job.status &&
                Objects.equals(jobKey, job.jobKey) &&
                Objects.equals(runnerClassName, job.runnerClassName) &&
                Objects.equals(createDate, job.createDate) &&
                Objects.equals(startDate, job.startDate) &&
                Objects.equals(cron, job.cron) &&
                Objects.equals(groupName, job.groupName) &&
                Objects.equals(time, job.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobKey, runnerClassName, groupId, createDate, startDate, times, cron, runnerType, year, month, day, hour, minute, second, status, groupName, time);
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
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
