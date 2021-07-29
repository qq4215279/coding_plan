package com.gobestsoft.service;

import com.gobestsoft.dao.TableConnectDao;
import com.gobestsoft.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class TableConnectService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private TableConnectDao tableConnectDao;

    public void insertBrand(String tableName) throws SQLException {

        List<Map<String,Object>> list = this.tableConnectDao.queryTableMeg(tableName);//1.查数据

        this.tableConnectDao.createTableBySql( tableName);  //2.创建表
        this.tableConnectDao.deleteTableMeg(tableName);     //3.先删除
        this.tableConnectDao.insertMessageInTable(tableName,list);  //4.在插入！
    }

    public void readTxt(List<Map<String,Object>> list){

        list.forEach(map -> {
            String sql = (String) map.get("sql");
            String tableName = (String) map.get("tableName");
            List<Map<String,Object>> listMessage = this.tableConnectDao.queryTableMeg(tableName);//1.查数据
            this.tableConnectDao.createTableByTxt(sql);     //2.创建表
            this.tableConnectDao.deleteTableMeg(tableName); //3.先删除
            this.tableConnectDao.insertMessageInTable(tableName,listMessage);   //4.在插入！
        });

    }


}

