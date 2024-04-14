# Use a imagem Maven para compilar a aplicação
FROM maven:3.8.4-jdk-11 AS builder

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o pom.xml e baixe as dependências Maven
COPY pom.xml .

# Copie o código-fonte
COPY src/ ./src/

# Limpar o cache do Maven e compilar a aplicação
RUN mvn clean package -DskipTests --fail-never \
    --batch-mode \
    --define http.timeout=900

# Use a imagem OpenJDK 11 para executar a aplicação
FROM openjdk:17

WORKDIR /event

# Copie o arquivo JAR gerado pelo Maven a partir do contexto de construção do Docker
COPY target/event-backend.jar ./

# Adicionar permissões ao diretório de banco de dados
RUN mkdir /banco && chmod g+w,o+w /banco

# Comando CMD para executar a aplicação Spring Boot
CMD ["java", "-jar", "event-backend.jar"]

# Expor a porta em que a aplicação Spring Boot irá escutar
EXPOSE 8080
