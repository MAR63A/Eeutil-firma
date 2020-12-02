FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/josefsabater/eeutil-shared.git
RUN git clone https://github.com/MAR63A/Eeutil-firma.git

FROM maven:3.6.3-jdk-8
WORKDIR /app
COPY --from=0 /app /app
WORKDIR /app/eeutil-shared
RUN mvn install
WORKDIR /app/Eeutil-firma
RUN mvn install

FROM tomcat:8.5-jdk8
ENV JAVA_OPTS="-Djavax.net.ssl.trustStore=/usr/local/tomcat/conf/eeutil/truststore.jks \
-Djavax.net.ssl.trustStorePassword=changeit \
-Dlocal_home_app=/usr/local/tomcat/conf/eeutil \
-Deeutil-firma.config.path=/usr/local/tomcat/conf/eeutil"
WORKDIR /app
COPY --from=1 /app/Eeutil-firma/fuentes/eeutil-firma/target/eeutil-firma.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]