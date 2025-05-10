package umc.spring.apiPayload.expection.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.expection.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
