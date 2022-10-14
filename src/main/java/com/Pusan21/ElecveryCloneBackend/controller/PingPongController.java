package com.Pusan21.ElecveryCloneBackend.controller;

import com.Pusan21.ElecveryCloneBackend.dto.PingPongDto;
import com.Pusan21.ElecveryCloneBackend.service.PingPongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PingPongController {

  private final PingPongService pingPongService;

  @GetMapping("/ping")
  public PingPongDto pingPong() {
    PingPongDto pong = pingPongService.pong();
    log.info(pong.toString());
    return pong;
  }
}
