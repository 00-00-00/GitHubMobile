package com.ground0.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zer0 on 17/10/16.
 */
public class RestApiException extends Throwable {
  public int getHttpStatus() {
    return httpStatus;
  }

  private int httpStatus;

  public Error getError() {
    return error;
  }

  private Error error;

  public RestApiException(int httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public RestApiException(int httpStatus, String message, ObjectMapper objectMapper) {
    super(message);
    this.httpStatus = httpStatus;
    parseError(objectMapper, message);
  }

  private void parseError(ObjectMapper objectMapper, String message) {
    try {
      this.error = objectMapper.readValue(message, Error.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
