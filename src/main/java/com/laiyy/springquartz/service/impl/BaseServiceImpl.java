package com.laiyy.springquartz.service.impl;

import com.laiyy.springquartz.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/8 11:27
 * @description 通用 Service 实现
 */
public class BaseServiceImpl<T, ID extends Number, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    @Autowired
    protected R repository;

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T get(ID id) {
        return repository.getOne(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findByPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T add(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }
}
