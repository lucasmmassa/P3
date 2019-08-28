# INSTRUCOES DE USO  
O funcionamento desse programa é bastante intuitivo, basta seguir as instrucoes que aparecem na tela.  
Caso haja duvida, bsta checar o documento que explica o funcionamento do projeto OO no github, pois  
o funcionamento é o mesmo, com uma excecao.  
A unica diferenca nesse caso se da na entrada do menu principal. Anteriormente as intrucoes no menu  
eram dadas atraves de numeros. Devido ao refatoramento com o padrao command, essa entrada agora é uma STRING.  
  
!!!!!!RESSALTANDO: A ENTRADA DO MENU PRINCIPAL É UMA STRING.  
Exemplo: caso queira adicionar um empregado, basta digita 'Add'. As strings de cada operacao sao mostradas na tela.  
                    
                      
# MODIFICAÇÕES REALIZADAS NO REFATORAMENTO:  

## TRATAMENTO DE CÓDIGO DUPLICADO:     
Em algumas classes, havia bstante código repetido.  
Com isso, foram criados metodos que sao chamados nas ocasioes onde antes estava o codigo repetido.  

##TRATAMENTO DE CLASSE DESNECESSARIAMENTE GRANDE:  
Anteriormente havia uma unica classe que lidava com todas as operacoes, chamada de EmployessManager.  
Ela foi dividida em varias classes de tamanaho menor, tendo agora uma classe manager para cada operacao.  
Essas classes fazem alteracoes no Array List que armazena os empregados na classe EmployeesData por meio  
de acesso package. Com isso temos melhor legibilidade e entendimento sobre o codigo.  

## FORAM APLICADOS OS SEGUINTES PADRÕES DE PROJETO:  
  
### 1 - COMMAND:  
Foi utilizado no refatoramento do switch do menu principal, na classe Main.  
Os componentes do padrao sao implementados pelas seguintes classes:
- Client: Main. (package main)  
- Invoker: Actions. (package main)  
- Interface: Main Command. (package main)  
- Concrete Commands: Add, Agenda, Close, Edit, Exit, Payroll, Redo,   
Remove, Sale, Service, Show, Timecard, Undo. (packge main)  
- Receivers: todas as classes Manager do package manager.  

### 2 - PROTOTYPE:  
Foi utilizado para criar clones dos funcionarios para serem adicionados nas
pilhas de undo e redo.  
Os componentes do padrao sao implementados pelas seguintes classes:  
- Client: EmployessData. (package manager)
- Interface: Prototype. (package employess)
- Concrete Prototypes: Commissioned, Hourly, Salaried. (package employess)

### 3 - STRATEGY:  
Foi utilizado para manejar as operações de pagamento, onde cada tipo de
agenda possui um tratamento diferente.  
Os componentes do padrao sao implementados pelas seguintes classes:  
- Client: PaymentManager. (package manager)
- Context: Context. (package manager)
- Interface: PaymentStrategy. (package manager)
- Concrete Strategies: MonthlyPayment, TwoWeekPayment, WeekPayment (package manager)

### 4 - TEMPLATE METHOD:  
Foi utilizado para melhor organizar os construtores das subclasses de Employee.  
A classe Employee é abstrata e possui um template method que serve para coletar
e atrubuir as informacoes comuns às subclasses na construcao dos objetos.  Apos isso
o metodo chama um abstrato que serve para coletar e atribuir as informacoes especificas
de cada subclasse.  
Os componentes do padrao sao implementados pelas seguintes classes:  
- Template Method: getConstructionInfo(). (classe Employee package employees)

### 5 - SINGLETON:  
Foi utilizado na classe EmployeesData, a qual armazena o Array List de empregados e  
as pilhas de undo e redo, para garantir que a mesma tenha uma unica instancia em todo o programa.  
Os componentes do padrao sao implementados pelas seguintes classes:  
- getInstance method: getSingleInstance(). (classe EmployeesData package manager)
