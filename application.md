### 一、springcloud依赖

```
<properties>
        <maven.compiler.sourece>17</maven.compiler.sourece>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-cloud-version>2023.0.3</spring-cloud-version>
        <spring-cloud-alibaba-version>2023.0.3.2</spring-cloud-alibaba-version>
    </properties>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

### 二、application-nacos.yaml

~~~java
spring:
  config:
    import:
      - nacos:service-product?public
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        username: nacos
        password: nacos
        namespace: public
      config:
        server-addr: 127.0.0.1:8848
        username: nacos
        password: nacos
        namespace: public
        file-extension: yaml
        group: DEFAULT_GROUP
        refresh-enabled: true # 支持配置动态刷新
        import-check:
          enabled: false #禁用nacos配置
nacos:
  core:
    auth:
      enabled: true
      token:
        secret:
          key: eTQlWDF1OSRUcSNvQTdyUHoyKldsOEBKZjYhZVZtM1E= # 和 Nacos 服务端一致


~~~

### 三、使用docker-compose安装nacos和mysql

~~~java
version: '3.8'  # Docker Compose 版本

services:
  # -----------------------------
  # MySQL 服务
  # -----------------------------
  my-mysql:
    image: mysql:8.0.32  # MySQL 镜像版本
    container_name: my-mysql  # 容器名称
    ports:
      - "3306:3306"  # 映射宿主机 3306 到容器 3306
    environment:
      MYSQL_ROOT_PASSWORD: xuxiaolei        # 设置 MySQL root 密码
      MYSQL_DATABASE: nacos_config          # 默认创建 nacos_config 数据库（Nacos 会用）
      MYSQL_USER: xuxiaolei                 # 创建一个普通用户
      MYSQL_PASSWORD: xuxiaolei             # 普通用户密码
    volumes:
      - ./mysql/data:/var/lib/mysql         # 持久化数据库数据到宿主机
      - ./mysql/init.sql:/docker-entrypoint-initdb.d/init.sql  # 初始化 SQL 脚本（第一次启动会自动执行）
    command:
      --default-authentication-plugin=mysql_native_password  # 避免新版 MySQL 驱动认证问题
    restart: always  # 容器退出自动重启

  # -----------------------------
  # Nacos 服务
  # -----------------------------
  nacos:
    image: nacos/nacos-server:v2.3.2  # Nacos 镜像
    container_name: nacos  # 容器名称
    environment:
      # Nacos 基础配置
      - MODE=standalone            # 单机模式
      - PREFER_HOST_MODE=hostname  # 用宿主机名注册自己
      - NACOS_SERVER_PORT=8848     # HTTP 访问端口
      - NACOS_AUTH_ENABLE=true     # 启用账号密码认证
      - NACOS_AUTH_IDENTITY_KEY=serverIdentity  # 自定义身份 key
      - NACOS_AUTH_IDENTITY_VALUE=security     # 自定义身份 value
      - NACOS_AUTH_TOKEN=eTQlWDF1OSRUcSNvQTdyUHoyKldsOEBKZjYhZVZtM1E=  # 自定义 token
      - JVM_XMS=512m  # JVM 最小内存
      - JVM_XMX=512m  # JVM 最大内存
      - JVM_XMN=256m  # JVM 年轻代内存

      # -----------------------------
      # MySQL 数据源配置（必须）
      # -----------------------------
      - SPRING_DATASOURCE_PLATFORM=mysql  # 告诉 Nacos 用 MySQL 数据库，而不是内存 DB
      - MYSQL_SERVICE_HOST=my-mysql       # MySQL 主机名（docker-compose service 名）
      - MYSQL_SERVICE_DB_NAME=nacos_config  # 数据库名
      - MYSQL_SERVICE_PORT=3306           # MySQL 端口
      - MYSQL_SERVICE_USER=root           # MySQL 用户
      - MYSQL_SERVICE_PASSWORD=xuxiaolei  # MySQL 密码
      - MYSQL_DATABASE_NUM=1              # 数据库实例数，单机模式写 1

    depends_on:
      - my-mysql  # 启动 Nacos 之前先启动 MySQL 容器（注意：只保证容器启动，不保证 MySQL 已可用）

    ports:
      - "8848:8848"  # Nacos HTTP 端口映射
      - "9848:9848"  # Nacos gRPC 端口映射
      - "9849:9849"  # Nacos gRPC 端口映射

    volumes:
      - ./nacos-data/logs:/home/nacos/logs       # 日志持久化
      - ./nacos-data/data:/home/nacos/data       # Nacos 配置数据持久化
      - ./nacos-data/init.d/custom.properties:/home/nacos/init.d/custom.properties  # 自定义 Nacos 配置文件

    restart: unless-stopped  # 容器异常退出自动重启
~~~

### 四、nacos依赖

```
	    <!--服务发现-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--配置中心-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
```

### 五、openfeign依赖

​	由于openfeign默认开启负载均衡的，所以需要加入负载均衡依赖

```
 		<!--feign-->
 		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--负载均衡-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>
```

​	需要在启动类上加上@EnableFeignClients，开启feign

### 六、RestTemplate

​	如果是使用RestTemplate来调用微服务，需要进行如下操作

#### 1.把RestTemplate加入Bean方便调用

~~~java
@Configuration
public class RestTemplateConfig {
    /*
     * 创建 RestTemplate Bean
     * 可在需要调用其他微服务的地方注入使用
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
~~~

#### 2.调用微服务

​	通过在nacos注册的服务名获取Url,并加上路径来调用微服务

~~~java
@Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void feignClientTest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        String uri = instances.get(0).getUri().toString() + "/test";

        System.out.println("uri = " + uri);
        String forObject = restTemplate.getForObject(uri, String.class);
        System.out.println("forObject = " + forObject);
    }

~~~

#### 3.RestTemplate负载均衡

​	RestTemplate加入负载均衡后就不同获取url，直接通过在nacos注册的微服务名加上对应路径来调用微服务，实现负载均衡很简单，就在RestTemplate方法上加上@LoadBalanced即可。

```java
@Configuration
public class RestTemplateConfig {
    /*
     * 创建 RestTemplate Bean
     * 可在需要调用其他微服务的地方注入使用
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

​	这样就可以通过nacos注册的服务名进行调用微服务，不用获取url了

~~~java
public String getTest(){
    // 微服务 URL，假设使用服务名 + Nacos 注册发现
    String url = "http://service-product/test";
    // 调用微服务 GET 方法，返回 String 类型数据
    return restTemplate.getForObject(url,String.class);
   }
~~~

