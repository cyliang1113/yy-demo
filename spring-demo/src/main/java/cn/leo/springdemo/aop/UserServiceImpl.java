package cn.leo.springdemo.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserServiceImpl implements UserService {
    private Log log = LogFactory.getLog(UserServiceImpl.class);
    @Override
    public String getUsername() {
        log.info("getUsername");
        return "Tom";
    }
}
