FROM openjdk:8-jdk-alpine
ADD /target/core-*.jar /
EXPOSE 8080
ENTRYPOINT java -Dspring.profiles.active=${SPA}
    -Dnetworkaddress.cache.ttl=0 -jar core-*.jar

