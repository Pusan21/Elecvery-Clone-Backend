package com.Pusan21.ElecveryCloneBackend.repository;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);
}
