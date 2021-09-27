package com.noriteo.delinori.common.security.domain;

import com.noriteo.delinori.common.dto.UploadResponseDTO;
import com.noriteo.delinori.common.security.dto.MemberDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String mid, mname, mpw, maddress, memail, mphone;
    private boolean enable, delifile;

    private List<MemberRole> roleList;

}
