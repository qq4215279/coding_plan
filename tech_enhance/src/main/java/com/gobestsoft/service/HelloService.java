package com.gobestsoft.service;

import com.gobestsoft.mapper.MMemberTransferMapper;
import com.gobestsoft.mapper.MemberTransferMapper;
import com.gobestsoft.mapper.UUserMapper;
import com.gobestsoft.mapper.UserMapper;
import com.gobestsoft.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HelloService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberTransferMapper transferMapper;

    @Autowired
    private UUserMapper uUserMapper;

    @Autowired
    private MMemberTransferMapper mMemberTransferMapper;


    public List<User> testQuery(int mem_id){

        User u = new User();
        u.setMemberId( mem_id );
        List<User> list = this.userMapper.select(u);
        System.out.println(list);

        return list;

    }

/*
    *   SELECT member_id FROM dept_member_transfer WHERE auid = ''
        SELECT auid FROM app_user WHERE member_id=105553
        UPDATE dept_member_transfer SET auid = '0001e116a84c431497179d841367a582' WHERE member_id = 105553
    问题要求：查询出dept_member_transfer表中auid为空时的member_id字段，
             根据查出来的member_id字段去查询app_user表中的auid字段，
             最后通过auid字段去更新dept_member_transfer表

        select auid from User where member_id = ( select member_id form MemberTransfer where auid = '' );
* */

    public void updateQuestion(){

        List<Integer> memberIdList = this.transferMapper.findMemberIdByAuid();  //查出的memberId封装成集合
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < memberIdList.size() ; i++) {
            //System.out.println(memberIdList.get( i ));
            String auid = this.userMapper.findAuidByMemId( memberIdList.get( i ) );
            if(!StringUtils.isEmpty( auid )){
                map.put( memberIdList.get( i ), auid ); //将查出的auid封装成map集合。key为memberId，values为auid
                System.out.println(memberIdList.get( i ));  // auidList集合为空？即打印查出来的memberId
                System.out.println(map.get( memberIdList.get( i ) ));   //试着打印查出来的auid
                System.out.println("==============================");
                //更新Auid操作：
                int n = this.transferMapper.updateAuidByMemId( auid, memberIdList.get( i ) );
                System.out.println(n);
            }else {
                System.out.println("auid为空，不能更新...");
            }
        }
        /*//更新Auid操作：
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer memId = iterator.next();
            System.out.println();
            this.transferMapper.updateAuidByMemId( map.get( memId ), memId );
        }*/

    }

    public void updateQuestion2(){

        List<Map<String, Object>> list = this.uUserMapper.findAuiddByMemberId();
        list.forEach( map -> {
            Integer memberId = (Integer) map.get( "member_id" );
            Object auid = map.get( "auid" );

            this.mMemberTransferMapper.updateAuid( map );
        });
        System.out.println(list);


    }


}
