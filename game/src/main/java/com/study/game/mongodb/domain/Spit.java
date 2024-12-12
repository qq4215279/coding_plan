/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.study.game.mongodb.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * Spit
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/12 23:07
 */
public class Spit implements Serializable {
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;
}
