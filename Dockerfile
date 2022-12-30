# 基础镜像信息
FROM maven:3.8-openjdk-8
# 设置工作目录
WORKDIR /workspace
RUN mvn clean package -DskipTests
# 创建目录
# RUN mkdir -p /app
# 拷贝文件
# ADD ./target /app
# 运行文件
# RUN ls ./target
RUN java -jar /workspace/target/video-server-0.0.1-SNAPSHOT.jar
# CMD ["java", "-jar", "/workspace/target/movie-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8088