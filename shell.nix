{ pkgs ? import <nixpkgs> { config.allowUnfree = true; }
}:
pkgs.mkShell {
  name="Prescricao-Zero-environment";
  buildInputs = [
    pkgs.git                # Comandos básicos de git
    pkgs.gitflow            # Comandos com suporte ao git flow
    pkgs.micro              # Editor de texto de terminal
    pkgs.bat                # Similar ao cat mas com formatação visual
    pkgs.asciinema          # Permmite gravar sessões de terminal para demonstração
    pkgs.asciinema-agg      # Permite gerar GIF animado da sessão de terminal gravada com asciinema
    pkgs.asciinema-scenario # Permite criar demonstração asciinema por meio de script
    pkgs.jdk17              # OpenJDK 22
    pkgs.maven              # Maven
    pkgs.docker             # Docker
    pkgs.docker-compose     # Docker Compose
    pkgs.redli              # Redis CLI (Cache)
    pkgs.vscode             # IDE Visual Studio Code run "export NIXPKGS_ALLOW_UNFREE=1" to allow use of it
    pkgs.nodejs
  ];
  shellHook = ''
    git pull --all
     export JAVA_HOME=${pkgs.openjdk17}
    export PATH=${pkgs.openjdk17}/bin:$PATH

    code --install-extension bierner.markdown-preview-github-styles@2.1.0       # Para visualizar como HTML o código Markdown (CTRL+SHIFT+V)
    code --install-extension DavidAnson.vscode-markdownlint@0.56.0              # Para verificar a corretude do código Markdown
    code --install-extension PKief.material-icon-theme@5.12.0                    # Para estilizar extensões e pastas para facilitar a identificação
    code --install-extension xabikos.JavaScriptSnippets@1.8.0                   # Suporta Javascript e Typescript
    code --install-extension mhutchie.git-graph@1.30.0                          # Para exibir graficamente o histórico de commits
    code --install-extension christian-kohler.npm-intellisense@1.4.5            # Autocomplete para quando utilizar require
    code --install-extension dbaeumer.vscode-eslint@3.0.10                       # Verifica conformidade de código baseado no ESlint
    code --install-extension streetsidesoftware.code-spell-checker@3.0.1        # Verifica conformidade de nome de funções, variáveis
    code --install-extension formulahendry.auto-close-tag@0.5.15                # Fecha as tags XML, HTML automaticamente
    code --install-extension mikestead.dotenv@1.0.1                             # Facilita a edição de arquivos .env
    code --install-extension christian-kohler.path-intellisense@2.9.0           # Autocomplete para paths e names
    code --install-extension mitchdenny.ecdc@1.8.0                              # Encode Decode diversos formatos (Base64, JSON, ...)
    code --install-extension humao.rest-client@0.25.1                           # Realiza requisições HTTP direto da IDE
    code --install-extension aaron-bond.better-comments@3.0.2                   # Ajuda na criação de comentários mais amigáveis
    code --install-extension SonarSource.sonarlint-vscode@4.11.1                 # Verifica a qualidade do código produzido
    code --install-extension redhat.java@1.35.1                                 # Suporte para a linguagem Java
    code --install-extension timonwong.shellcheck@0.37.1                        # Linter, avalia código shell script
    code --install-extension Remisa.shellman@5.7.0                              # Autocomplete para código shell script
    code --install-extension vmware.vscode-spring-boot@1.58.0                   # Provê validação e assistente para Spring Boot
    code --install-extension vscjava.vscode-java-debug@0.58.0                   # Debugger para Java
    code --install-extension vscjava.vscode-spring-initializr@0.11.2            # Facilita a criação de projetos Spring Boot
    code --install-extension vscjava.vscode-spring-boot-dashboard@0.14.0        # Dashboard para Spring Boot
    code --install-extension mtxr.sqltools@0.28.3                               # Permite conectar e interagir com diversos bancos de dados
    code --install-extension mtxr.sqltools-driver-pg@0.5.4                      # Driver do banco Postgres
    code --install-extension jnoortheen.nix-ide@0.3.5                           # Syntax highlight for Nix
    code --install-extension PKief.material-icon-theme@5.11.1                    # Melhora os ícones do VSCode
    code --install-extension ms-azuretools.vscode-docker@1.29.3                 # Extensão Docker
    code --uninstall-extension ms-vscode-remote.remote-containers               # Remove a extensão Remote Container
    code --install-extension madhavd1.javadoc-tools@1.6.0                       # Auxilia na geração de JavaDoc
    code --install-extension streetsidesoftware.code-spell-checker-portuguese-brazilian@2.2.1 # Pacote de idiomas para o spell checker(cSpell)
    echo ""
    echo "Ambiente Prescrição Zero pronto para desenvolvimento!"
    echo "Caso não possua um editor de código de sua preferência, execute o comando code . na pasta do projeto"
  '';
}
