package cn.com.pfinfo.demo.annotation;

import cn.com.pfinfo.demo.util.constant.Operation;

public @interface LogAnnotation {
    Operation op() default Operation.QUERY;
}
