package com.gobestsoft.mapper;

import com.gobestsoft.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Select( " SELECT auid FROM app_user WHERE member_id= #{memId} " )
    String findAuidByMemId(@Param( "memId" )Integer memId);

}
