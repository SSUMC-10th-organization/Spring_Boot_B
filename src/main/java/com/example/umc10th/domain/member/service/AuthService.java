package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public MemberResponseDTO.LoginResultDto login(MemberRequestDTO.LoginDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            AuthMember authMember = (AuthMember) authentication.getPrincipal();
            String accessToken = jwtUtil.createAccessToken(authMember);

            return MemberResponseDTO.LoginResultDto.builder()
                    .accessToken(accessToken)
                    .tokenType("Bearer")
                    .build();

        } catch (AuthenticationException e) {
            // 이메일 없음 or 비밀번호 불일치 → 401
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
    }
}
