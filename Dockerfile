FROM openjdk:11
ADD target/spring-boot-project.jar spring-boot-project.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "spring-boot-project.jar"]