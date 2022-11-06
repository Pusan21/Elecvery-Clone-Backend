package com.Pusan21.ElecveryCloneBackend.repository;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCarRepository extends JpaRepository<MyCar, Long> {

  List<MyCar> findAllByMember(Member member);

  MyCar findByMemberAndAndMyCarNumber(Member member, Long myCarNumber);
}
