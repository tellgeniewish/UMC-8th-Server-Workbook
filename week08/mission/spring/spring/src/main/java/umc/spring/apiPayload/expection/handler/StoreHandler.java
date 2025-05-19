package umc.spring.apiPayload.expection.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.expection.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
