package com.Pusan21.ElecveryCloneBackend.controller;

import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.AddMyCarDto;
import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.GetMyCarDto;
import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.UpdateMyCarDto;
import com.Pusan21.ElecveryCloneBackend.service.MyCarService;
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
@RequestMapping("/my-cars")
@RequiredArgsConstructor
public class MyCarController {

  private final MyCarService myCarService;

  @GetMapping("/members/{memberId}")
  public List<GetMyCarDto> getMyCars(
      @PathVariable @NotNull Long memberId
  ) {
    return myCarService.getMyCars(memberId);
  }

  @PostMapping("/members/{memberId}")
  public Long addMyCar(
      @PathVariable @NotNull Long memberId,
      @RequestBody @Valid AddMyCarDto requestDto
  ) {
    return myCarService.addMyCar(memberId, requestDto);
  }

  @PutMapping("/members/{memberId}/myCars/{myCarsId}")
  public UpdateMyCarDto updateMyCar(
      @PathVariable @NotNull Long memberId,
      @PathVariable @NotNull Long myCarsId,
      @RequestBody @Valid UpdateMyCarDto requestDto
  ) {
    return myCarService.updateMyCar(memberId, myCarsId, requestDto);
  }

  @DeleteMapping("/members/{memberId}/myCars/{myCarsId}")
  public Long deletePaymentInformation(
      @PathVariable @NotNull Long memberId,
      @PathVariable @NotNull Long myCarsId
  ) {
    return myCarService.deleteMyCar(memberId, myCarsId);
  }

}
