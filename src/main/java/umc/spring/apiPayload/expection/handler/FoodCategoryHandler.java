package umc.spring.apiPayload.expection.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.expection.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
