package com.gobestsoft.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMemberMapper {

    List<Integer> queryPersonIdsByDeptId(int deptId);

    int judgeIsBindByPersonId(int personId);

    List<Integer> queryAllSons(@Param( "sonIds" ) String sonIds);
}
