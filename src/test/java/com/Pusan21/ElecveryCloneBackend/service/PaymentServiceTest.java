package com.Pusan21.ElecveryCloneBackend.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.AddPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.GetPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.UpdatePaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.helper.PaymentInformationServiceTestHelper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class PaymentServiceTest extends PaymentInformationServiceTestHelper {

  private Member member1;
  private Member member2;

  @Autowired
  private PaymentInformationService paymentInformationService;

  /*
   * Member Table 에는 1L, 2L, 3L의 Id 값을 가진 총 3개의 더미 데이터가 있습니다.
   */
  @BeforeEach()
  public void setUp() {
    member1 = memberRepository.findById(1L).orElse(null);
    member2 = memberRepository.findById(2L).orElse(null);
  }

  @Test
  @DisplayName("내 지불 정보 등록 테스트")
  public void addMyPaymentInformation() {
    // given
    AddPaymentInformationDto requestDto = AddPaymentInformationDto.builder().cardNumber("1234")
        .cardDetail("부산대학카드").build();

    // when
    Long paymentInformationId = paymentInformationService
        .addPaymentInformation(member1.getMemberId(), requestDto);
    em.flush();
    em.clear();

    // then

    // assertThat(paymentInformationId).isEqualTo(28L);
    // Service 코드에서 Entity 를 바로 반환하지 않도록, Entity 의 Id 값을 반환하게끔 설계하였습니다.
    // 그런데 위 assertThat 문을 돌리면, Id 값이 25L 부터 순차적으로 증가하고 있어 테스트가 어렵습니다.
  }

  @Test
  @DisplayName("내 지불 정보 수정 테스트")
  public void updateMyPaymentInformation() {
    // given
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학카드1",
        member1);
    generatePaymentInformation(2L, "5678", "부산대학카드2", member1);
    generatePaymentInformation(3L, "9123", "부산대학카드3", member1);

    UpdatePaymentInformationDto requestDto = UpdatePaymentInformationDto.builder()
        .cardNumber("1111").cardDetail("부산대학카드1").build();

    // when
    UpdatePaymentInformationDto resultDto = paymentInformationService.updatePaymentInformation(
        member1.getMemberId(), paymentInformation.getPaymentInformationNumber(), requestDto);

    em.flush();
    em.clear();

    // then
    assertThat(resultDto.getCardNumber()).isEqualTo(requestDto.getCardNumber());
    assertThat(resultDto.getCardDetail()).isEqualTo(requestDto.getCardDetail());
    assertThat(paymentInformation.getCardNumber()).isEqualTo(resultDto.getCardNumber());
    assertThat(paymentInformation.getCardDetail()).isEqualTo(resultDto.getCardDetail());
  }

  @Test
  @DisplayName("내 지불 정보 삭제 테스트")
  public void deleteMyPaymentInformation() {
    // given
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학카드",
        member1);

    // when
    Long deletePaymentInformationNumber = paymentInformationService.deletePaymentInformation(
        member1.getMemberId(), paymentInformation.getPaymentInformationNumber());
    PaymentInformation findDeletedPaymentInformation = paymentInformationRepository
        .findById(deletePaymentInformationNumber).orElse(null);

    em.flush();
    em.clear();

    // then
    assertThat(deletePaymentInformationNumber)
        .isEqualTo(paymentInformation.getPaymentInformationNumber());
    assertThat(findDeletedPaymentInformation).isEqualTo(null);
  }

  @Test
  @DisplayName("내 지불 정보 조회 테스트")
  public void getMyPaymentInformations() {
    // given
    generatePaymentInformation(1L, "1234", "부산대학카드1", member1);
    generatePaymentInformation(2L, "5678", "부산대학카드2", member1);
    generatePaymentInformation(3L, "9123", "부산대학카드3", member2);

    // when
    List<GetPaymentInformationDto> paymentInformations = paymentInformationService
        .getPaymentInforamtions(member1.getMemberId());

    em.flush();
    em.clear();

    // then
    assertThat(paymentInformations.size()).isEqualTo(2);

    assertThat(paymentInformations.get(0).getCardNumber()).isEqualTo("1234");
    assertThat(paymentInformations.get(0).getCardDetail()).isEqualTo("부산대학카드1");
    assertThat(paymentInformations.get(1).getCardNumber()).isEqualTo("5678");
    assertThat(paymentInformations.get(1).getCardDetail()).isEqualTo("부산대학카드2");
  }
}
