FROM maven:3.9-ibm-semeru-21-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM registry.cn-guangzhou.aliyuncs.com/github-proxy/eclipse-temurin:21-jre AS builder
WORKDIR /builder

COPY --from=build /app/target/*.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM registry.cn-guangzhou.aliyuncs.com/github-proxy/eclipse-temurin:21-jre
WORKDIR /application

# 拷贝 Spring Boot 分层
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ARG BUILD_PROJECT=unknown
ARG BUILD_VERSION=0.0.1
ARG BUILD_COMMIT=unknown
ARG BUILD_TIME=unknown

# 元信息
LABEL maintainer="vains-Sofia <17683906991@163.com>" \
      org.opencontainers.image.title="${BUILD_PROJECT}" \
      org.opencontainers.image.version="${BUILD_VERSION}" \
      org.opencontainers.image.revision="${BUILD_COMMIT}" \
      org.opencontainers.image.created="${BUILD_TIME}"

ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 10000