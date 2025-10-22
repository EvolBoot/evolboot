# EvolBoot

<div align="center">

一个基于 Spring Boot 3.x 和领域驱动设计（DDD）的现代化企业级多租户微服务框架

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

</div>

## 📖 简介

EvolBoot 是一个企业级的多租户微服务开发框架，采用领域驱动设计（DDD）和充血模型架构，旨在帮助开发者快速构建高质量、可维护的业务应用。

### ✨ 核心特性

- 🏗️ **领域驱动设计（DDD）**：采用充血模型，聚合根、领域服务、应用服务清晰分层
- 🏢 **多租户架构**：完善的多租户权限体系，支持平台、租户、用户三级权限管理
- 🔧 **模块化设计**：业务模块高内聚低耦合，支持独立部署和扩展
- 🚀 **开箱即用**：集成常用组件（存储、短信、支付、定时任务等）
- 📊 **数据库迁移管理**：使用 Flyway 进行版本化数据库管理
- 🔐 **安全认证**：支持 Token 和 API Key 双认证机制
- 📝 **API 文档自动化**：集成 SpringDoc，自动生成 OpenAPI 3.0 文档

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 编程语言 |
| Spring Boot | 3.1.5 | 基础框架 |
| Spring Cloud | 2023.0.0 | 微服务框架 |
| Spring Cloud Alibaba | 2023.0.1.0 | 微服务组件 |
| JPA/Hibernate | - | ORM 框架 |
| MyBatis Plus | - | 数据访问增强 |
| QueryDSL | - | 类型安全的查询 |
| Redis | - | 缓存 |
| Redisson | - | 分布式锁 |
| Flyway | - | 数据库迁移 |
| PowerJob | - | 分布式任务调度 |
| SpringDoc | - | API 文档生成 |

## 📦 项目结构

```
evolboot/
├── docs/                      # 文档
│   ├── code-generator/        # 代码生成器文档
│   ├── error-code.md          # 错误码范围说明
│   └── migration-version.md   # 数据库迁移版本管理
│
├── module/                    # 核心模块
│   ├── core/                  # 技术基础组件（无业务逻辑）
│   ├── shared/                # 业务共享模块（纯 POJO）
│   ├── config-resource/       # 配置文件集中管理
│   │
│   ├── entry/                 # 应用入口
│   │   ├── entry-admin/       # 后台管理 API
│   │   ├── entry-app/         # 用户/商户 API
│   │   ├── entry-schedule/    # 定时任务服务
│   │   └── entry-test/        # 测试入口
│   │
│   ├── component/             # 通用组件
│   │   ├── apidoc/            # API 文档
│   │   ├── captcha/           # 验证码
│   │   ├── code-generator/    # 代码生成器
│   │   ├── config/            # 动态配置
│   │   ├── mq/                # 消息队列
│   │   ├── sentinel/          # 限流熔断
│   │   ├── sms/               # 短信服务
│   │   ├── storage/           # 存储服务
│   │   └── websocket/         # WebSocket
│   │
│   ├── identity/              # 身份认证与权限
│   ├── security/              # 安全框架
│   │   ├── security-api/      # 权限校验
│   │   └── security-token/    # Token 认证
│   ├── system/                # 系统管理
│   ├── pay/                   # 支付网关
│   ├── schedule/              # 定时任务
│   └── bff/                   # 面向前端的后端
│
└── static/                    # 静态资源
```

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 本地运行

1. **克隆项目**

```bash
git clone https://github.com/EvolBoot/evolboot.git
cd evolboot
```

2. **配置数据库**

修改配置文件 `module/config-resource/src/main/resources/application-dev.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/evolboot?useUnicode=true&characterEncoding=utf-8
    username: your_username
    password: your_password
  redis:
    host: localhost
    port: 6379
```

3. **编译项目**

```bash
mvn clean compile
```

4. **运行应用**

```bash
# 运行后台管理 API
cd module/entry/entry-admin
mvn spring-boot:run

# 或运行用户 API
cd module/entry/entry-app
mvn spring-boot:run --spring.profiles.active=dev
```

5. **访问 API 文档**

启动后访问：`http://localhost:8080/swagger-ui.html`

## 🏗️ 架构设计

### DDD 分层架构

EvolBoot 采用领域驱动设计（DDD），主要分为以下几层：

```
┌─────────────────────────────────────┐
│     Controller/Resource/Remote      │  对外接口层
├─────────────────────────────────────┤
│         Application Service         │  应用服务层（协调、技术关注点）
├─────────────────────────────────────┤
│          Domain Service             │  领域服务层（核心业务逻辑）
├─────────────────────────────────────┤
│         Aggregate Root              │  聚合根（充血模型）
├─────────────────────────────────────┤
│    Repository/Dao (JPA/MyBatis)     │  数据持久化层
└─────────────────────────────────────┘
```

### 核心原则

#### 1. 领域边界隔离
- ❌ 禁止跨聚合直接调用 Repository
- ❌ 禁止跨聚合直接调用领域服务
- ✅ 跨聚合写操作：调用目标聚合的 `AppService`
- ✅ 跨聚合读操作：调用目标聚合的 `QueryService`

#### 2. 充血模型
聚合根维护自身状态完整性：

```java
// ❌ 贫血模型
public void deductBalance(Account account, BigDecimal amount) {
    if (account.getBalance().compareTo(amount) < 0) {
        throw new InsufficientBalanceException();
    }
    account.setBalance(account.getBalance().subtract(amount));
}

// ✅ 充血模型
public class Account {
    private BigDecimal balance;

    public void subtract(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = balance.subtract(amount);
    }
}
```

#### 3. 事件驱动解耦

使用事件解耦领域间协作：

```java
// 发布事件
eventPublisher.publish(new BalanceChangedEvent(userId, oldBalance, newBalance));

// 订阅事件
@EventListener
public void onBalanceChanged(BalanceChangedEvent event) {
    // 记录日志、发送通知等
}
```

## 📚 开发指南

### 新增业务模块

1. 在 `module/` 下创建模块目录
2. 创建标准 DDD 分层结构
3. 在父 `pom.xml` 添加依赖管理
4. 更新相关 entry 模块依赖

### 数据库迁移

使用 Flyway 管理数据库版本：

```sql
-- 命名格式：V{version}__description.sql
-- 位置：module/*/src/main/resources/db/migration/
-- 示例：V1.1.0__add_table_user.sql
```

版本号规范：参考 `docs/migration-version.md`

### Repository 实现规范

- **接口定义**：`domain/{entity}/repository/`
- **JPA 实现**：`domain/{entity}/repository/jpa/Jpa{Entity}Repository.java`
- **继承关系**：JpaRepository + ExtendedQuerydslPredicateExecutor + 领域 Repository 接口

### 消息与事件

#### 事件（同步）
- 位置：`module/shared/src/main/java/org/evolboot/shared/event/`
- 命名：`*Event.java`
- 发送：`EventPublisher.publish(event)`

#### 消息（异步）
- 位置：`module/shared/src/main/java/org/evolboot/shared/event/`
- 命名：`*Message.java`
- 发送：`MQMessagePublisher.publish(message)`

## 🤝 贡献指南

欢迎贡献代码！请遵循以下规范：

1. **代码风格**
   - 遵循 DDD 开发规范
   - 保持代码简洁，优先复用
   - 避免硬编码，提取常量

2. **提交规范**
   ```
   feat: 新增功能
   fix: 修复 bug
   refactor: 重构代码
   docs: 文档更新
   test: 测试相关
   chore: 构建/工具链变更
   ```

3. **Pull Request**
   - 确保测试通过
   - 更新相关文档
   - 一个 PR 只做一件事

## 📄 许可证

本项目采用 [MIT License](LICENSE) 许可证。

## 📧 联系方式

- 问题反馈：[GitHub Issues](https://github.com/EvolBoot/evolboot/issues)
- 邮箱：evolboot@proton.me

---

<div align="center">

**[文档](docs/) | [更新日志](CHANGELOG.md) | [问题反馈](https://github.com/EvolBoot/evolboot/issues)**

Made with ❤️ by EvolBoot Team

</div>
