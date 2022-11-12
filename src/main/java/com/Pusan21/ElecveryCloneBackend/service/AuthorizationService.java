package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.Role;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthorizationService implements UserDetailsService {


    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId);

        if(member == null){
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User not found in the DB");
        } else {
            log.info("User found in the database: {}", loginId);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        member.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(member.getLoginId(), member.getLoginPassword(), authorities);
    }

    public Member saveMember(Member member) {
        log.info("Saving new user {} into DB", member.getLoginId());
        member.setLoginPassword(passwordEncoder.encode(member.getLoginPassword()));
        return memberRepository.save(member);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} into DB", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToMember(String loginId, String roleName) {
        log.info("Adding role {} to user {}", roleName, loginId);
        Member member = memberRepository.findByLoginId(loginId);
        Role role = roleRepository.findByName(roleName);
        member.getRoles().add(role);
    }

    public List<Member> getMembers() {
        log.info("Fetching users");
        return memberRepository.findAll();
    }

}
