package com.noriteo.delinori.common.security.mapper;

import com.noriteo.delinori.common.security.domain.Member;


public interface MemberMapper {

    Member findByMid(String mid);

}
