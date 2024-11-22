package com.example.notificationservice.API.TeamInviteNotification.ExceptionType;

import com.example.notificationservice.Exception.ExceptionType;

public enum TeamInviteExceptionType implements ExceptionType {
    TEAM_INVITE_NOT_FOUND(404, "초대 알림을 찾는데 실패하였습니다."),
    TEAM_INVITE_DELETE_ERROR(400, "초대 알림을 삭제하는데 실패하였습니다."),
    TEAM_INVITE_ALREADY_REQUEST(400, "이미 초대 알림을 보낸 사용자입니다."),
    TEAM_INVITE_READ_ERROR(400, "초대 알림을 읽는데 실패하였습니다."),
    MEMBER_INVALID_AUTHORITY(401, "권한이 없는 요청입니다."),
    TEAM_INVITE_ERROR(400, "팀에 초대하는데 실패하였습니다.");

    private int errorCode;
    private String errorMessage;

    TeamInviteExceptionType(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() { return errorCode; }

    @Override
    public String getErrorMessage() { return errorMessage; }
}
