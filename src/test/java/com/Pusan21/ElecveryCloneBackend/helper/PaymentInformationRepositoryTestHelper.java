package com.Pusan21.ElecveryCloneBackend.helper;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.PaymentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PaymentInformationRepositoryTestHelper {

  @Autowired
  protected EntityManager em;
  @Autowired
  protected MemberRepository memberRepository;
  @Autowired
  protected PaymentInformationRepository paymentInformationRepository;

  protected PaymentInformation generatePaymentInformation(
      String cardNumber, String cardDetail,
      Member member) {
    return paymentInformationRepository.save(
        PaymentInformation.builder()
            .cardNumber(cardNumber)
            .cardDetail(cardDetail)
            .member(member)
            .build()
    );
  }
}
