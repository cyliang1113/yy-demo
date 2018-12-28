
# Niudun工程

## 工程结构说明

- `config` bean配置
- `interceptor` 拦截器
- `controller` 控制器
- `service` 服务
- `dao` DAO
- `entity` 实体


## local

- 启动zookeeper（如果有）
- start `com.cheguo.niudun.console.server.NiudunApplication`

### 默认端口

- `web 8080`
- `dubbo 28080`


## 单元测试

- 单元测试类**只允许**放在`src/test/com/cheguo/niudun/console/server/`
- 单元测试类(依赖容器)必须继承 `com.cheguo.niudun.console.server.AbstractApplicationTest`

## deploy

- `mvn deploy`

> Powerby 架构组 spy