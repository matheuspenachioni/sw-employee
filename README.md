# JWT Auth
Aqui foi feita apenas uma API com a entidade (Employee) e na versão 3.0.5 do Spring

<hr>

Caso queira ver em funcionamento, após rodar o código em sua IDE, siga o tutorial abaixo:

Utilizando o Postman
1. Acesse a url ``http://localhost:8080/login`` (POST)
```json
{
  "emailEmployee": "matheus@mail.com",
  "passwordEmployee": "123"
}
```
2. Copie o token que irá vir se a requisição for bem-sucedida
```
Exemplo: 
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXRoZXVzQG1haWwuY29tIiwiaXNzIjoiQXV0aFVzZXIiLCJpZCI6MSwiZXhwIjoxNjgxODgwNjA4fQ.DcyyeJQ7eIP6wgNaLlaNi4vg7R0_iBtB2x4V3hTXm8c
```
3. Testando algum endpoint </br>
3.1. Acesse a url ``http://localhost:8080/employee/list`` (GET) </br>
3.2. Em ``Authorization`` mude o ``Type`` de ``Inherit auth from parent`` para ``Bearer Token`` e cole o token gerado em ``Token``

<hr>

**OBS**: A senha e e-mail acabaram saindo repetidas na listagem por causa do DTO, irei estudar para arrumar isso...
