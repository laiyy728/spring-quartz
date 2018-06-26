SpringBoot + Quartz 实现完全可控的定时任务调度，可自定义定时任务具体的实现类

<h1>后台采用：</h1><br />
    SpringBoot + Quartz + MySQL + Redis + JPA
    
----
    
前台采用：
    Beetl + Layui + Ajax + JS
    
    
---

运行一次：需要确定何时运行 <br/>
永远运行：需要确定运行周期 <br/>
运行固定次数：需要确定运行周期

运行周期：需要确定每多长时间运行一次



<h2>踩坑经历：</h2>
======
1. 全局获取任务：<br/>
    可以使用私有静态的 ConcurrentMap 保证线程安全，且可以随时取用，key 采用 JobKey，便于唯一定位；value 采用 JobDetail，便于直接取值使用

2. 获取全局 Trigger：<br/>
    使用私有静态 scheduler 作为管理者，使用 scheduler 的方法来获取某一个任务的所有信息
    
3. 使用 scheduler.getTrigger 和 scheduler.getJobDetail 遇到的问题：<br/>
    triggerKey，jobKey 不能使用创建 jobDetail 和 Trigger 时指定的 key。
    jobKey 规则：任务组.任务名称， 如：group1.job1
    triggerKey 规则：组名.触发器名称，如：group1.trigger1
    

    
* Trigger scheduler.getTrigger(triggerKey)：获取触发器信息
* List<Trigger> scheduler.getTriggerOfJob(jobKey)：获取对应任务 key 的所有触发器
* JobDetail scheduler.getJobDetail(jobKey)：获取任务信息
* List<String> scheduler.getJobGroupNames()：获取全部任务组名称
* List<String> scheduler.getTriggerGroupNames()：获取全部触发器组名称
* void scheduler.scheduleJob(JobDetail, Trigger)：添加一个任务
* void scheduler.interrupt(jobKey)：恢复一个任务
* void scheduler.deleteJob(jobKey)：删除一个任务
* void scheduler.pauseJob(jobKey)：暂停一个任务
* void scheduler.start()：开始所有任务
