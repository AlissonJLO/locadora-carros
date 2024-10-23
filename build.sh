#!/bin/bash

# Etapa 1: Compilar o projeto localmente
echo "Building the Maven project locally..."
mvn clean package -DskipTests

# Verificar se o build foi bem sucedido
if [ $? -eq 0 ]; then
    echo "Build successful, preparing to create Docker image..."

    # Etapa 2: Subir o contêiner do Docker usando o JAR gerado
    docker-compose up --build
else
    echo "Build failed. Check errors."
fi
