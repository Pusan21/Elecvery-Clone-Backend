package com.Pusan21.ElecveryCloneBackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import com.Pusan21.ElecveryCloneBackend.helper.MyCarRepositoryTestHelper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyCarRepositoryRepositoryTest extends MyCarRepositoryTestHelper {

  @Test
  @DisplayName("내 차 등록 테스트")
  public void addMyCar() {
    // given
    Member member = memberRepository.getReferenceById(1L);
    MyCar myCar = generateMyCar("벤츠", "10허 0000", member);

    em.flush();
    em.clear();

    // when
    MyCar findMyCar = myCarRepository.findById(myCar.getMyCarNumber()).orElse(null);

    // then
    assertThat(myCar.getMyCarNumber()).isEqualTo(findMyCar.getMyCarNumber());
  }

  @Test
  @DisplayName("내 차 삭제 테스트")
  public void deleteMyPaymentInformation() {
    // given
    Member member = memberRepository.getReferenceById(2L);
    MyCar myCar = generateMyCar("벤츠", "10허 0000", member);

    em.flush();
    em.clear();

    // when
    myCarRepository.delete(myCar);
    MyCar findMyCar = myCarRepository.findById(myCar.getMyCarNumber()).orElse(null);

    // then
    assertThat(findMyCar).isEqualTo(null);
  }

  @Test
  @DisplayName("내 차 조회, 등록한 내 차 개수 테스트")
  public void getMyPaymentInformations() {
    // given
    Member member1 = memberRepository.getReferenceById(1L);
    MyCar myCar = generateMyCar("벤츠1", "10허 0001", member1);
    generateMyCar("벤츠2", "10허 0002", member1);
    Member member2 = memberRepository.getReferenceById(2L);
    generateMyCar("벤츠3", "10허 0003", member2);

    em.flush();
    em.clear();

    // when
    List<MyCar> myCars = myCarRepository.findAllByMember(member1);
    MyCar findMyCar = myCarRepository.findById(
        myCar.getMyCarNumber()).orElse(null);

    // then
    assertThat(myCar.getCarType()).isEqualTo(findMyCar.getCarType());
    assertThat(myCar.getPlateNumber()).isEqualTo(findMyCar.getPlateNumber());

    assertThat(myCars.size()).isEqualTo(2);
  }
}
