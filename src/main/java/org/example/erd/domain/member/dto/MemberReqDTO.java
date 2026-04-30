package org.example.erd.domain.member.dto;

public class MemberReqDTO {

    public record SignUp(
            Boolean isAgreeService,
            Boolean isAgreePrivacy,
            Boolean isAgreeAddress,
            Boolean isAgreeMarketing,
            String name,
            String gender,
            String birth,
            String address,
            String specAddress,
            Long foodCategoryId
    ) {}

    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}
}
