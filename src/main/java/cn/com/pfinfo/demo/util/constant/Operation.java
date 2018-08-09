package cn.com.pfinfo.demo.util.constant;

public enum Operation {
    ADD((byte)1, "新增"),
    DEL((byte)2, "删除"),
    MODIFY((byte)4, "修改"),
    QUERY((byte)8, "查询");

    private Byte code;
    private String description;

    private Operation(Byte code, String description) {
        this.code = code;
        this.description = description;
    }

    public static boolean isHasOperationCodes(byte permission, Operation operation) {
        return OperationConstant.isHasOperationCode(permission, operation.code);
    }

    public static boolean hasAddOperationCodes(byte permission) {
        return isHasOperationCodes(permission, ADD);
    }

    public static boolean hasDelOperationCodes(byte permission) {
        return isHasOperationCodes(permission, DEL);
    }

    public static boolean hasModifyOperationCodes(byte permission) {
        return isHasOperationCodes(permission, MODIFY);
    }

    public Byte getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
