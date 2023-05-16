入口服务 默认分为4个 entry-admin 用来提供 Admin API 服务 entry-app 用来提供APP API服务 entry-schedule 用来跑定时任务
entry-test 用来测试集成

不同的服务，集成的模块（pom.xml) 可能不一样，也可能一样，然后EntryXXXApplication里面，可以用注解排除一些

```java
@ComponentScan(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {AdminClient.class})})
```