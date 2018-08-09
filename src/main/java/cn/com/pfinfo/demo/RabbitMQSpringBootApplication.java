package cn.com.pfinfo.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cuitpf
 * @create 2018/08/09 下午8:54
 * @since 1.0
 */
@EnableRabbit
@SpringBootApplication
public class RabbitMQSpringBootApplication {

    public static void main(String[] args){
        SpringApplication.run(RabbitMQSpringBootApplication.class,args);
    }
}
