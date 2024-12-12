/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.study.game.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.study.game.mongodb.domain.Spit;

/**
 * SpitDao
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/12 23:08
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
