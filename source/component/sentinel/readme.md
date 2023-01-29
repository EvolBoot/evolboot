sentinel 限流 使用时，需要配置：

```yaml
spring:
  cloud:
    sentinel:
      enabled: true
      eager: true
      transport:
        port: 8719
        dashboard: 127.0.0.1:8080
      filter:
        url-patterns: /**

```

dashboard 是另外起一个服务，控制面板

port 是sentinel 自身的端口，用来和 dashboard沟通的。