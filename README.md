# Sudoku Java

Jogo de Sudoku desenvolvido em Java com versões para terminal e interface gráfica utilizando Java Swing.

O projeto foi realizado como parte de um desafio prático da Digital Innovation One (DIO), com o objetivo de aplicar Programação Orientada a Objetos, organização em camadas, manipulação de coleções e validação de regras de negócio.

## Sobre o projeto

A aplicação representa um tabuleiro tradicional de Sudoku com 81 posições organizadas em nove setores 3x3.

O jogador deve preencher as células disponíveis com números de 1 a 9, sem repetir valores na mesma linha, coluna ou setor.

## Funcionalidades

- Execução pelo terminal ou por interface gráfica
- Preenchimento das células editáveis
- Proteção dos números iniciais do tabuleiro
- Verificação do estado atual da partida
- Identificação de inconsistências
- Reinicialização do jogo
- Validação da solução
- Mensagem de conclusão da partida
- Tela com instruções e regras do Sudoku

## Melhorias implementadas

Além das funcionalidades do projeto-base, esta versão recebeu melhorias próprias:

- Integração da versão gráfica à branch principal
- Criação do botão **Como jogar**
- Inclusão de instruções dentro da aplicação
- Mensagens de status mais claras
- Correção de textos apresentados ao usuário
- Identificação personalizada da janela
- Organização e padronização do código
- Remoção de arquivos locais da IDE
- Configuração de um `.gitignore` mais completo

## Conceitos aplicados

- Programação Orientada a Objetos
- Encapsulamento
- Composição de objetos
- Separação de responsabilidades
- Classes e enumerações
- Interfaces e eventos
- Expressões lambda
- Streams
- Coleções
- Java Swing
- Tratamento de regras de negócio

## Estrutura do projeto

| Pacote ou classe | Responsabilidade |
|---|---|
| `model` | Representa o tabuleiro, as posições e os estados do jogo |
| `service` | Controla as regras, eventos e notificações |
| `ui/custom/button` | Contém os botões personalizados |
| `ui/custom/input` | Controla a entrada dos números |
| `ui/custom/panel` | Monta o tabuleiro e seus setores |
| `ui/custom/frame` | Configura a janela da aplicação |
| `ui/custom/screen` | Organiza os componentes da interface |
| `util` | Mantém o modelo visual do tabuleiro no terminal |
| `Main.java` | Inicia a versão pelo terminal |
| `UIMain.java` | Inicia a versão com interface gráfica |

## Tecnologias

- Java 17 ou superior
- Java Swing
- Git
- GitHub

## Como executar

### Pré-requisitos

- Java JDK 17 ou superior
- IntelliJ IDEA, Eclipse ou outra IDE compatível com Java
- Git instalado, caso queira clonar o projeto

### Clonar o repositório

```bash
git clone https://github.com/talitagroberto/sudoku-java.git
```

Depois, acesse a pasta:

```bash
cd sudoku-java
```

### Configurar o tabuleiro

A aplicação recebe a configuração inicial pelas opções de execução da classe principal.

Cada posição segue o formato:

```text
coluna,linha;valorEsperado,numeroFixo
```

Exemplo:

```text
0,0;4,false
```

Nesse exemplo:

- `0,0` representa a coluna e a linha;
- `4` é o valor correto da posição;
- `false` indica que a célula pode ser editada.

A configuração completa deve conter as 81 posições do tabuleiro.

### Executar a interface gráfica

Na IDE:

1. Abra a configuração de execução.
2. Selecione `br.com.dio.UIMain` como classe principal.
3. Adicione a configuração completa do tabuleiro em **Program arguments**.
4. Execute a aplicação.

Para utilizar a versão pelo terminal, execute a classe `br.com.dio.Main` com os mesmos argumentos.

## Regras

Para concluir corretamente:

- Cada linha deve conter números de 1 a 9 sem repetição.
- Cada coluna deve conter números de 1 a 9 sem repetição.
- Cada setor 3x3 deve conter números de 1 a 9 sem repetição.
- Os números iniciais não podem ser alterados.

## Autoria

Desenvolvido por **Talita Gonçalves** durante a formação Java da Digital Innovation One.

Projeto baseado no [repositório oficial do desafio](https://github.com/digitalinnovationone/sudoku), com melhorias de interface, código e documentação.