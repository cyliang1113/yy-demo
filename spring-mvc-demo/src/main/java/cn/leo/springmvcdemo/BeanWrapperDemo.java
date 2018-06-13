package cn.leo.springmvcdemo;

import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.servlet.HttpServletBean;
import sun.applet.Main;

import static org.apache.struts2.ServletActionContext.getServletContext;

public class BeanWrapperDemo {
    public static void main(String[] args) {
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("name", "li");
        Person person = new Person();
        BeanWrapper bw = new BeanWrapperImpl(person);
        bw.setPropertyValues(pvs);
        System.out.print(person);
    }

    private static class Person{

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "{name:" + this.name + "}";
        }
    }
}
