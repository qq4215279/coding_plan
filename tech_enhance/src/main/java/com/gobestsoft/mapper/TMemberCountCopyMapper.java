package com.gobestsoft.mapper;

import com.gobestsoft.pojo.TMemberCountCopy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface TMemberCountCopyMapper extends Mapper<TMemberCountCopy> {

    @Update( " UPDATE t_member_count_copy SET bind_num = #{bindNum} WHERE t_member_count_copy.`dept_id` = #{deptId} " )
    void insertBindNum(int bindNum, int deptId);

    @Select( " SELECT COUNT(tmcc.`bind_num`) FROM t_member_count_copy tmcc WHERE tmcc.dept_id = #{deptId} " )
    int isBindNum(@Param( "deptId" )int deptId);

    @Select( " SELECT d1.`dept_id` FROM dept_organization d1 " )
    List<Integer> queryAllDeptId();

}
