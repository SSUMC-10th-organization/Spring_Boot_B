package org.example.erd.global.security.service;

import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.member.exception.MemberException;
import org.example.erd.domain.member.exception.code.MemberErrorCode;
import org.example.erd.domain.member.repository.MemberRepository;
import org.example.erd.global.security.entity.AuthMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 멤버입니다."));
        return new AuthMember(member);
    }
}
