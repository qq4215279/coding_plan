package com.gobestsoft.service;

import com.gobestsoft.mapper.*;
import com.gobestsoft.pojo.DeptOrganization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrganizationService {

    @Autowired
    DeptOrganizationMapper deptOrganizationMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;
    @Autowired
    TMemberCountMapper tMemberCountMapper;
    @Autowired
    DeptMemberMapper deptMemberMapper;


    public List<Map<String, Object>> queryOrgMessage(String unitName) {

        List<Map<String, Object>> messageMapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isNotEmpty( unitName )) {    // "海口市总工会"
            DeptOrganization deptOrganization = this.deptOrganizationMapper.queryPidAndDeptIdByName( unitName );
            int deptId = deptOrganization.getDeptId();
            int pid = deptOrganization.getpId();
            //总组织数 + 总会员数 + 统计专属会员个数
            int deptCount = this.findDeptCount( deptId );
            int memberWholeCount = this.tMemberCountMapper.queryMemberWholeCountByDeptId( deptId );
            int count = this.findMemberCount( deptId );
            System.out.println( "正在查询的组织：" + unitName + " 总组织数为：" + deptCount + " 会员总数：" + memberWholeCount + " 专属会员数：" + count );
            System.out.println( "deptId:" + deptId + " pid为：" + pid );
            System.out.println( deptId + ":======================================" );
            map.put( "组织名称：", unitName );
            map.put( "组织总数量：", deptCount );
            map.put( "会员总数：", memberWholeCount );
            map.put( "专属会员数量：", count );
            messageMapList.add( map );
            //主要用于查询子的deptAllCount2去判断是否发生递归
            int flag = this.sysDeptMapper.queryDeptCountByPid( deptId );//查组织数
            List<Integer> deptIdsList = deptOrganizationMapper.queryAllDeptIdsByPid( deptId );//查子组织的dept_id
            if (flag > 0) {  //组织数有1及以上
                for (int i = 0; i < deptIdsList.size(); i++) {
                    Integer deptId1 = deptIdsList.get( i );
                    System.out.println( "select deptId === " + deptId1 );
                    String unitName2 = this.deptOrganizationMapper.queryDeptUnitNameByDeptId( deptId1 ).get( 0 );
                    System.out.println( "unitName2:" + unitName2 );
                    queryOrgMessage( unitName2 );
                }
            }
        }
        return messageMapList;
    }

    //test:=================================================
    public Map<String, Object> queryOrgMsg(String unitName) {

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty( unitName )) {
            DeptOrganization deptOrganization = this.deptOrganizationMapper.queryPidAndDeptIdByName( unitName );
            int deptId = deptOrganization.getDeptId();
            int pid = deptOrganization.getpId();
            //总组织数 + 总会员数统计 + 专属会员个数
            int deptCount = this.findDeptCount( deptId );
            int memberWholeCount = this.tMemberCountMapper.queryMemberWholeCountByDeptId( deptId );
            int count = this.findMemberCount( deptId );

            System.out.println( "正在查询的组织：" + unitName + " 总组织数为：" + deptCount + " 会员总数：" + memberWholeCount + " 专属会员数：" + count );
            System.out.println( "deptId:" + deptId + " pid为：" + pid );
            map.put( "组织名称：", unitName );
            map.put( "组织总数量：", deptCount );
            map.put( "会员总数：", memberWholeCount );
            map.put( "专属会员总数量：", count );
            return map;
        }
        return null;
    }


    public int findDeptCount(int deptId) {   //1.查总组织数
        int deptCount = this.sysDeptMapper.queryDeptCountByPid( deptId );//查组织数
        int sum = 0;
        if (deptCount > 0) {  //组织数有1或以上
            List<Integer> deptIdsList = deptOrganizationMapper.queryAllDeptIdsByPid( deptId );  //查出子组织的dept_id
            for (int i = 0; i < deptIdsList.size(); i++) {
                int deptId2 = deptIdsList.get( i );
                int deptCount2 = findDeptCount( deptId2 );//递归查组织数
                sum += deptCount2;
            }
        }
        return deptCount + sum;//返回总组织数
    }

    public int findMemberCount(int deptId) {//查总专属会员数
        int deptCount = this.sysDeptMapper.queryDeptCountByPid( deptId );   //2.查组织数
        int sum = 0;
        List<Integer> personIdsList = this.deptMemberMapper.queryPersonIdsByDeptId( deptId );
        int count = 0;  //统计专属会员个数
        for (int j = 0; j < personIdsList.size(); j++) {
            int personId = personIdsList.get( j );
            int isBind2 = deptMemberMapper.judgeIsBindByPersonId( personId );
            if (isBind2 == 1) {
                count++;
            }
        }
        if (deptCount > 0) {  //组织数大于1
            List<Integer> deptIdsList = deptOrganizationMapper.queryAllDeptIdsByPid( deptId );  //查出子组织的dept_id
            for (int i = 0; i < deptIdsList.size(); i++) {
                int deptId2 = deptIdsList.get( i );
                int memberCount = this.findMemberCount( deptId2 );
                sum += memberCount;
            }
        }
        return count + sum;//返回总组织数
    }

    /*public List<Integer> findAllDeptId(){
        int deptCount = this.sysDeptMapper.queryDeptCountByPid( 3625 );//查海口市组织数
        int sum = 0;


    }*/

    public void insertBindNum() {  //插入专属会员数量

        List<Integer> deptIdList = this.tMemberCountCopyMapper.queryAllDeptId();
        for (int i = 0; i < deptIdList.size(); i++) {
            int deptId = deptIdList.get( i );
            int flag = this.tMemberCountCopyMapper.isBindNum( deptId );
            if (flag != 0) {
                continue;
            }
            //统计专属会员个数
            List<Integer> personIdsList = this.deptMemberMapper.queryPersonIdsByDeptId( deptId );
            int bindNum = 0;
            for (int j = 0; j < personIdsList.size(); j++) {
                int personId = personIdsList.get( j );
                int isBind = deptMemberMapper.judgeIsBindByPersonId( personId );
                if (isBind == 1) {
                    bindNum++;
                }
            }
            this.tMemberCountCopyMapper.insertBindNum( bindNum, deptId );
        }
    }


    @Autowired
    private TMemberCountCopyMapper tMemberCountCopyMapper;

    public List<Integer> getSonDeptIds(int pid) {
        return deptOrganizationMapper.queryAllSonDeptIdsByPid( pid );
    }

    public List<Integer> getSonMembers(String sonIds) {
        return deptMemberMapper.queryAllSons( sonIds );
    }



}
