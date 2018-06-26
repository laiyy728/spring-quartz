package com.laiyy.springquartz.service.impl;

import com.laiyy.springquartz.base.impl.BaseServiceImpl;
import com.laiyy.springquartz.constants.GlobalConstant;
import com.laiyy.springquartz.enums.SortEnum;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.repository.GroupRepository;
import com.laiyy.springquartz.service.GroupService;
import com.laiyy.springquartz.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 16:27
 * @description
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<Group, Integer, GroupRepository> implements GroupService {

    @Override
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    public void updateGroupStatus(int status, int id) {
        repository.updateGroupStatus(status, id);
    }

    @Override
    public List<Group> findAllByStatusOrderByCreateDateDesc(int status) {
        return repository.findAllByStatusOrderByCreateDateDesc(status);
    }

    @Override
    public Page<Group> findGroupByStatus(int status, int page, int limit) {
        if (status == GlobalConstant.STATUS_OF_ALL) {
            return repository.findGroupByStatusNotEquals(GlobalConstant.DELETE,
                    PageUtil.of(page, limit, SortEnum.DESC, "id"));
        }
        return repository.findGroupByStatus(status,
                PageUtil.of(page, limit, SortEnum.DESC, "id"));
    }

    @Override
    public Page<Group> findGroupByStatusAndNameLike(int status, String name, int page, int limit) {
        if (status == GlobalConstant.STATUS_OF_ALL) {
            return repository.findGroupByStatusNotEqualsAndNameLike(GlobalConstant.DELETE,
                    "%" + name + "%",
                    PageUtil.of(page, limit, SortEnum.DESC, "id"));
        }
        return repository.findGroupByStatusAndNameLike(status,
                "%" + name + "%",
                PageUtil.of(page, limit, SortEnum.DESC, "id"));
    }

}
