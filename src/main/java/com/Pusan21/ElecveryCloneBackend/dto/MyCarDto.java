package com.Pusan21.ElecveryCloneBackend.dto;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MyCarDto {

  @Getter
  @Setter
  @Builder
  public static class GetMyCarDto {
    @NotNull
    private String plateNumber;
    @NotNull
    private String carType;

    public static GetMyCarDto from(MyCar myCar) {
      return GetMyCarDto.builder()
          .plateNumber(myCar.getPlateNumber())
          .carType(myCar.getCarType())
          .build();
    }
  }

  @Getter
  @Setter
  @Builder
  public static class AddMyCarDto {
    @NotNull
    private String plateNumber;
    @NotNull
    private String carType;

    public MyCar toEntity(Member member) {
      return MyCar.builder()
          .carType(this.carType)
          .plateNumber(this.plateNumber)
          .member(member)
          .build();
    }
  }

  @Getter
  @Setter
  @Builder
  public static class UpdateMyCarDto {

    @NotNull
    private String plateNumber;
    @NotNull
    private String carType;

    public static UpdateMyCarDto from(MyCar myCar) {
      return UpdateMyCarDto.builder()
          .plateNumber(myCar.getPlateNumber())
          .carType(myCar.getCarType())
          .build();
    }
  }
}
