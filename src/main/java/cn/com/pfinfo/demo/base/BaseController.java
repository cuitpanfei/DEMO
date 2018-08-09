package cn.com.pfinfo.demo.base;

import org.springframework.amqp.core.Message;

public class BaseController implements ControllerHandler {
    public BaseController() {
    }

    public void invoke(Message msg) {
        System.out.println(msg.getBody());
    }
}
