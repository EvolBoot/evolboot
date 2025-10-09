## 生成archetype 时注意

0. 先 install mvn clean compile package install -Dmaven.test.skip=true -f pom.xml


1. 在确认原型完成之后，执行 mvn archetype:create-from-project ，会在根目录生成一个
   `./target/generated-sources/archetype `

mvn archetype:create-from-project -Darchetype.properties=archetype.properties

2. 删除Idea特有的 leaven.iml 和 .idea文件

进入到  `./target/generated-sources/archetype/src/main/resources/archetype-resources/` 中， id 删除 `leaven.iml`
和 `.idea`

然后修改`./target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml`

将其中的`leaven.iml` 和 和 `.idea`  节点删除

**删除参考如下信息(如果不删除也可以)**

  ```
  <include>leaven.iml</include>
  
  <fileSet encoding="UTF-8">
          <directory>.idea</directory>
          <includes>
            <include>**/*.name</include>
          </includes>
    </fileSet>
        
    <directory>.idea/libraries</directory>
    
     <fileSet filtered="true" encoding="UTF-8">
          <directory>.idea/libraries</directory>
          <includes>
            <include>**/*.xml</include>
          </includes>
        </fileSet>
        <fileSet filtered="true" encoding="UTF-8">
          <directory>.idea/artifacts</directory>
          <includes>
            <include>**/*.xml</include>
          </includes>
        </fileSet>
        <fileSet filtered="true" encoding="UTF-8">
          <directory>.idea</directory>
          <includes>
            <include>**/*.xml</include>
          </includes>
        </fileSet>
        <fileSet filtered="true" encoding="UTF-8">
          <directory>.idea/copyright</directory>
          <includes>
            <include>**/*.xml</include>
          </includes>
        </fileSet>
  ```

3. 修改  `./target/generated-sources/archetype/pom.xml` ，加入部署相关

```

  <!-- 部署相关 ，直接加在 project 根节点下-->
    <distributionManagement>
        <repository>
            <id>harme-nexus-releases</id>
            <url>http://maven.harme.cn/repository/maven-releases/</url>
        </repository>
        <snapshotRepository>
            <id>harme-nexus-snapshots</id>
            <url>http://maven.harme.cn/repository/maven-snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
```

4. 在`./target/generated-sources/archetype` 目录下 执行部署 `mvn deploy`

当显示 `BUILD SUCCESS` 的时候，可在对应的仓库中查询。

5. 生成方式

其他开发人员如果需要使用本构建，需要在settings.xml 设置仓库为此部署地址，示例：

```
  <profiles>
    <profile>
      <id>harme-maven-profile</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>harme-maven-public</id>
          <name>harme-maven-public</name>
          <url>http://maven.harme.cn/repository/maven-public/</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <releases>
            <enabled>true</enabled>
          </releases>
        </repository>
      </repositories>
    </profile>
```

然后使用以下生成，其中的包名`-DgroupId=cc.iqr.qili.web `和构建名称`-DartifactId=qili-web`可以替换。

```

mvn archetype:generate -DgroupId=cc.iqr.qili.web.qili-web -DartifactId=qili-web  -DarchetypeArtifactId=leaven-archetype -DinteractiveMode=false -DarchetypeGroupId=com.github.cn/released-order.archetype -DarchetypeVersion=1.0-SNAPSHOT

```

时间有点久，目前还不知道是什么情况，后续解决时间问题。

PS: 以上部分给archetype 开发人员

## 本项目基于archetype 自动生成，可根据项目自由删改

## 本项目数据库可基于 liquibase

如果使用`liquibase` 作为数据库集成管理可以使用

```mvn clean clean -P local,execDB```

和

``` mvn clean compile -P local,execDB ```

来清除和更新数据库 如果不使用可选择性移除
mvn archetype:generate \
-DgroupId=com.godhapy \
-Dpackage=com.godhapy \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \ll
-DarchetypeVersion=0.0.1

mvn archetype:generate \
-DgroupId=org.fs.noai \
-Dpackage=org.fs.noai \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1

mvn archetype:generate \
-DgroupId=org.xxx.mjb \
-Dpackage=org.xxx.mjb \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1

mvn archetype:generate \
-DgroupId=ai.nezha \
-Dpackage=ai.nezha \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1

mvn archetype:generate \
-DgroupId=com.github.vce \
-Dpackage=com.github.vce \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1



mvn archetype:generate \
-DgroupId=ai.nezha.erp \
-Dpackage=ai.nezha.erp \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1

mvn archetype:generate \
-DgroupId=ai.nezha.cms \
-Dpackage=ai.nezha.cms \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=0.0.1


mvn archetype:generate \
-DgroupId=ai.nezha.stargate \
-Dpackage=ai.nezha.stargate \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1


mvn archetype:generate \
-DgroupId=ai.nezha.tiaojia \
-Dpackage=ai.nezha.tiaojia \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1


mvn archetype:generate \
-DgroupId=ai.nezha.dianyu \
-Dpackage=ai.nezha.dianyu \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1



mvn archetype:generate \
-DgroupId=ai.nezha.ali1688 \
-Dpackage=ai.nezha.ali1688 \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1


mvn archetype:generate \
-DgroupId=com.godhapy.api \
-Dpackage=com.godhapy.api \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1


mvn archetype:generate \
-DgroupId=ai.novel \
-Dpackage=ai.novel \
-DartifactId=parent \
-Dversion=0.0.1 \
-DarchetypeArtifactId=parent-archetype \
-DinteractiveMode=false \
-DarchetypeGroupId=org.evolboot \
-DarchetypeVersion=eb-0.0.1