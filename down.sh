#!/bin/bash

# Verifica se o Docker Compose está instalado
if ! command -v docker-compose &>/dev/null; then
  echo "Docker Compose não está instalado. Instale o Docker Compose e tente novamente."
  exit 1
fi

echo "Parando e removendo os contêineres..."

# Parar e remover contêineres
docker-compose down

# Mensagem de sucesso
echo "Contêineres parados e removidos com sucesso."
