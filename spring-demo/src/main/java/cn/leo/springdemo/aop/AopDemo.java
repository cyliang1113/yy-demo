package cn.leo.springdemo.aop;

import cn.leo.springdemo.autowire.FooService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        UserService service = (UserService) ac.getBean("userService");
        service.getUsername();


        System.out.println(service);


    }
}
