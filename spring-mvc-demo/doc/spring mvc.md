#### 通过DispatcherServlet启动
#### <mvc:annotation-driven/\>
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser()), 
    AnnotationDrivenBeanDefinitionParser里加载RequestMappingHandlerMapping, RequestMappingHandlerMapping
    实现了Spring的InitializingBean. afterPropertiesSet()方法中扫描spring中的bean, 找出类名上有@Controller
    或@RequestMapping的类, 然后解析类方法上的@RequestMapping.