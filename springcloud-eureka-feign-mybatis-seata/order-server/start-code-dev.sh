#!/bin/bash
cd $(dirname $0)

img_mvn="maven:3.3.3-jdk-8"       # docker image of maven
m2_cache=~/.m2                    # the local maven cache dir
proj_home=$PWD                    # the project root dir
img_output="xiaomo/xaiomo.order.server" # output image tag

apollo_configService="http://192.168.0.218:8080"
app_id="xaiomo.order.server"
apollo_meta="http://192.168.0.218:8080"
apollo_bootstrap_enabled="true"
apollo_bootstrap_namespaces="application"
apollo_env="DEV"
git pull # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
  -v $m2_cache:/root/.m2 \
  -v $proj_home:/usr/src/mymaven \
  -w /usr/src/mymaven $img_mvn mvn clean install -U -Pdev -DskipTests
  #-w /usr/src/mymaven $img_mvn mvn clean package -U -Pdev -DskipTests

mv $proj_home/target/order-server-*.jar $proj_home/target/demo.jar # 兼容所有sh脚本
docker build -t $img_output .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

# 删除容器
docker rm -f xaiomo.order.server &>/dev/null

version=$(date "+%Y%m%d%H")

# 启动镜像
docker run -d --restart=on-failure:5 --privileged=true \
  -w /home \
  --net=host \
  -v $PWD/logs:/home/logs \
  --name xaiomo.order.server dd/xaiomo.order.server \
  java \
  -Djava.security.egd=file:/dev/./urandom \
  -Duser.timezone=Asia/Shanghai \
  -Denv=$apollo_env \
  -Dapollo.configService=$apollo_configService \
  -XX:+PrintGCDateStamps \
  -XX:+PrintGCTimeStamps \
  -XX:+PrintGCDetails \
  -XX:+HeapDumpOnOutOfMemoryError \
  -Xloggc:logs/gc_$version.log \
  -jar /home/demo.jar \
  --app.id=$app_id \
  --apollo.meta=$apollo_meta \
  --apollo.bootstrap.enabled=$apollo_bootstrap_enabled \
  --apollo.bootstrap.namespaces=$apollo_bootstrap_namespaces
