# Using 'from' to declare whitch jdk im  using
# In this case, my project is running on Java 1.8 (8)
# Therefore im refering to 'openjdk' and setting the version to '8'
FROM openjdk:8

# Using 'add' to locate the .jar file from the maven-build
# After i have given the pom.xml file a 'finalName' i can give the path to the dockerfile
ADD  target/docker-spring-boot.jar docker-spring-boot.jar

# Using 'expose' to inform the docker what port it shold listen to
EXPOSE 8080

# Using 'entrypoint' to run the application
# The first value is a command, and the last two are parameters
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]