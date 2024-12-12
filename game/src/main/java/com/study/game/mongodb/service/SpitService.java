/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.study.game.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.game.mongodb.dao.SpitDao;
import com.study.game.mongodb.domain.Spit;

/**
 * SpitService
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/12 23:08
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    /**
     * 查询全部记录
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    public Spit findById(String id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * 增加
     * @param spit
     */
    public void add(Spit spit) {
        // spit.set_id(idWorker.nextId()+""); //主键值
        spitDao.save(spit);
    }

    /**
     * 修改
     * @param spit
     */public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }
}
