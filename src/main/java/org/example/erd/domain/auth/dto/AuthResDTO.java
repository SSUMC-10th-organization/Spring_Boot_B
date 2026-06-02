package org.example.erd.domain.auth.dto;

public class AuthResDTO {
    public record SignUpRes(
            Long memberId
    ){}

    public record LoginRes(
            String accessToken
    ){}
}
