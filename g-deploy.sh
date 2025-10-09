#!/bin/sh
mvn clean compile package install -Dmaven.test.skip=true -f pom.xml -T 1C
echo "流程:mvn clean compile package install -T 1C -Dmaven.test.skip=true "
mvn archetype:create-from-project -Darchetype.properties=archetype.properties
echo "流程:mvn archetype:create-from-project -Darchetype.properties=archetype.properties"
cd ./target/generated-sources/archetype
mvn install
echo "流程:mvn install"
echo "结束:SUCCESS"