package com.gobestsoft.mapper;

import com.gobestsoft.pojo.DeptOrganization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptOrganizationMapper {

    DeptOrganization queryPidAndDeptIdByName(@Param("unitName")String unitName);

    List<Integer> queryAllDeptIdsByPid(int pid);

    List<Integer> queryAllSonDeptIdsByPid(int pid);

    List<String> queryDeptUnitNameByDeptId(int deptId);

}
