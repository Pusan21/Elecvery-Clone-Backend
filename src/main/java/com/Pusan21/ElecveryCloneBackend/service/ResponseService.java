package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.util.SingleResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

  public <T> SingleResult<T> getSuccessSingleResult(T data) {
    SingleResult<T> result = new SingleResult<>();
    result.setData(data);
    return result;
  }
}
