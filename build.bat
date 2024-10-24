@echo off

REM Função para compilar a aplicação localmente com Maven
echo.
echo =============================================================================
echo                          BUILDING APPLICATION
echo =============================================================================
echo.
docker-compose up --build
