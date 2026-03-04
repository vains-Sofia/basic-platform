FROM registry.cn-guangzhou.aliyuncs.com/github-proxy/maven:3.9.12-ibm-semeru-25-noble AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 分层构建镜像
FROM registry.cn-guangzhou.aliyuncs.com/github-proxy/bellsoft-liberica-openjre-debian:25-cds AS builder
WORKDIR /builder
COPY . .
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} application.jar
# RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM registry.cn-guangzhou.aliyuncs.com/github-proxy/bellsoft-liberica-openjre-debian:25-cds
WORKDIR application
# /builder/extracted/../ ./
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动应用
ENTRYPOINT ["java", "-jar", "application.jar"]