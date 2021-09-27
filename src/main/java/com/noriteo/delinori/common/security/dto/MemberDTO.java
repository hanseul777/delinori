package com.noriteo.delinori.common.security.dto;

import com.noriteo.delinori.common.dto.UploadResponseDTO;
import com.noriteo.delinori.common.security.domain.Member;
import com.noriteo.delinori.qna.dto.QnaDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO extends User {

    private String mid;
    private String mname;
    private String mpw;
    private String maddress;
    private String memail;
    private String mphone;
    private boolean enable;
    private boolean delifile;

    public MemberDTO(Member member){
        super(member.getMid(),
                member.getMpw(),
                member.getRoleList().stream().map(memberRole ->
                        new SimpleGrantedAuthority(memberRole.getRole())).collect(Collectors.toList()));
        this.mid = member.getMid();
        this.mname = member.getMname();
        this.mpw = member.getMpw();
        this.maddress = member.getMaddress();
        this.memail = member.getMemail();
        this.mphone = member.getMphone();
        this.enable = member.isEnable();
        this.delifile = member.isDelifile();
    }

    public MemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }

}
