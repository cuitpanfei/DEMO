package cn.com.pfinfo.demo.util.exception;

public class NullException extends Exception {
    public NullException() {
    }

    public NullException(String msg) {
        super(msg);
    }

    public NullException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
