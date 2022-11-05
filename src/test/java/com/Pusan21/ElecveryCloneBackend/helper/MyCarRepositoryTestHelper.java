package com.Pusan21.ElecveryCloneBackend.helper;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.MyCarRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MyCarRepositoryTestHelper {

  @Autowired
  protected EntityManager em;
  @Autowired
  protected MemberRepository memberRepository;
  @Autowired
  protected MyCarRepository myCarRepository;

  protected MyCar generateMyCar(String carType, String plateNumber, Member member) {
    return myCarRepository.save(MyCar.builder()
        .carType(carType)
        .plateNumber(plateNumber)
        .member(member)
        .build());
  }
}
