package cn.leo.demo.eureka.consumer.feign;

import cn.leo.demo.eureka.provider.facade.UserServiceFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "hello-provider")
public interface UserServiceFacadeFeign extends UserServiceFacade {
}
