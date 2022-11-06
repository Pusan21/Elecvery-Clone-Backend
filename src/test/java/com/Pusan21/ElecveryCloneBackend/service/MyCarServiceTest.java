package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.AddMyCarDto;
import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import com.Pusan21.ElecveryCloneBackend.helper.MyCarServiceTestHelper;
import com.Pusan21.ElecveryCloneBackend.repository.MyCarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MyCarServiceTest extends MyCarServiceTestHelper {
  /*private Member member1;
  private Member member2;

  @Autowired
  protected MyCarService myCarService;

  @BeforeEach()
  public void setUp() {
    member1 = memberRepository.findById(1L).orElse(null);
    member2 = memberRepository.findById(2L).orElse(null);
  }

  @Test
  @DisplayName("내 차 등록 테스트")
  public void addMyCar(){
    // given
    AddMyCarDto requestDto = AddMyCarDto.builder().carType("벤츠").plateNumber("10허 0000").build();

    // when
    Long myCarNumber = myCarService.addMyCar(member1.getMemberId(), requestDto);
    MyCar findMyCar = myCarRepository.findByMemberAndAndMyCarNumber(member1,
        myCarNumber);

    em.flush();
    em.clear();

    // then
    Assertions.assertThat(findMyCar.getMyCarNumber()).isEqualTo(myCarNumber);
  }*/
}
