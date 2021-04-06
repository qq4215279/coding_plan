package com.gobestsoft.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String auid;
    private Integer memberId;

    private List<Integer> memIdList;

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public List<Integer> getMemIdList() {
        return memIdList;
    }

    public void setMemIdList(List<Integer> memIdList) {
        this.memIdList = memIdList;
    }
}
