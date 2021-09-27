package com.noriteo.delinori.common.security.service;

import com.noriteo.delinori.common.security.domain.Member;
import com.noriteo.delinori.common.security.dto.MemberDTO;
import com.noriteo.delinori.common.security.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("==============CustomUserDetailsService===========");
        log.info(username);
        log.info(memberMapper);

        Member member = memberMapper.findByMid(username);

        log.info(member);

        if(member == null){
            throw new UsernameNotFoundException("NOT EXIST");
        }

//        String[] authorities = member.getRoleList().stream().map(memberRole -> memberRole.getRole()).toArray(String[]::new);
        User result = new MemberDTO(member);
//        User result = (User) User.builder()
//                .username(username)
//                .password(member.getMpw())
//                .accountExpired(false)
//                .accountLocked(false)
//                .authorities(authorities)
//                .build();

        return result;
    }
}
