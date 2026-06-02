package org.example.erd.global.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.enums.SocialType;

@Getter
@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private final String socialUid;
    private final String email;
    private final String name;

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
