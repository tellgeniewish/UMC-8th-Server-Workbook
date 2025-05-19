package umc.spring.apiPayload.expection.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.expection.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
