package umc.spring.apiPayload.expection.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.expection.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

