package org.example.erd.global.security.dto;

import org.example.erd.domain.member.enums.SocialType;

public interface OAuthDTO {
    String getSocialUid();
    String getEmail();
    String getName();
    SocialType getSocialType();
}
