package cn.leo.demo.eureka.consumer.feign;

import cn.leo.demo.eureka.provider.facade.TestFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "hello-provider")
public interface TestFacadeFeign extends TestFacade {
}
