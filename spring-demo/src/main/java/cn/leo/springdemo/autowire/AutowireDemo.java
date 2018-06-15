package cn.leo.springdemo.autowire;

import cn.leo.springdemo.autowire.FooService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        FooService service = (FooService) ac.getBean("fooService");



        System.out.println(service);

//		UserUser userUser2 = new UserUser();
//		userUser2.setUsername("yoo");
//		userUser2.setCellphone("15166668899");
//		service.saveUser(userUser2);

    }
}
