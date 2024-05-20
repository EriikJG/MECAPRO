FROM tomcat:9.0.8-jre8-alpine

ARG WAR_FILE=target/MECAPRO-1.0-SNAPSHOT.war

# Copiar tu archivo WAR a la carpeta webapps de Tomcat
COPY $WAR_FILE /usr/local/tomcat/webapps/

# Exponer el puerto en el que Tomcat está escuchando
EXPOSE 8080

# Comando para iniciar Tomcat cuando se inicie el contenedor
CMD ["catalina.sh", "run"]
