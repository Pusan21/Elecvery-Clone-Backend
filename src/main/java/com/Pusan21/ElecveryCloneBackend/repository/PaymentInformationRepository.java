package com.Pusan21.ElecveryCloneBackend.repository;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInformationRepository extends JpaRepository<PaymentInformation, Long> {

  List<PaymentInformation> findAllByMember(Member member);


  PaymentInformation findPaymentInformationByMemberAndPaymentInformationNumber(Member member,
      Long paymentInformationNumber);
}
