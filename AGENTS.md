# Repository Guidelines

## 项目结构与模块组织
- Maven 多模块根在 `module/`；核心模块：`core`、`shared`、`security`、`schedule`、`identity`、`system`、`bff`、`pay`、`im`；入口：`entry-admin`、`entry-app`、`entry-schedule`、`entry-test`。
- 代码与资源：`src/main/java` 与 `src/main/resources`（Mapper XML 在 `mybatis/`，i18n 在 `messages*.properties`）；测试放 `src/test/java`。
- 配置集中：`module/config-resource/src/main/resources`（profiles: `dev/evol/prod`）；静态资源 `static/`，文档 `docs/`。
- 技术栈：Java 17，Spring Boot 3.1.5，Spring Cloud 2023.0.0，Spring Cloud Alibaba 2023.0.1.0。

## 构建、测试与本地运行
- 全量构建：`mvn -T 1C clean install`；仅打包：`mvn clean package -DskipTests`。
- 单模块：`mvn -pl module/bff -am package`。
- 运行（dev）：`mvn -pl module/entry/entry-app -am spring-boot:run -Dspring-boot.run.profiles=dev`；或 `java -jar module/entry/entry-admin/target/entry-admin.jar --spring.profiles.active=dev`。
- 测试：`mvn test`；集成测试：`mvn -pl module/entry/entry-test test`；单测：`mvn -Dtest=RoleRepositoryTest -pl module/entry/entry-test test`。
- API 文档：`/swagger-ui.html` 与 `/v3/api-docs`（SpringDoc 自动生成）。
- 端口：默认在配置资源中管理（dev 示例为 `8009`），如需调整请在对应 profile 下修改 `server.port`。

## 代码风格与命名
- Java 17 / UTF-8 / 4 空格；使用 Lombok，建议行宽 ≤120。
- DDD 分层：`domain`（聚合根/领域服务）、`app`（`AppService`/`QueryService`）、`remote`（REST，类名 `*ResourceV1`）、`repository`、`dto`（`*Request`/`*Response`）、`mapper`（XML 于 `resources/mybatis`）。
- 边界约束：禁止跨领域直接访问 Repository 或修改他域对象状态；跨域协作通过公开服务或查询服务。
- 数据访问：优先 JPA/QueryDSL；复杂查询可使用 MyBatis Plus；BFF 支持跨表组装只读查询。
- Repository 位置：接口置于 `domain/.../repository/`，实现按技术栈（JPA/MyBatis）在模块内维护；REST 控制器放 `remote/`。

## 测试规范
- 框架：JUnit 5 + Spring Boot Test；优先覆盖安全、权限与核心领域逻辑。
- 约定：测试类以 `*Test`/`*Tests` 结尾；集成测试优先放 `entry-test`。
- 断言与工具：可结合 AssertJ；必要时使用内存数据库或测试容器隔离环境。

## 提交与 PR
- 提交遵循 Conventional Commits（示例：`feat(bff): 导出权限树`）；历史包含 `feat`/`fix`/`refactor` 等。
- PR 包含：变更说明、影响范围、关联 Issue（如 `Closes #123`）、运行/验证步骤与截图/日志；确保 `mvn -q -DskipTests package` 与 `mvn test` 通过。

## 数据与配置
- 数据迁移：Flyway，放 `src/main/resources/db/migration`，命名 `V{version}__description.sql`；版本参考 `docs/migration-version.md`。
- 消息队列：默认支持 Redis Stream（`mq-redis-consumer`）；亦提供 RocketMQ/Kafka 模块，可按需启用。
- API 文档：SpringDoc（`/swagger-ui.html` 与 `/v3/api-docs`）；勿提交敏感凭据（使用本地 profiles）。
- QueryDSL：通过 `apt-maven-plugin` 生成至 `target/generated-sources/java`；IDE 需启用注解处理。
- 国际化：新增功能需提供中英文消息（`messages.properties`、`messages_zh_CN.properties`）。

## 新增模块与迁移流程
- 新模块：在 `module/` 下创建目录与 `pom.xml`（继承父项），补充标准分层；在 `module/pom.xml` 与根 `pom.xml` 中登记模块/依赖。
- 入口集成：按需在 `entry-*` 中加入模块依赖并暴露 `remote` 接口；跨域协作通过 `AppService/QueryService`。
- 数据迁移：为新表/变更创建 `db/migration/V{version}__description.sql`，本地验证通过后提交 PR。
