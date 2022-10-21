package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.AddPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.GetPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.UpdatePaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.PaymentInformationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentInformationService {

  private final MemberRepository memberRepository;
  private final PaymentInformationRepository paymentInformationRepository;


  /* TODO
   * 1. 추후에 JWT 로 Member Id를 받아오도록 수정이 필요해보임.
   * 2. JPA Repository 에서 데이터를 못 찾았을 경우에 알맞은 Custom Exception 을 던져야 함.
   */
  @Transactional
  public Long addPaymentInformation(Long memberId, AddPaymentInformationDto requestDto) {
    Member member = memberRepository.findById(memberId).orElse(null);
    return paymentInformationRepository.save(requestDto.toEntity(member))
        .getPaymentInformationNumber();
  }

  @Transactional
  public UpdatePaymentInformationDto updatePaymentInformation(Long memberId,
      Long paymentInformationNumber,
      UpdatePaymentInformationDto requestDto) {
    Member member = memberRepository.findById(memberId).orElse(null);

    PaymentInformation paymentInformation = paymentInformationRepository.findPaymentInformationByMemberAndPaymentInformationNumber(
        member, paymentInformationNumber);

    paymentInformation
        .updatePaymentInformation(requestDto.getCardNumber(), requestDto.getCardDetail());

    return UpdatePaymentInformationDto.from(paymentInformationRepository.save(paymentInformation));
  }

  public Long deletePaymentInformation(Long memberId, Long paymentInformationNumber) {
    Member member = memberRepository.findById(memberId).orElse(null);

    PaymentInformation paymentInformation = paymentInformationRepository.findPaymentInformationByMemberAndPaymentInformationNumber(
        member, paymentInformationNumber);

    paymentInformationRepository.delete(paymentInformation);
    return paymentInformation.getPaymentInformationNumber();
  }

  public List<GetPaymentInformationDto> getPaymentInforamtions(Long memberId) {
    Member member = memberRepository.findById(memberId).orElse(null);
    List<PaymentInformation> paymentInformations = paymentInformationRepository
        .findAllByMember(member);
    return paymentInformations.stream().map(GetPaymentInformationDto::from).toList();
  }
}
