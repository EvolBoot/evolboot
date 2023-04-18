# evolboot 

# 使用maven `mvn archetype:generate` 生成后需要替换的

1. config 配置的报名 全局替换包名：org.evolboot

2. 数据库前缀替换 全局替换: evoltb_

3. 全局替换项目前缀 全局替换： /evol/

4. 全局替换 evolpn

5. 全局替换 项目骨架

6. 复制一个.gitgnore

7. 需要配置一个oss （静态服务器），然后更改静态yml 配置

8. 替换 pom.xml 下空格，并格式化回来

9. 全局搜索 evol ，看看哪里还需要替换的

10. 修改 code-generator 下生成代码的配置

11. 修改配置文件 evol,evol-app

12. 修改 readme.md

13. 如果需要使用用户邀请机制,首次运行记得执行【用户上下级关系重构】接口
