package com.gobestsoft.mapper;

import com.gobestsoft.pojo.MemberTransfer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MemberTransferMapper extends Mapper<MemberTransfer> {


//    SELECT member_id FROM dept_member_transfer WHERE auid = ''

    @Select( " SELECT member_id FROM dept_member_transfer WHERE auid = '' " )
    List<Integer> findMemberIdByAuid();


//    UPDATE dept_member_transfer SET auid = '0001e116a84c431497179d841367a582' WHERE member_id = 105553

    @Update( " UPDATE dept_member_transfer SET auid = #{auid} WHERE member_id = #{memId} " )
    int updateAuidByMemId(@Param( "auid" )String auid, @Param("memId")Integer memId );



}
