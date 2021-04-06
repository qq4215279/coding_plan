package com.gobestsoft.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UUserMapper {

     List<Map<String, Object>> findAuiddByMemberId() ;


}
