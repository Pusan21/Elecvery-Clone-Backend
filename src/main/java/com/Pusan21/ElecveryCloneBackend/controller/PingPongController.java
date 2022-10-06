package com.Pusan21.ElecveryCloneBackend.controller;

import com.Pusan21.ElecveryCloneBackend.dto.PingPongDto;
import com.Pusan21.ElecveryCloneBackend.service.PingPongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PingPongController {
  private final PingPongService pingPongService;

  @GetMapping("/ping")
  public PingPongDto pingPong() {
    return pingPongService.pong();
  }
}
