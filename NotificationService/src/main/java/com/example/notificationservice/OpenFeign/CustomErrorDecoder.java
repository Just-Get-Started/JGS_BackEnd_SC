package com.example.notificationservice.OpenFeign;

import com.example.notificationservice.Exception.BusinessLogicException;
import com.example.notificationservice.Exception.ExceptionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        // JSON에서 ErrorCode와 ErrorMessage 추출
        String errorMessage = "Unknown error"; // 기본 에러 메시지
        int errorCode = response.status(); // 상태 코드 사용

        // 응답 본문에서 ErrorMessage 추출
        if (response.body() != null) {
            try {
                // JSON 응답 본문을 Map으로 변환
                Map<String, Object> errorResponse = objectMapper.readValue(response.body().asInputStream(), Map.class);
                errorMessage = (String) errorResponse.getOrDefault("errorMessage", errorMessage); // ErrorMessage 추출
            } catch (IOException e) {
                // JSON 파싱 오류 처리 (파싱이 실패할 경우 기본 에러 메시지 사용)
                errorMessage = "Failed to parse error message";
            }
        }

        // 예외 생성
        if (response.status() >= 400 && response.status() < 500) {
            return new BusinessLogicException(new CustomExceptionType(errorCode, errorMessage));
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    // CustomExceptionType 클래스 정의 (ExceptionType 인터페이스 구현)
    private static class CustomExceptionType implements ExceptionType {
        private final int errorCode;
        private final String errorMessage;

        public CustomExceptionType(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        @Override
        public int getErrorCode() {
            return errorCode;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
