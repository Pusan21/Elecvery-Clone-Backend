package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.dto.PingPongDto;
import org.springframework.stereotype.Service;

@Service
public class PingPongService {
  public PingPongDto pong() {
    return new PingPongDto();
  }
}
