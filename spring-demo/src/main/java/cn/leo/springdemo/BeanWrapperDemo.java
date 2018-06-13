package cn.leo.springdemo;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

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
