# ONLY used for testing

FROM openjdk:8-alpine

ADD target/exam-exec.jar .


CMD java -jar exam-exec.jar --spring.datasource.username="postgres"   --spring.datasource.password  --spring.datasource.url="jdbc:postgresql://postgresql-host:5432/postgres"




