package com.noriteo.delinori.security.mapper;

import com.noriteo.delinori.common.config.RootConfig;
import com.noriteo.delinori.common.security.config.SecurityConfig;
import com.noriteo.delinori.common.security.domain.Member;
import com.noriteo.delinori.common.security.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {RootConfig.class, SecurityConfig.class})
public class PasswordTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    MemberMapper memberMapper;

    @Test
    public void testMember(){
        try{
            log.info("===============testMember=============");
            log.info(memberMapper);

            String mid = "admin0";

            Member member = memberMapper.findByMid(mid);

            log.info(member);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void insertAdmin(){
        String query = "insert into member (mid,mpw,mname,maddress,memail,mphone) values ('v1','v2','v3','v4','v5','v6');";

        for(int i = 0; i < 10; i++){
            String mid = "admin"+i; //user0
            String mpw = passwordEncoder.encode("pw"+i);//pw0 -> 암호화(Bcrypt 된 암호화를 사용)
            String mname = "관리자"+i; //관리자0
            String maddress = "주소"+i;
            String memail = "이메일"+i;
            String mphone = "전화번호"+i;

            String result = query;

            result = result.replace("v1",mid);
            result = result.replace("v2",mpw);
            result = result.replace("v3",mname);
            result = result.replace("v4",maddress);
            result = result.replace("v5",memail);
            result = result.replace("v6",mphone);

            System.out.println(result);

        }
    }

    @Test
    public void insertDeliRole(){
        String sql = "insert into member_role (mid,role) values ('%s','%s');";

        for(int i = 0; i<10; i++){
            String result = String.format(sql, "deli"+i, "ROLE_DELI");

            System.out.println(result);
        }
    }

    @Test
    public void insertNoriRole(){
        String sql = "insert into member_role (mid,role) values ('%s','%s');";

        for(int i = 0; i<10; i++){
            String result = String.format(sql, "nori"+i, "ROLE_NORI");

            System.out.println(result);
        }
    }

    @Test
    public void insertAdminRole(){
        String sql = "insert into member_role (mid,role) values ('%s','%s');";

        for(int i = 0; i<10; i++){
            String result = String.format(sql, "admin"+i, "ROLE_NORI");
            System.out.println(result);

            String result2 = String.format(sql, "admin"+i, "ROLE_DELI");
            System.out.println(result2);

            String result3 = String.format(sql, "admin"+i, "ROLE_ADMIN");
            System.out.println(result3);
        }
    }
}
