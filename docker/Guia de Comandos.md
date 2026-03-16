
Este guia centraliza os comandos para gerenciar o ambiente de desenvolvimento usando a estrutura de pastas `docker/` e o arquivo `.env`.

---

## 🛠️ Ambiente de Desenvolvimento (Base + Override)
*Estes comandos gerenciam o **Banco de Dados** e o **pgAdmin** simultaneamente.*

## 🧩 Estratégia de Configuração: Merging Configuration

Neste projeto, utilizamos a técnica de **Merging Configuration** (Mesclagem de Configurações) do Docker Compose. 
### Como funciona?
Em vez de termos um arquivo gigante e repetitivo, dividimos a infraestrutura em camadas lógicas que se sobrepõem:

1.  **`docker-compose.yml` (Base):** Contém a definição essencial dos serviços (imagens, volumes e redes) que são comuns a qualquer ambiente.
2.  **`docker-compose.override.yml` (Desenvolvimento):** Adiciona camadas exclusivas para o dia a dia do desenvolvedor, como a exposição de portas para o host local e ferramentas auxiliares (ex: pgAdmin).

### Composição de Ambientes
A grande vantagem dessa técnica é a granularidade. Você "constrói" o ambiente que deseja apenas listando os arquivos na ordem de precedência:

* **Apenas o essencial:** `docker-compose.yml`
* **Ambiente de Dev completo:** `docker-compose.yml` + `docker-compose.override.yml`
* **Ambiente de Staging (Futuro):** `docker-compose.yml` + `docker-compose.staging.yml`

O Docker lê esses arquivos em sequência e mescla as configurações, onde o último arquivo da lista sempre tem prioridade sobre os anteriores.

### 🚀 Subir Tudo
Une os arquivos de configuração, injeta as variáveis do `.env` e sobe os serviços em segundo plano.
```
docker compose --env-file .env -f docker/docker-compose.yml -f docker/docker-compose.override.yml up -d
```

### 🏗️ Reconstruir (Build)

Força o Docker a ler alterações nos arquivos de configuração ou recriar as imagens.

```
docker compose --env-file .env -f docker/docker-compose.yml -f docker/docker-compose.override.yml up -d --build
```

### 🛑 Parar (Stop)

Apenas desliga os containers. Eles continuam existindo, mas param de consumir CPU/RAM.

```
docker compose -f docker/docker-compose.yml -f docker/docker-compose.override.yml stop
```

### 🗑️ Derrubar (Down)

Remove os containers e as redes internas. Os dados do banco **são preservados**.

```
docker compose -f docker/docker-compose.yml -f docker/docker-compose.override.yml down
```

### 🧨 Resetar Tudo (Limpar Volumes)

Remove containers e **APAGA OS DADOS** do banco de dados (deleta o volume `pgdata`).

```
docker compose -f docker/docker-compose.yml -f docker/docker-compose.override.yml down -v
```

## 🔍 Diagnóstico e Monitoramento

### Status dos Containers

```
docker ps
```

### Ver Logs do Banco de Dados

```
docker logs -f postgres-desafio
```


# 🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳🐳