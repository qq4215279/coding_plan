package com.gobestsoft.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptMapper {

    int queryDeptCountByPid(int pId);

}
