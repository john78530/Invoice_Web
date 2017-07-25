FROM java:8

COPY target/Invoice_web-1.0-SNAPSHOT-jar-with-dependencies.jar /home/Invoice_web-1.0-SNAPSHOT-jar-with-dependencies.jar

CMD ["java", "-jar", "/home/Invoice_web-1.0-SNAPSHOT-jar-with-dependencies.jar"]