package cn.leo.springdemo.autowire;

public class FooService {
    private FooDao fooDao;

    public FooDao getFooDao() {
        return fooDao;
    }

    public void setFooDao(FooDao fooDao) {
        this.fooDao = fooDao;
    }

    public void setAbc(){

    }
}
