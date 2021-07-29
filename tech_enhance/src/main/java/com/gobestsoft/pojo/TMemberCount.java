package com.gobestsoft.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_member_count")
public class TMemberCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer membeWholeCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMembeWholeCount() {
        return membeWholeCount;
    }

    public void setMembeWholeCount(Integer membeWholeCount) {
        this.membeWholeCount = membeWholeCount;
    }
}
