🍽️ Sistema de Gerenciamento de Funcionários para Restaurante
Este projeto em Java oferece uma solução completa para o gerenciamento de funcionários em um ambiente de restaurante, incluindo operações como controle de ponto, execução de tarefas específicas por cargo (Garçom, Cozinheiro, Gerente), além de integração com banco de dados e tratamento de exceções personalizadas.

## 📁 Estrutura do Projeto

```
└── src
    ├── br
    │   └── com
    │       └── funcionario
    |           ├──Connection
    |              ├── ConnectionSQL.java
    |              └── Integracao.java
    |           |
    │           ├── exception 
    │               └── PedidoInvalidoException.java
    |           |
    │           ├── main
    │               └── Main.java
    │           └── model
    │               ├── ControlePonto.java
    │               ├── Funcionario.java
    │               ├── InformacaoFuncionario.java
    │               └── Operacao.java
    └── module-info.java
```

📝 Descrição das Principais Classes
InformacaoFuncionario.java: Classe base que encapsula os dados essenciais de um funcionário, como nome, CPF, idade, cargo, salário, turno e data de admissão. Inclui métodos para cadastro e validação do cargo.

Funcionario.java: Extende InformacaoFuncionario e adiciona funcionalidades específicas, como a validação da existência de um funcionário no sistema (buscarFuncionario), além de sobrecarga de construtores para flexibilidade na criação de objetos.

Garcom.java, Cozinheiro.java, Gerente.java: Classes que representam os diferentes cargos no restaurante, cada uma com métodos específicos às suas funções, como anotarPedido() para o garçom, prepararPedido() para o cozinheiro e emitirRelatorioVendas() para o gerente.

ControlePonto.java: Gerencia o registro de entrada e saída dos funcionários, validando horários permitidos (entre 06:00 e 22:00) e calculando o tempo de serviço.

Operacao.java: Define operações específicas para cada cargo, garantindo que cada função execute apenas as operações pertinentes ao seu papel no restaurante.

ConnectionSQL.java: Responsável pela conexão com o banco de dados, utilizando JDBC para estabelecer e gerenciar a comunicação com o banco.

Integracao.java: Contém métodos que interagem com o banco de dados para realizar operações como inserção, atualização, deleção e consulta de dados dos funcionários.

🛠️ Funcionalidades Implementadas
Gerenciamento de Funcionários: Cadastro, atualização e remoção de funcionários no sistema, com integração direta ao banco de dados.

Controle de Ponto: Registro preciso dos horários de entrada e saída dos funcionários, com validação de horários permitidos e cálculo automático do tempo de serviço.

Operações Específicas por Cargo:

Garçom: Anotar e entregar pedidos.

Cozinheiro: Preparar pedidos e notificar quando prontos.

Gerente: Contratar funcionários, emitir relatórios de vendas e organizar escalas de trabalho.

Tratamento de Exceções Personalizadas: Implementação de exceções específicas, como PedidoInvalidoException, para garantir a integridade e a confiabilidade das operações do sistema.

Integração com Banco de Dados: Utilização de JDBC para conectar e operar diretamente no banco de dados, permitindo persistência e recuperação eficiente dos dados dos funcionários.

🚀 Como Executar o Projeto
Configuração do Banco de Dados:

Certifique-se de que o banco de dados está configurado corretamente e acessível.

Atualize as credenciais de acesso no arquivo ConnectionSQL.java conforme necessário.

Compilação e Execução:

Compile os arquivos Java utilizando seu ambiente de desenvolvimento preferido ou via linha de comando.

Execute a classe Main.java para iniciar a aplicação.

🧪 Testes
Testes Unitários: Foram implementados testes unitários para as principais funcionalidades do sistema, garantindo a confiabilidade e a robustez do código.

---

## ❗ Exceções Personalizadas

| Exceção                             | Quando é lançada |
|------------------------------------|------------------|
| `PedidoInvalidoException`          | Quando o pedido anotado é nulo ou vazio. |

---

## ▶️ Como Executar

1. Compile os arquivos:
```bash
javac br/com/funcionario/**/*.java

