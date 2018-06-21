package com.laiyy.springquartz.controller.view;

import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.service.GroupService;
import com.laiyy.springquartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laiyy
 * @date 2018/6/8 15:05
 * @description 页面管理器
 */
@Controller
@RequestMapping(value = "/pro")
public class ViewController {

    @Autowired
    private JobService jobService;

    @Autowired
    private GroupService groupService;

    // ************************ 任务组页面 ************************

    @GetMapping(value = "group/list")
    public String grouoList(){
        return "group/list";
    }

    @GetMapping(value = "group/add")
    public String groupAdd(){
        return "group/add";
    }

    @GetMapping(value = "group/edit/{id}")
    public String groupEdit(@PathVariable int id, Model model){
        Group group = groupService.get(id);
        model.addAttribute(group);
        return "group/edit";
    }

    // ************************ 任务页面 ************************

    @GetMapping("/job/list")
    public String jobList(){
        return "job/list";
    }

    @GetMapping(value = "/job/add")
    public String addJob(){
        return "job/add-job";
    }

    @GetMapping(value = "job/edit/{id}")
    public String jobEdit(@PathVariable int id, Model model){
        Job job = jobService.get(id);
        model.addAttribute(job);
        return "job/edit";
    }

}

