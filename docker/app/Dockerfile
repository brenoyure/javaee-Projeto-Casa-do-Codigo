FROM quay.io/wildfly/wildfly:latest-jdk17

WORKDIR /opt/jboss/wildfly

COPY mysql-connector-j-8.0.33.jar ./modules/mysql/

ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_DATABASE
ARG MYSQL_DATABASE_HOST

COPY config.cli ./bin/
COPY casadocodigo.war ./standalone/deployments/

RUN ["/opt/jboss/wildfly/bin/jboss-cli.sh", "--file=/opt/jboss/wildfly/bin/config.cli", "--resolve-parameter-values"]

CMD [ "/opt/jboss/wildfly/bin/standalone.sh", "--server-config=standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0" ]
