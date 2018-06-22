package com.laiyy.springquartz.controller;

import com.laiyy.springquartz.base.BaseController;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.service.GroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laiyy
 * @date 2018/6/21 16:39
 * @description
 */
@Controller
@RequestMapping("/api/group")
public class GroupController extends BaseController<GroupService> {

    /**
     * 任务组列表
     * @param status 状态
     * @param name 名称
     * @param page 当前页
     * @param limit 每页显示条数
     * @return 分页信息
     */
    @GetMapping
    @ResponseBody
    public Page<Group> groups(@RequestParam(required = false, defaultValue = "-1") int status,
                              @RequestParam(required = false) String name,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "15") int limit){
        if (StringUtils.isNotBlank(name)) {
            return service.findGroupByStatusAndNameLike(status, name, page, limit);
        } else {
            return service.findGroupByStatus(status, page, limit);
        }
    }

    /**
     * 修改任务组状态
     * @param id 任务组id
     * @param status 任务组状态
     */
    @PutMapping(value = "status/{id}/{status}")
    @ResponseBody
    public void updateStatus(@PathVariable int id,
                             @PathVariable int status){
        service.updateGroupStatus(status, id);
    }

}
