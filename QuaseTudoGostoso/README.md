# QTG-Servidor
Projeto Gerenciador de Receitas com API REST simples.

API REST simples
-----------------

Foi adicionada uma API REST simples usando `com.sun.net.httpserver.HttpServer`.

Endpoints:

- GET /receitas -> lista todas as receitas (JSON)
- POST /receitas -> cria uma receita. Corpo JSON esperado:

  {
    # QTG-Servidor
    Projeto: Gerenciador de Receitas com API REST simples

    Visão geral
    -----------

    Este projeto expõe uma API REST mínima para gerenciar receitas usando a implementação embutida `com.sun.net.httpserver.HttpServer` do JDK.

    Principais endpoints
    --------------------

    - GET /receitas
      - Lista todas as receitas (JSON array).
    - POST /receitas
      - Cria uma nova receita. Corpo JSON esperado:

        {
          "nome":"Bolo",
          "ingredientes":[{"nome":"Farinha","quantidade":"2 xícaras"}],
          "modoPreparo":[{"etapa":"Misturar"},{"etapa":"Assar"}]
        }

    - GET /receitas/{id}
      - Retorna a receita pelo índice (id é o índice dentro da lista em memória, começando em 0).
    - DELETE /receitas/{id}
      - Remove a receita pelo índice.
    - GET /ingredientes
      - Lista todos os ingredientes de todas as receitas cadastradas (flat list).
    - GET /modos
      - Lista todas as etapas de preparo de todas as receitas (flat list).

    Notas importantes
    -----------------

    - Armazenamento: os dados são mantidos em memória (`GerenciadorDeReceitas`). Ao reiniciar o servidor, tudo é perdido.
    - JSON: o projeto usa um parser/serializador simples caseiro (`JsonUtil.java`). Para uso em produção, recomendo integrar uma biblioteca como Gson ou Jackson.
    - Identificadores: os "ids" expostos nas rotas são índices na lista (0..N-1). Posso mudar para UUIDs ou persistência se desejar.

    Como compilar e executar
    ------------------------

    1. Compile todas as classes:

    ```bash
    javac *.java
    ```

    2. Execute o servidor (porta 8089 por padrão):

    ```bash
    java ApiServer
    ```

    Executando em background (macOS/linux):

    ```bash
    nohup java ApiServer > server.log 2>&1 &
    ```

    Parar o servidor:

    ```bash
    ps aux | grep 'ApiServer' | grep -v grep
    kill <PID>
    ```

    Exemplos de uso (curl)
    ----------------------

    Listar receitas (inicialmente vazio):

    ```bash
    curl -s http://localhost:8089/receitas
    ```

    Adicionar uma receita:

    ```bash
    curl -X POST -H "Content-Type: application/json" \
      -d '{"nome":"Test","ingredientes":[{"nome":"Ovo","quantidade":"2"}],"modoPreparo":[{"etapa":"Bater"}]}' \
      http://localhost:8089/receitas
    ```

    Consultar receita por id (ex.: id 0):

    ```bash
    curl -s http://localhost:8089/receitas/0
    ```

    Remover receita por id (ex.: id 0):

    ```bash
    curl -X DELETE http://localhost:8089/receitas/0
    ```

    Listar todos ingredientes de todas as receitas:

    ```bash
    curl -s http://localhost:8089/ingredientes
    ```

    Listar todas as etapas de preparo de todas as receitas:

    ```bash
    curl -s http://localhost:8089/modos
    ```

    Próximos passos recomendados (opcionais)
    ---------------------------------------

    - Persistência em arquivo JSON ou banco de dados para manter receitas entre reinícios.
    - Substituir o parser caseiro por Gson/Jackson.
    - Adicionar endpoints de atualização (PUT /receitas/{id}) e paginação.

    Licença / Notas finais
    ----------------------

    Projeto simples para teste e prototipação. 
    Feito Por Rafael Ricetti Para a turma de ADS 2025
