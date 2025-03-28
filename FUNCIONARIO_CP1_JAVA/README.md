# 🍽️ Sistema de Funcionários - Restaurante

Este projeto em Java simula o gerenciamento de funcionários de um restaurante, com foco em operações como controle de ponto, execução de tarefas por cargo (Garçom, Cozinheiro, Gerente) e tratamento de erros com exceções personalizadas.

## 📁 Estrutura do Projeto

```
└── src
    ├── br
    │   └── com
    │       └── funcionario
    │           ├── exception 
    │           └── PedidoInvalidoException.java
    |           |
    │           ├── main
    │           │   └── Main.java
    │           └── model
    │               ├── ControlePonto.java
    │               ├── Funcionario.java
    │               ├── InformacaoFuncionario.java
    │               └── Operacao.java
    └── module-info.java
```

## 🧠 Descrição das Principais Classes

### 📌 `InformacaoFuncionario.java`
Classe base com os dados do funcionário:
- Nome, CPF, Idade, Cargo, Salário, Turno, Data de Admissão.
- Métodos para cadastrar e validar o cargo.

### 📌 `Funcionario.java`
Extende `InformacaoFuncionario` e adiciona:
- Validação de existência de funcionário (`buscarFuncionario`).
- Sobrecarga de construtores.

### 📌 `ControlePonto.java`
Classe responsável pelo controle de jornada:
- Registro de entrada e saída com horário.
- Validação de horário permitido (`06:00 às 22:00`).
- Cálculo de tempo de serviço.

### 📌 `Operacao.java`
Simula as operações específicas de cada cargo:
- **Garçom**: `anotarPedido()`, `entregarPedido()`.
- **Cozinheiro**: `prepararPedido()`, `notificarPedidoPronto()`.
- **Gerente**: `contratarFuncionario()`, `emitirRelatorioVendas()`, `organizarescala()`.

Inclui também validações como:
- Pedido inválido (campo vazio)
- Notificação vazia

---

## ❗ Exceções Personalizadas

| Exceção                             | Quando é lançada |
|------------------------------------|------------------|
| `CargoInvalidoException`           | Quando o cargo informado não é "Garçom", "Cozinheiro" ou "Gerente". |
| `FuncionarioNaoEncontradoException`| Quando o funcionário buscado não é encontrado. |
| `HorarioInvalidoException`         | Quando o funcionário tenta bater ponto fora do horário permitido. |
| `PedidoInvalidoException`          | Quando o pedido anotado é nulo ou vazio. |
| `NotificacaoPedidoInvalido`        | Quando a notificação enviada ao cliente está vazia. |

---

## ▶️ Como Executar

1. Compile os arquivos:
```bash
javac br/com/funcionario/**/*.java

