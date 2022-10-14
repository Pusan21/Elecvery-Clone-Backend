package com.Pusan21.ElecveryCloneBackend.dto;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class PingPongDto {

  private String message;

  private ZonedDateTime time;

  public PingPongDto() {
    this.message = "pong";
    this.time = ZonedDateTime.now();
  }
}
