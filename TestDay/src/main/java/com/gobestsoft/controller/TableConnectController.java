package com.gobestsoft.controller;

import com.gobestsoft.dao.TableConnectDao;
import com.gobestsoft.mapper.BrandMapper;
import com.gobestsoft.service.TableConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

@RestController
public class TableConnectController {

    @Autowired
    private TableConnectService tableConnectService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private TableConnectDao tableConnectDao;

   /* @GetMapping("read/table")
    public void  timeMask(){

       Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           //重写方法
           public void run() {

               try {
                   readTxt();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       },3000,1000*60*60);//延迟3s执行，5s执行一次


    }*/

    @GetMapping("insert/brand")
    public String insertBrand() {  //方法1：读表名创建表

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("H:\\MajorData\\IDEAWorkspace\\temData\\tableNames.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];//存取每次读取的数据
        int len = 0;
        List<String> list = null;
        while (true) {
            try {
                if (!((len = fis.read(bytes)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            list = new ArrayList<>();
            String tableName = new String(bytes, 0, len);
            System.out.printf(tableName);
            list.add(tableName);
        }
        list.forEach(tableName -> {
            try {
                this.tableConnectService.insertBrand(tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return "OK!";
    }

    @GetMapping("read/table")
    @ResponseBody
    public List<Map<String, Object>> readTxt() throws IOException {  //方法2：读表结构创建表

        BufferedReader fr = new BufferedReader(new FileReader("H:\\MajorData\\IDEAWorkspace\\temData\\allTableNames.txt"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = fr.readLine()) != null) {
            sb.append(line);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        String sqls = sb.toString();
        String[] tableSQL = sqls.split("#####");
        for (String sql : tableSQL) {
            Map<String, Object> map = new HashMap<>();
            String one = sql.substring(0, sql.indexOf('('));
            String tableName = one.substring(27);
            System.out.println("tableName=>" + tableName);
            map.put("sql", sql);
            map.put("tableName", tableName);
            list.add(map);
        }
        this.tableConnectService.readTxt(list);
        return list;
    }

    @GetMapping("ttt")
    public void ttt() {  //打印查出的表数据
        List<Map<String, Object>> list = this.tableConnectDao.queryTableMeg("tb_atest");
        list.forEach(map -> {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key:" + entry.getKey() + "  value:" + entry.getValue());
            }
            System.out.println("=================================================");
        });
    }


    @GetMapping("read")
    public List<String> readTableNamesByFile() throws IOException {

        FileInputStream fis = new FileInputStream("H:\\MajorData\\IDEAWorkspace\\temData\\tableNames.txt");
        int len = 0;
        byte[] bytes = new byte[1024];
        List<String> list = null;
        while ((len = fis.read(bytes)) != -1) {
            list = new ArrayList<>();
            String tableName = new String(bytes, 0, len);
            System.out.printf(tableName);
            list.add(tableName);
        }
        return list;

    }

}
