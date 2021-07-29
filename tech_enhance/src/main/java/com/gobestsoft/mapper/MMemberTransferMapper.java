package com.gobestsoft.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MMemberTransferMapper {

    void updateAuid(Map<String,Object> map);


}
