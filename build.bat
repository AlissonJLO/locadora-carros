@echo off

REM Etapa 1: Compilar o projeto localmente
echo Building the Maven project locally...
mvn clean package -DskipTests

REM Verificar se o build foi bem sucedido
IF %ERRORLEVEL% EQU 0 (
    echo Build successful, preparing to create Docker image...

    REM Etapa 2: Subir o contêiner do Docker usando o JAR gerado
    docker-compose up --build
) ELSE (
    echo Build failed. Check errors.
)
