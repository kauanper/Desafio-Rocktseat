# 🚀 Projeto Spring Boot com Docker Merging Configuration

Este projeto consiste em uma API desenvolvida com **Spring Boot** e uma infraestrutura conteinerizada utilizando **Docker**. A arquitetura foi pensada para ser escalável e segura, separando as responsabilidades de ambiente.

## 🛠️ Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.x**
* **PostgreSQL** (Banco de dados relacional)
* **pgAdmin 4** (Interface de gerenciamento do banco)
* **Docker & Docker Compose**

---

## 🧩 Estratégia de Infraestrutura: Merging Configuration

Para a gestão dos containers, apliquei a técnica de **Merging Configuration** (Mesclagem de Configurações). Em vez de um único arquivo massivo, a configuração é dividida em camadas:

1.  **`docker-compose.yml` (Base):** Define o núcleo da infra (PostgreSQL, volumes e variáveis essenciais).
2.  **`docker-compose.override.yml` (Dev):** Camada de desenvolvimento que expõe portas para o `localhost` e adiciona o **pgAdmin** para debug.

Essa técnica permite que o ambiente seja montado "empilhando" os arquivos conforme a necessidade, garantindo que configurações de desenvolvimento (como ferramentas de interface) não vazem para ambientes de produção.

---

## ⚙️ Como Executar o Projeto

### 1. Requisitos
* Docker instalado.
* Arquivo `.env` configurado na raiz (veja o `.env.example`).

### 2. Comandos Principais

Os comandos abaixo utilizam a mesclagem dos arquivos para subir o ambiente completo de desenvolvimento:

**🚀Os comando estão respectivamente: Up, Build, Stop, down**
```powershell
docker compose --env-file .env -f docker/docker-compose.yml -f docker/docker-compose.override.yml up -d

docker compose --env-file .env -f docker/docker-compose.yml -f docker/docker-compose.override.yml up -d --build

docker compose -f docker/docker-compose.yml -f docker/docker-compose.override.yml stop

docker compose -f docker/docker-compose.yml -f docker/docker-compose.override.yml down -v

```
