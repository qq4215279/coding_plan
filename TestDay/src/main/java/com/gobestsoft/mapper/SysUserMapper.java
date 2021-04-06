package com.gobestsoft.mapper;

import com.gobestsoft.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper extends tk.mybatis.mapper.common.Mapper<SysUser> {

    @Select( " SELECT COUNT(1) FROM sys_user_big_data WHERE username = #{username} AND PASSWORD = #{password} " )
    int selectCountByUsernameAndPassword(@Param( "username" )String username,@Param( "password" )String password);

}
