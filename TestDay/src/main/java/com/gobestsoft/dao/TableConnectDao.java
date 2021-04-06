package com.gobestsoft.dao;

import com.gobestsoft.utils.JDBCUtils;
import com.gobestsoft.utils.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class TableConnectDao {

    //操作本地数据库：
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Autowired  //操作 39.108.88.223:12306 数据库
    private JdbcTemplate jdbcTemplate;


    public List<Map<String,Object>> queryTableMeg(String tableName){

        List<Map<String,Object>> list = null;
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM " + tableName);
        try {
            list = jdbcTemplate.queryForList(sb.toString());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createTableBySql(String tableName) throws SQLException {

        DatabaseMetaData metaData = JDBCUtils.getConnection().getMetaData();
        ResultSet colRet = metaData.getColumns(null, null, tableName, null);
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE IF NOT EXISTS " + tableName + " (  ");
        while (colRet.next()){
            String field = colRet.getString("COLUMN_NAME").toLowerCase();
            String type = colRet.getString("TYPE_NAME").toLowerCase();
            int size = colRet.getInt("COLUMN_SIZE");
            System.out.println("field:"+field+" type:"+type+" size:"+size +"====");
            String column = null;
            if (type.equals("text") || type.equals("longtext") || type.equals("double") || type.equals("datetime")){
                column = field + " " + type ;
                sb.append(column).append(",");
                System.out.println(column);
            }else if (type.equals("decimal")){
                column = field + " " + type + "(" + size + ",3)";
                sb.append(column).append(",");
                System.out.println(column);
            }else if (type.equals("bit")){
                type = " tinyint(1)";
                column = field + type;
                sb.append(column).append(",");
                System.out.println(column);
            }else {
                if (type.contains("unsigned")){
                    type = type.replace("unsigned","");
                }
                column = field + " " + type + "(" + size + ")";
                sb.append(column).append(",");
                System.out.println(column);
            }
        }
        String sql = sb.toString();
        sql = sql.substring(0, sql.length() - 1) + ")";
        System.out.println(sql);
        template.update(sql);
    }

    public void createTableByTxt(String sql){
        template.update(sql);
    }

    public void deleteTableMeg(String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("TRUNCATE TABLE  " + tableName);
        template.update(sb.toString());
    }

    public void insertMessageInTable(String tableName,List<Map<String,Object>> list) {    //往表中插入数据
        list.forEach(map ->{
            StringBuffer sb = new StringBuffer();
            sb.append(" INSERT INTO " + tableName + " VALUES( ");
            for(Map.Entry<String,Object> entry : map.entrySet()){
                Object value = entry.getValue();
                if (ToolUtil.isEmpty(value)){
                    value = "NULL";
                    sb.append(value +",");
                }else {
                    if (StringUtils.isNumeric(value.toString())){   //判断是否为数字
                        sb.append(value + ",");
                    }else {
                        value = value.toString();
                        if (value.equals("true")){
                            value = 1;
                            sb.append(value + ",");
                        }else if (value.equals("false")){
                            value = 0;
                            sb.append(value + ",");
                        }else {
                            if(ToolUtil.contains(value,"'")){
                                value = value.toString().replace("'","\\'");
                                System.out.println(value);
                                sb.append("'" + value + "',");
                            }else {
                                sb.append("'" + value + "',");
                            }
                        }
                    }
                }
            }
            String sql = sb.toString();
            sql = sql.substring(0,sql.length()-1);
            sql = sql + ")";
            System.out.println(sql);
            template.update(sql);


        });
        System.out.println("list的大小：" + list.size());
    }


    public List<String> queryTableNames(){     //查询数据库所有表名

        String sql = " select table_name from information_schema.tables where table_schema='mybatis' ";
        List<String> list = template.queryForList(sql,String.class);
        return list;
    }


}
