package com.laiyy.springquartz.base.impl;

import com.laiyy.springquartz.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author laiyy
 * @date 2018/6/8 11:27
 * @description 通用 Service 实现，接收一个实体类型、实体主键类型、实体的 jpaRepository，并自动注入 repository
 */
public class BaseServiceImpl<T, ID extends Number, R extends JpaRepository<T, ID>> extends ServiceImpl implements BaseService<T, ID> {

    @Autowired
    protected R repository;

    @Override
    public void delete(T t) {
        logger.debug(">>>>>>>>>>>>>>>> 正在删除实体：{} <<<<<<<<<<<<<<<<<", t);
        repository.delete(t);
    }

    @Override
    public void delete(ID id) {
        logger.debug(">>>>>>>>>>>>>>>>>> 正在删除实体 id：{} <<<<<<<<<<<<<<<<<<", id);
        repository.deleteById(id);
    }

    @Override
    public T get(ID id) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>> 正在获取实体 id：{} <<<<<<<<<<<<<<<<<<<<<", id);
        Optional<T> optional = repository.findById(id);
        T t = null;
        if (optional.isPresent()) {
            t = optional.get();
        }
        return t;
    }

    @Override
    public List<T> findAll() {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>> 正在获取所有 <<<<<<<<<<<<<<<<<<<<");
        return repository.findAll();
    }

    @Override
    public Page<T> findByPageable(Pageable pageable) {
        logger.debug(">>>>>>>>>>>>>>>>>>> 正在获取分页数据，当前页：{}， 每页显示条数：{} <<<<<<<<<<<<<<<<<<<<", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable);
    }

    @Override
    public T add(T t) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>> 正在添加数据：{} <<<<<<<<<<<<<<<<<<<<<<<", t);
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>> 正在修改数据：{} <<<<<<<<<<<<<<<<<<<<<<<", t);
        return repository.save(t);
    }
}
