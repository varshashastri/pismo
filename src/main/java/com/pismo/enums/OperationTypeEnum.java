package com.pismo.enums;

public enum OperationTypeEnum {
    PURCHASE(1L),
    INSTALLMENT_PURCHASE(2L),
    WITHDRAWAL(3L),
    PAYMENT(4L);

    private final long code;

    OperationTypeEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public static OperationTypeEnum fromCode(long code) {
        for (OperationTypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid OperationType code: " + code);
    }
}
