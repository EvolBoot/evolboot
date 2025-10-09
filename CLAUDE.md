## 项目架构

这是一个基于 Spring Boot 3.1.5 + Java 17 的多模块微服务项目，采用 DDD（领域驱动设计）架构风格。

### 核心技术栈
- **Java**: 17
- **Spring Boot**: 3.1.5
- **Spring Cloud**: 2023.0.0
- **Spring Cloud Alibaba**: 2023.0.1.0
- **数据库**: JPA/Hibernate + MyBatis Plus + QueryDSL
- **缓存**: Redis + Redisson
- **消息队列**: Redis MQ
- **数据库迁移**: Flyway (V{version}__description.sql)
- **任务调度**: PowerJob
- **API文档**: SpringDoc (OpenAPI 3)

### 模块架构

#### 入口模块 (Entry)
- **entry-admin**: 后台管理 API 入口
- **entry-app**: 商户/用户 API 入口
- **entry-schedule**: 定时任务服务入口

#### 核心模块
- **core**: 技术基础组件，无业务逻辑， 纯技术，不涉及业务
- **shared**: 业务共享模块，纯 POJO，跨模块协作接口
- **config-resource**: 配置文件集中管理

#### 通用组件 (Component)
- **common**: 通用工具类
- **captcha**: 验证码服务
- **storage**: 存储服务 (阿里云OSS/七牛/腾讯云)
- **sms**: 短信服务
- **mq**: 消息队列抽象层 (RocketMQ/Redis/Kafka)
- **websocket**: WebSocket 支持
- **sentinel**: 限流熔断

#### 业务模块
- **identity**: 用户身份认证与权限
- **security**: 安全框架 (token/api)
- **finance**: 财务、订单、支付，被其他模块依赖
- **system**: 系统管理 (日志、配置、媒体库)，一些不知道放哪里的，可以放这里，不允许被其他模块依赖，可以直接调用其他模块的一些东西
- **ecommerce**: 电商核心业务
- **autoprice**: 自动定价
- **followsell**: 跟卖业务
- **collection**: 商品采集
- **productselection**: 商品选品
- **listing-manager**: 商品刊登管理
- **support**: 支撑服务 (货币、语言、物流)
- **bi**: 商业智能
- **pay**: 支付网关
- **schedule**: 定时任务

## 开发指南

### 构建和运行

```bash
# 编译整个项目
mvn clean compile 
# 打包
mvn clean package 

# 运行特定服务 (在对应 entry 目录下)
cd module/entry/entry-app
mvn spring-boot:run

# 或者运行打包后的 jar
java -jar target/entry-app-0.0.1.jar --spring.profiles.active=dev
```

### 环境配置

项目使用多环境配置：
- **dev**: 开发环境
- **evol**: 演示环境
- **prod**: 生产环境


配置文件位于 `module/config-resource/src/main/resources/`

### 数据库迁移

使用 Flyway 进行数据库版本管理：
- 迁移文件格式: `V{version}__description.sql`
- 位置: `module/*/src/main/resources/db/migration/`
- 版本号规范: 按模块划分 (如 V1.12.x 为 finance 模块)

### DDD 开发规范

#### 领域边界
1. **禁止直接调用其他领域的 Repository/Dao**
2. **禁止直接修改其他领域对象的状态**
3. **跨领域协作必须通过领域服务接口**

#### 分层架构
- **应用服务** (AppService): 协调多个领域服务，处理技术关注点
- **领域服务** (DomainService): 封装核心业务逻辑
- **查询服务** (QueryService): 专门负责数据查询，避免循环依赖
- **聚合根** (Entity): 充血模型，维护自身状态完整性
- **事件** (Event): 解耦领域间协作


### API 文档

使用 SpringDoc 自动生成 OpenAPI 文档：
- 访问地址: `http://localhost:8080/swagger-ui.html`
- API JSON: `http://localhost:8080/v3/api-docs`

### 常见开发任务

#### 新增业务模块
1. 在 `module/` 下创建新模块目录
2. 添加 `pom.xml` 继承父项目
3. 创建标准 DDD 分层结构
4. 在父 `pom.xml` 中添加模块依赖管理
5. 更新相关 entry 模块的依赖

#### 添加数据库迁移
1. 在模块的 `src/main/resources/db/migration/` 下创建 SQL 文件
2. 命名格式: `V{version}__description.sql`
3. 版本号按模块递增 (参考 `docs/migration-version.md`)

#### 国际化支持
消息文件位于各模块的 `src/main/resources/` 下：
- `messages.properties` (默认)
- `messages_zh_CN.properties` (中文)
- `messages_en_US.properties` (英文)

## 团队协作规范

### 开发流程

#### 1. 跨模块交互策略

**接口定义位置**
- **shared模块**：多模块共用的接口定义
- **acl防腐层**：单模块专用接口，在各自模块中实现

### 与Claude Code协作规范

#### 开发规范
1. **遵循DDD原则**：严格按照领域驱动设计规范，不违反模块边界
2. **使用代码生成器**：新聚合开发优先使用项目代码生成工具
3. **接口设计原则**：
    - 共用接口放在shared模块
    - 专用接口通过acl防腐层实现
4. **数据库迁移**：新建表结构必须通过Flyway迁移文件管理

#### 质量保证
1. **测试覆盖**：使用entry-test模块进行充分测试
2. **API文档**：确保SpringDoc自动生成完整的API文档
3. **国际化支持**：新增功能需要提供中英文消息支持
4. **代码审查**：提交前确保代码符合项目架构规范

#### 技术选型约束
- 数据访问：优先使用JPA，复杂查询使用MyBatis Plus
- 缓存：统一使用Redis + Redisson
- 消息队列：使用项目配置的Redis MQ
- 任务调度：使用PowerJob框架
- API文档：使用SpringDoc，不要手写文档

#### Repository实现规律
- **Repository接口**：定义在 `domain/{entity}/repository/` 目录下
- **JPA实现**：具体实现在 `domain/{entity}/repository/jpa/Jpa{Entity}Repository.java`
- **实现方式**：JPA实现类继承 `JpaRepository` 和 `ExtendedQuerydslPredicateExecutor`，同时实现领域Repository接口
- **查询实现**：使用QueryDSL进行复杂查询，通过 `fillQueryParameter` 方法构建查询条件
- **统计查询**：可以直接在JPA实现接口中添加default方法实现统计功能
- **命名规范**：JPA实现类命名为 `Jpa{Entity}Repository`，如 `JpaLmCollectionProductRepository`


### 一些通用类

#### ExtendHttpUtil
包装了一些 Http 方法的请求,路径是：module/core/src/main/java/ai/nezha/globex/core/util/ExtendHttpUtil.java
需要对接对外HTTP请求的,可以使用这个,如果方法不够,请在这里面增加方法

#### JsonUtil
包装了Json的序列化和反序列化的工具,路径是：module/core/src/main/java/ai/nezha/globex/core/util/JsonUtil.java
有需要Json序列化的请参考这个

#### Assert
断言工具，路径在：module/core/src/main/java/ai/nezha/globex/core/util/Assert.java

其他更多的通用工具类，都在这个路径底下：module/core/src/main/java/ai/nezha/globex/core/util


### 消息和事件

#### 消息和事件存储的位置
位置：module/shared/src/main/java/ai/nezha/globex/shared/event

在这包里面，简单的以模块作为区分，每个模块在event包名下都有自己的包名

#### 消息和事件的区别
消息的类名以Message 结尾，事件的类目以Event 结尾.
如果以异步的方式，发送消息的类为：module/core/src/main/java/ai/nezha/globex/core/mq/MQMessagePublisher.java
如果是同步的方式，发送事件的类为：module/core/src/main/java/ai/nezha/globex/core/event/EventPublisher.java

事件类 Event 固定继承 module/shared/src/main/java/ai/nezha/globex/shared/event/Event.java
消息类可继承的有：
事务消息： module/shared/src/main/java/ai/nezha/globex/shared/event/mq/TransactionMQMessage.java
普通消息：module/shared/src/main/java/ai/nezha/globex/shared/event/mq/MQMessage.java


