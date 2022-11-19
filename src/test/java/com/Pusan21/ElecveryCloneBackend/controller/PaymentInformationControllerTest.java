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
   * Member Table 에는 1L, 2L, 3L의 Id 값을 가진 총 3개의 더미 데이터가 있습니다.
   */
  @BeforeEach()
  public void setUp() {
    member1 = RandomMemberGenerator.generateTo(memberRepository, "member1");
    member2 = RandomMemberGenerator.generateTo(memberRepository, "member2");
  }

  @Test
  @DisplayName("[SUCCESS] 내 지불 정보 등록 테스트")
  public void addMyPaymentInformation() throws Exception {
    AddPaymentInformationDto requestDto = AddPaymentInformationDto.builder().cardNumber("1234")
        .cardDetail("부산대학카드").build();

    mockMvc.perform(RestDocumentationRequestBuilders.post("/payment-information/members/{memberId}",
                member1.getMemberId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(requestDto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-add",
            pathParameters(
                parameterWithName("memberId").description("카드를 등록하는 멤버의 ID")
            ),
            requestFields(
                fieldWithPath("cardNumber").description("카드 번호"),
                fieldWithPath("cardDetail").description("카드 상세 설명")
            ),
            responseFields(
                fieldWithPath("data").description("추가한 지불 정보의 ID")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] 내 지불 정보 조회 테스트")
  public void getMyPaymentInformations() throws Exception {
    generatePaymentInformation(1L, "1234", "부산대학카드1", member1);
    generatePaymentInformation(2L, "5678", "부산대학카드2", member1);
    generatePaymentInformation(3L, "9123", "부산대학카드3", member2);

    mockMvc.perform(RestDocumentationRequestBuilders.get("/payment-information/members/{memberId}",
                member1.getMemberId())
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-get",
            pathParameters(
                parameterWithName("memberId").description("정보를 조회하는 멤버의 ID")
            ),
            responseFields(
                fieldWithPath("[].cardNumber").description("카드 번호"),
                fieldWithPath("[].cardDetail").description("카드 상세 정보")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] 내 지불 정보 수정 테스트")
  public void updateMyPaymentInformation() throws Exception {
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학카드1",
        member1);

    UpdatePaymentInformationDto requestDto = UpdatePaymentInformationDto.builder()
        .cardNumber("1111").cardDetail("부산대학카드1").build();

    mockMvc.perform(put("/payment-information/members/{memberId}/cards/{cardId}",
            member1.getMemberId(), paymentInformation.getPaymentInformationNumber())
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(requestDto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-update",
            pathParameters(
                parameterWithName("memberId").description("카드를 수정하는 멤버의 ID"),
                parameterWithName("cardId").description("수정하려는 지불정보의 ID")
            ),
            requestFields(
                fieldWithPath("cardNumber").description("카드 번호"),
                fieldWithPath("cardDetail").description("카드 상세 설명")
            ),
            responseFields(
                fieldWithPath("cardNumber").description("카드 번호"),
                fieldWithPath("cardDetail").description("카드 상세 정보")
            )));
  }

  @Test
  @DisplayName("[SUCCESS] 내 지불 정보 삭제 테스트")
  public void deleteMyPaymentInformation() throws Exception {
    PaymentInformation paymentInformation = generatePaymentInformation(1L, "1234", "부산대학카드",
        member1);

    mockMvc.perform(delete(
            "/payment-information/members/{memberId}/cards/{cardId}",
            member1.getMemberId(), paymentInformation.getPaymentInformationNumber())
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("payment-info-delete",
            pathParameters(
                parameterWithName("memberId").description("정보를 조회하는 멤버의 ID"),
                parameterWithName("cardId").description("삭제하려는 지불정보의 ID")
            ),
            responseFields(
                fieldWithPath("data").description("삭제한 지불 정보의 ID")
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
