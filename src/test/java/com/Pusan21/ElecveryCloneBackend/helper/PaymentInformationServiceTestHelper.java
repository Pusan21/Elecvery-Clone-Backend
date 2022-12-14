package com.Pusan21.ElecveryCloneBackend.helper;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.PaymentInformationRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentInformationServiceTestHelper {

  @Autowired
  protected EntityManager em;
  @Autowired
  protected MemberRepository memberRepository;

  @Autowired
  protected PaymentInformationRepository paymentInformationRepository;

  protected PaymentInformation generatePaymentInformation(Long paymentInformationNumber,
      String cardNumber, String cardDetail,
      Member member) {
    return paymentInformationRepository.save(
        PaymentInformation.builder()
            .paymentInformationNumber(paymentInformationNumber)
            .cardNumber(cardNumber)
            .cardDetail(cardDetail)
            .member(member)
            .build()
    );
  }
}
