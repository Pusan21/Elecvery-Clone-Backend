package com.Pusan21.ElecveryCloneBackend.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.AddPaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.dto.PaymentInformationDto.UpdatePaymentInformationDto;
import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.PaymentInformation;
import com.Pusan21.ElecveryCloneBackend.helper.RandomMemberGenerator;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.PaymentInformationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class PaymentInformationControllerTest {

  private Member member1;
  private Member member2;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PaymentInformationRepository paymentInformationRepository;

  /*
   * Member Table ?????? 1L, 2L, 3L??? Id ?????? ?????? ??? 3?????? ?????? ???????????? ????????????.
   */
  @BeforeEach()
  public void setUp() {
    member1 = RandomMemberGenerator.generateTo(memberRepository, "member1");
    member2 = RandomMemberGenerator.generateTo(memberRepository, "member2");
  }

  @Test
  @DisplayName("[SUCCESS] ??? ?????? ?????? ?????? ?????????")
  public void addMyPaymentInformation() throws Exception {
    AddPaymentInformationDto requestDto = AddPaymentInformationDto.builder().cardNumber("1234")
        .cardDetail("??????????????????").build();

    mockMvc.perform(RestDocumentationRequestBuilders.post("/payment-information/members/{memberId}",
                member1.getMemberId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(requestDto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-add",
            pathParameters(
                parameterWithName("memberId").description("????????? ???????????? ????????? ID")
            ),
            requestFields(
                fieldWithPath("cardNumber").description("?????? ??????"),
                fieldWithPath("cardDetail").description("?????? ?????? ??????")
            ),
            responseFields(
                fieldWithPath("data").description("????????? ?????? ????????? ID")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] ??? ?????? ?????? ?????? ?????????")
  public void getMyPaymentInformations() throws Exception {
    generatePaymentInformation(1L, "1234", "??????????????????1", member1);
    generatePaymentInformation(2L, "5678", "??????????????????2", member1);
    generatePaymentInformation(3L, "9123", "??????????????????3", member2);

    mockMvc.perform(RestDocumentationRequestBuilders.get("/payment-information/members/{memberId}",
                member1.getMemberId())
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-get",
            pathParameters(
                parameterWithName("memberId").description("????????? ???????????? ????????? ID")
            ),
            responseFields(
                fieldWithPath("[].cardNumber").description("?????? ??????"),
                fieldWithPath("[].cardDetail").description("?????? ?????? ??????")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] ??? ?????? ?????? ?????? ?????????")
  public void updateMyPaymentInformation() throws Exception {
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "??????????????????1",
        member1);

    UpdatePaymentInformationDto requestDto = UpdatePaymentInformationDto.builder()
        .cardNumber("1111").cardDetail("??????????????????1").build();

    mockMvc.perform(put("/payment-information/members/{memberId}/cards/{cardId}",
            member1.getMemberId(), paymentInformation.getPaymentInformationNumber())
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(requestDto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-update",
            pathParameters(
                parameterWithName("memberId").description("????????? ???????????? ????????? ID"),
                parameterWithName("cardId").description("??????????????? ??????????????? ID")
            ),
            requestFields(
                fieldWithPath("cardNumber").description("?????? ??????"),
                fieldWithPath("cardDetail").description("?????? ?????? ??????")
            ),
            responseFields(
                fieldWithPath("cardNumber").description("?????? ??????"),
                fieldWithPath("cardDetail").description("?????? ?????? ??????")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] ??? ?????? ?????? ?????? ?????????")
  public void deleteMyPaymentInformation() throws Exception {
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "??????????????????",
        member1);

    mockMvc.perform(delete(
            "/payment-information/members/{memberId}/cards/{cardId}",
            member1.getMemberId(), paymentInformation.getPaymentInformationNumber())
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-delete",
            pathParameters(
                parameterWithName("memberId").description("????????? ???????????? ????????? ID"),
                parameterWithName("cardId").description("??????????????? ??????????????? ID")
            ),
            responseFields(
                fieldWithPath("data").description("????????? ?????? ????????? ID")
            )));
  }

  private PaymentInformation generatePaymentInformation(Long paymentInformationNumber,
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

  protected String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.registerModule(new JavaTimeModule()).writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
