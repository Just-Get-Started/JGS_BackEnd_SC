package com.example.conferenceservice.Exception;

public enum ConferenceExceptionType implements ExceptionType {
    INVALID_AUTHORITY(401, "권한이 없는 요청입니다."),
    CONFERENCE_NOT_FOUND(404, "존재하지 않는 대회 이름입니다."),
    CONFERENCE_SAVE_ERROR(400, "대회를 생성하는데 실패하였습니다."),
    DUPLICATION_CONFERENCE_NAME(400, "이미 존재하는 대회 이름입니다.");

    private int errorCode;
    private String errorMessage;

    ConferenceExceptionType(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() { return errorCode; }

    @Override
    public String getErrorMessage() { return errorMessage; }
}