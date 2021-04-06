package com.gobestsoft.controller;

import com.gobestsoft.mapper.DeptMemberMapper;
import com.gobestsoft.mapper.DeptOrganizationMapper;
import com.gobestsoft.mapper.TMemberCountMapper;
import com.gobestsoft.pojo.DeptOrganization;
import com.gobestsoft.service.OrganizationService;
import com.gobestsoft.utils.ExcelExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    DeptOrganizationMapper deptOrganizationMapper;
    @Autowired
    TMemberCountMapper tMemberCountMapper;
    @Autowired
    DeptMemberMapper deptMemberMapper;
    @Autowired
    OrganizationService organizationService;

    @GetMapping("dept")
    @ResponseBody
    public List<Map<String, Object>> queryOrgMessage(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, Object>> list = this.organizationService.queryOrgMessage( "海口市美兰区总工会" );//海口市总工会 海口市美兰区总工会 海口市秀英区总工会
        return list;
    }

    @GetMapping("test2")    //查一个组织
    @ResponseBody
    public Map<String, Object> queryOrtMsg2(){

        Map<String,Object> map = this.organizationService.queryOrgMsg( "海口市美兰区总工会" );
        return map;
    }

    @GetMapping("test")     //查多个组织
    @ResponseBody
    public List<Map<String, Object>> queryOrtMsg(){

//        List<String> queryNameList = new LinkedList<>();
//        queryNameList.add( "海南省总工会" );
//        queryNameList.add( "海口市总工会" );
//        queryNameList.add( "海口市美兰区总工会" );
//        queryNameList.add( "海口市秀英区总工会" );
        String[] queryNameList = {"海口市总工会","海口市美兰区总工会","海口市秀英区总工会"};

        List<Map<String,Object>> dataList = new LinkedList<>();
        for (int i = 0; i < queryNameList.length ; i++) {
            String unitName = queryNameList[i];
            Map<String, Object> nums = this.organizationService.queryOrgMsg( unitName );
            dataList.add( nums );
            System.out.println("当前组织:"+unitName);
            System.out.println("对应数量:"+nums);
        }
        //组织名称：其他   其他总组织数：deptCountOther   其他总会员数：memberWholeCountOther    其他认证会员数：countOther
        int deptCountAll = (int) dataList.get(0).get( "组织总数量" );
        int memberWholeCountAll = (int) dataList.get(0).get( "会员总数" );
        int countAll = (int) dataList.get(0).get( "专属会员总数量" );
        int deptCountOther = 0;
        int memberWholeCountOther = 0;
        int countOther = 0;
        for (int i = 1; i < dataList.size() ; i++) {
            deptCountOther = deptCountAll - (int)dataList.get( i ).get( "组织总数量" );
            memberWholeCountOther = memberWholeCountAll - (int) dataList.get(i).get( "专属会员总数量" );
            countOther = countAll - (int) dataList.get(i).get( "专属会员总数量" );
        }
        Map<String, Object> map = new HashMap<>();
        map.put(  "组织名称：", "其他" );
        map.put( "组织总数量：", deptCountOther );
        map.put( "会员总数：", memberWholeCountOther );
        map.put( "专属会员总数量：", countOther );
        dataList.add( map );

        //控制台打印结果：
        Map<String,Object> m = null;
        for (int i = 0; i < dataList.size() ; i++) {
            m = dataList.get(i);
            Set<String> set = m.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                String ket = (String) iterator.next();
                System.out.println(ket + ":" + m.get(ket) );
            }
        }

        return dataList;
    }

    @GetMapping("insert")
    public String insertBindNum() {  //插入专属会员数量

        this.organizationService.insertBindNum();
        System.out.println("插入成功！！！");
        return "插入完成！！！";

    }

    @GetMapping("test3")
    @ResponseBody
    public List<Map<String,Object>> test3(){
        String[] unitNameArrays = {"海口市总工会","海口市美兰区总工会","海口市秀英区总工会","海口市秀英区秀英街道办事处","海口市秀英区社区网格管理中心"};
        List<Map<String,Object>> list = new LinkedList<>();
        for (int i = 0; i < unitNameArrays.length ; i++) {
            Map<String,Object> map = new HashMap<>();
            String unitName = unitNameArrays[i];
            DeptOrganization deptOrganization = this.deptOrganizationMapper.queryPidAndDeptIdByName( unitName );
            int deptId = deptOrganization.getDeptId();
            List<Integer> sonDeptIds = organizationService.getSonDeptIds( deptId );
            sonDeptIds.add( deptId );
            int size = sonDeptIds.size();
            map.put( "组织名称", unitName );
            map.put( "组织数", size );
            String sonIds = sonDeptIds.toString().replace( "[","" ).replace( "]","" );
            List<Integer> sonMembers = organizationService.getSonMembers( sonIds );
            int members = sonMembers.size();
            List<Integer> binds = sonMembers.stream().filter( son -> son == 1 ).collect( Collectors.toList() );
            int bindNum = binds.size();
            map.put( "会员数", members );
            map.put( "绑定会员数", bindNum );
            list.add( map );
            System.out.println(map);
            System.out.println("===================================");
        }
        //组织名称：其他   其他总组织数：sizeOthers   其他总会员数：memberOthers    其他认证会员数：bindOthers
        int sizeMax = (int) list.get(0).get( "组织数" );
        int memberMax = (int) list.get(0).get( "会员数" );
        int bindMax = (int) list.get(0).get( "绑定会员数" );
        int sizeOthers1 = 0;
        int memberOthers1 = 0;
        int bindOthers1 = 0;
        Map<String,Object> mapOthers = new HashMap<>();
        for (int i = 1; i < list.size() ; i++) {
            int sizeOthers2 = (int)list.get( i ).get( "组织数" );
            int memberOthers2 = (int) list.get(i).get( "会员数" );
            int bindOthers2 = (int) list.get(i).get( "绑定会员数" );
            sizeOthers1 += sizeOthers2;
            memberOthers1 += memberOthers2;
            bindOthers1 += bindOthers2;
        }

        mapOthers.put(  "组织名称", "其他" );
        mapOthers.put( "组织数", sizeMax-sizeOthers1 );
        mapOthers.put( "会员数", memberMax-memberOthers1 );
        mapOthers.put( "绑定会员数", bindMax-bindOthers1 );
        System.out.println(mapOthers);
        list.add( mapOthers );

        return list;
    }

    @GetMapping("demo")
    public void demo(HttpServletRequest request,HttpServletResponse response) throws Exception {

        String[] excelHeader = {"组织名称:组织名称", "组织数:组织数", "会员数:会员数", "绑定会员数:专属会员数"};
        ExcelExportUtil excelExportUtil = new ExcelExportUtil(excelHeader,"海口市总工会组织数据");
        excelExportUtil.export( request,response, test3(),":" );

    }


}
