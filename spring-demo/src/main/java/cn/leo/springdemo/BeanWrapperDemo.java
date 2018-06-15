package cn.leo.springdemo;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

import java.beans.PropertyDescriptor;

public class BeanWrapperDemo {
    public static void main(String[] args) {
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("name", "li");
        //pvs.add("age", 18); //由于没有setAge方法, 会报错
        Person person = new Person();
        BeanWrapper bw = new BeanWrapperImpl(person);

        PropertyDescriptor[] propertyDescriptors = bw.getPropertyDescriptors();

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
