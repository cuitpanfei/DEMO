package cn.com.pfinfo.demo.util.constant;

import cn.com.pfinfo.demo.util.exception.NullException;

class OperationConstant {
    public static final byte ADD = 1;
    public static final byte DEL = 2;
    public static final byte MODIFY = 4;
    public static final byte QUERY = 8;
    public static final byte ALL = 15;

    OperationConstant() {
    }

    public static boolean isHasOperationCode(byte permission, byte operationCode) {
        return (permission ^ operationCode ^ 15) != 0;
    }

    public static boolean isHasOperationCodes(byte permission, byte... operationCodes) throws NullException {
        byte code = 1;
        if (null != operationCodes && operationCodes.length != 0) {
            byte[] var3 = operationCodes;
            int var4 = operationCodes.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                byte one = var3[var5];
                code |= one;
            }

            return isHasOperationCode(permission, code);
        } else {
            throw new NullException("不存在参与校验的操作码");
        }
    }
}
