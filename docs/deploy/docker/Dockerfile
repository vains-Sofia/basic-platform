# 2024.10.18 使用文档中新的分层打包命令 RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
# 和COPY目录/builder/extracted/../  会造成认证服务自带端点(基于过滤器提供服务)404
# 分层的RUN命令和COPY命令是配合使用的，没有单独摘出来测试
FROM registry.cn-guangzhou.aliyuncs.com/vains/eclipse-temurin:21-jre AS builder
WORKDIR application
COPY . .
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
# RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
RUN java -Djarmode=layertools -jar application.jar extract

FROM registry.cn-guangzhou.aliyuncs.com/vains/eclipse-temurin:21-jre
WORKDIR application
# /builder/extracted/../ ./
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]