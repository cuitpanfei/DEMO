package cn.com.pfinfo.demo.util.context;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ControllerURLHandlerCatch {
    public static final String BEAN = "bean";
    public static final String METHOD = "method";
    private static Map<String, Map<String, Object>> controllers = new HashMap();

    public ControllerURLHandlerCatch() {
    }

    public static Map<String, Map<String, Object>> getControllers() {
        return controllers;
    }

    public void setControllers(Map<String, Map<String, Object>> controllers) {
        controllers = controllers;
    }
}
