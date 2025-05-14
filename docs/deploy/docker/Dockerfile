# 分层构建镜像
FROM registry.cn-guangzhou.aliyuncs.com/vains/eclipse-temurin:21-jre AS builder
WORKDIR application
COPY . .
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
# RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM registry.cn-guangzhou.aliyuncs.com/vains/eclipse-temurin:21-jre
WORKDIR application
# /builder/extracted/../ ./
COPY --from=builder /application/extracted/dependencies/ ./
COPY --from=builder /application/extracted/spring-boot-loader/ ./
COPY --from=builder /application/extracted/snapshot-dependencies/ ./
COPY --from=builder /application/extracted/application/ ./

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动应用
ENTRYPOINT ["java", "-jar", "application.jar"]