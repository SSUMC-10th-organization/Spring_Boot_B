package org.example.erd.domain.member.service;

import org.example.erd.domain.member.converter.MemberConverter;
import org.example.erd.domain.member.dto.MemberReqDTO;
import org.example.erd.domain.member.dto.MemberResDTO;

public class MemberService {

    public String singleParameter(
            String singleParameter
    ){
        return singleParameter;
    }


    public MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBody dto
    ) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }
}
