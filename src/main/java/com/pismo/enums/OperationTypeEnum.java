package com.pismo.enums;

public enum OperationTypeEnum {
    PURCHASE(1),
    INSTALLMENT_PURCHASE(2),
    WITHDRAWAL(3),
    PAYMENT(4);

    private final int code;

    OperationTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
