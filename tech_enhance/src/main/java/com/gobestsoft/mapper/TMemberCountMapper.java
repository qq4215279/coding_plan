package com.gobestsoft.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TMemberCountMapper {

    int queryMemberWholeCountByDeptId(int deptId);

}
