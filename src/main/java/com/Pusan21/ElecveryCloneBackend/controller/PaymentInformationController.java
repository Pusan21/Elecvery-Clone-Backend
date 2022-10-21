package com.Pusan21.ElecveryCloneBackend.controller;

import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.AddPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.GetPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.UpdatePaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.service.PaymentInformationService;
import com.sun.istack.NotNull;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment-information")
@RequiredArgsConstructor
public class PaymentInformationController {

  private final PaymentInformationService paymentInformationService;

  @GetMapping("/members/{memberId}")
  public List<GetPaymentInformationDto> getPaymentInformations(
      @PathVariable @NotNull Long memberId
  ) {
    return paymentInformationService.getPaymentInforamtions(memberId);
  }

  @DeleteMapping("/members/{memberId}/cards/{cardId}")
  public Long deletePaymentInformation(
      @PathVariable @NotNull Long memberId,
      @PathVariable @NotNull Long cardId
  ) {
    return paymentInformationService.deletePaymentInformation(memberId, cardId);
  }

  @PostMapping("/members/{memberId}")
  public Long addPaymentInformation(
      @PathVariable @NotNull Long memberId,
      @RequestBody @Valid AddPaymentInformationDto requestDto
  ) {
    return paymentInformationService.addPaymentInformation(memberId, requestDto);
  }

  @PutMapping("/members/{memberId}/cards/{cardId}")
  public UpdatePaymentInformationDto updatePaymentInformation(
      @PathVariable @NotNull Long memberId,
      @PathVariable @NotNull Long cardId,
      @RequestBody @Valid PaymentInformationDto.UpdatePaymentInformationDto requestDto
  ) {
    return paymentInformationService.updatePaymentInformation(memberId, cardId, requestDto);
  }

}
