package org.example.erd.domain.member.converter;

import org.example.erd.domain.member.dto.MemberResDTO;

public class MemberConverter {

    public static MemberResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ) {
        return MemberResDTO.RequestBody.builder()
                .stringTest(stringTest)
                .longTest(longTest)
                .build();
    }
}
