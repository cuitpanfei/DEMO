package cn.com.pfinfo.demo.base;

import org.springframework.amqp.core.Message;

public interface ControllerHandler {
    void invoke(Message var1);
}
