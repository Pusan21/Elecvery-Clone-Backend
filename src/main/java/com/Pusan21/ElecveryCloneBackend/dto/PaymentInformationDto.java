package com.Pusan21.ElecveryCloneBackend.dto;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PaymentInformationDto {

  @Getter
  @Setter
  @Builder
  public static class GetPaymentInformationDto {

    @NotNull
    private String cardNumber;
    @NotNull
    private String cardDetail;

    public static GetPaymentInformationDto from(PaymentInformation paymentInformation) {
      return GetPaymentInformationDto.builder()
          .cardNumber(paymentInformation.getCardNumber())
          .cardDetail(paymentInformation.getCardDetail())
          .build();
    }
  }

  @Getter
  @Setter
  @Builder
  public static class AddPaymentInformationDto {

    @NotNull
    private String cardNumber;
    @NotNull
    private String cardDetail;

    public PaymentInformation toEntity(Member member) {
      return PaymentInformation.builder()
          .cardNumber(this.cardNumber)
          .cardDetail(this.cardDetail)
          .member(member)
          .build();
    }
  }

  @Getter
  @Setter
  @Builder
  public static class UpdatePaymentInformationDto {

    @NotNull
    private String cardNumber;
    @NotNull
    private String cardDetail;

    public static UpdatePaymentInformationDto from(PaymentInformation paymentInformation) {
      return UpdatePaymentInformationDto.builder()
          .cardNumber(paymentInformation.getCardNumber())
          .cardDetail(paymentInformation.getCardDetail())
          .build();
    }
  }
}
