/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.user;

import com.gobestsoft.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationTest {

    @Autowired
    DeptOrganizationMapper deptOrganizationMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;
    @Autowired
    TMemberCountMapper tMemberCountMapper;
    @Autowired
    DeptMemberMapper deptMemberMapper;
   /* @Autowired
    private TMemberCountCopyMapper tMemberCountCopyMapper;*/



    @Test
    public void test1(){
        String name = "海口市总工会";
        System.out.println(".....");
        if (StringUtils.isEmpty( name )){
//            DeptOrganization deptOrganization = this.deptOrganizationMapper.queryPidAndDeptIdByName( name );
//            int pid = deptOrganization.getpId();
//            int deptId = deptOrganization.getDeptId();
//            System.out.println("pid:" + pid + "deptId:" + deptId);

        }
        /*int deptAllCount = this.sysDeptMapper.queryDeptCountByPid( 1 );
        System.out.println("组织总数量："+ deptAllCount);*/

        /*int memberWholeCount = this.tMemberCountMapper.queryMemberWholeCountByDeptId( 3625 );
        System.out.println("会员总数："+ memberWholeCount);*/

        //List<Integer> personIdsList = this.deptMemberMapper.queryPersonIdsByDeptId( 3625 );
    }

    @Test
    public void test2() {
        int deptId = 3625;
        List<Integer> deptIdsList = deptOrganizationMapper.queryAllDeptIdsByPid( deptId );
        for (int i = 0; i < deptIdsList.size(); i++) {
            String unitName2 = this.deptOrganizationMapper.queryDeptUnitNameByDeptId( deptIdsList.get( i ) ).get( 0 );
            System.out.println( "unitName2:" + unitName2 );
            if (StringUtils.isNotEmpty( unitName2 )){
                Integer deptId2 = this.deptOrganizationMapper.queryPidAndDeptIdByName( unitName2 ).getDeptId();
                System.out.println( "deptId2:" + deptId2 );
                int deptAllCount2 = this.sysDeptMapper.queryDeptCountByPid( deptId2 );
                System.out.println( "查询出的组织数：" + deptAllCount2 );
            }
        }

    }

    /*
    @Test
    public void test3(){

        //统计专属会员个数
        List<Integer> personIdsList = this.deptMemberMapper.queryPersonIdsByDeptId( 3643 );
        int bindNum = 0;
        for (int j = 0; j < personIdsList.size() ; j++) {
            int personId = personIdsList.get( j );
            int isBind2 = deptMemberMapper.judgeIsBindByPersonId( personId );
            if (isBind2 == 1){
                bindNum++;
            }
        }
        this.tMemberCountCopyMapper.insertBindNum( bindNum,3643 );

    }*/


}
