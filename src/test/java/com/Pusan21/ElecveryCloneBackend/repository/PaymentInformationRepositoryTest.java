package com.Pusan21.ElecveryCloneBackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.helper.PaymentInformationRepositoryTestHelper;
import com.Pusan21.ElecveryCloneBackend.helper.RandomMemberGenerator;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentInformationRepositoryTest extends
    PaymentInformationRepositoryTestHelper {

  @Autowired
  MemberRepository memberRepository;

  @Test
  @DisplayName("내 지불 정보 등록 테스트")
  public void addMyPaymentInformation() {
    // given
    Member member = RandomMemberGenerator.generateTo(memberRepository, "member");
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학교학생지원카드",
        member);

    em.flush();
    em.clear();

    // when
    PaymentInformation findPaymentInformation = paymentInformationRepository.findById(
        paymentInformation.getPaymentInformationNumber()).orElse(null);

    // then
    assertThat(findPaymentInformation).isNotNull();
    assertThat(paymentInformation.getPaymentInformationNumber()).isEqualTo(
        findPaymentInformation.getPaymentInformationNumber());
  }

  @Test
  @DisplayName("내 지불 정보 삭제 테스트")
  public void deleteMyPaymentInformation() {
    // given
    Member member = RandomMemberGenerator.generateTo(memberRepository, "member");
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학교학생지원카드",
        member);

    em.flush();
    em.clear();

    // when
    paymentInformationRepository.delete(paymentInformation);
    PaymentInformation findPaymentInformation = paymentInformationRepository.findById(
        paymentInformation.getPaymentInformationNumber()).orElse(null);

    // then
    assertThat(findPaymentInformation).isEqualTo(null);
  }

  @Test
  @DisplayName("내 지불 정보 조회, 등록한 지불 정보 개수 테스트")
  public void getMyPaymentInformations() {
    // given
    Member member1 = RandomMemberGenerator.generateTo(memberRepository, "member1");
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학교학생지원카드",
        member1);
    generatePaymentInformation(2L, "5678", "국민카드",
        member1);
    Member member2 = RandomMemberGenerator.generateTo(memberRepository, "member2");
    generatePaymentInformation(3L, "9123", "컴퓨터공학카드",
        member2);

    em.flush();
    em.clear();

    // when
    List<PaymentInformation> paymentInformations = paymentInformationRepository.findAllByMember(
        member1);
    PaymentInformation findPaymentInformation = paymentInformationRepository.findById(
        paymentInformation.getPaymentInformationNumber()).orElse(null);

    // then
    assertThat(findPaymentInformation).isNotNull();
    assertThat(paymentInformation.getPaymentInformationNumber()).isEqualTo(
        findPaymentInformation.getPaymentInformationNumber());
    assertThat(paymentInformation.getCardNumber()).isEqualTo(
        findPaymentInformation.getCardNumber());
    assertThat(paymentInformation.getCardDetail()).isEqualTo(
        findPaymentInformation.getCardDetail());
    assertThat(paymentInformation.getMember().getMemberId()).isEqualTo(
        findPaymentInformation.getMember().getMemberId());

    assertThat(paymentInformations.size()).isEqualTo(2);
    assertThat(paymentInformation.getMember().getMemberId()).isEqualTo(
        findPaymentInformation.getMember().getMemberId());
  }
}
