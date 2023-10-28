package funcionario;

import java.util.*;
import funcionario.cargos.*;
import geradorId.GeradorId;

public class SistemaADM {
  //LISTA DE FUNCIONARIOS
  static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();


  //ADICIONAR FUNCIONARIO
  public static void addFuncionario(){
    System.out.println("[DIGITE O CPF: 11 digitos]");
    String cpfString = System.console().readLine();
    long cpfLong;
    if(cpfString.length() < 11 || cpfString.length() > 11 || !cpfString.matches("^[0-9]+$")){
      System.out.println("[ERRO] valor do CPF invalido");
      return;
    }else{
      cpfLong = Long.parseLong(cpfString);
    }

    System.out.println("[DIGITE O NOME]");
    String nome = System.console().readLine();


    System.out.println("[DIGITE O SEXO: 'm' ou 'f']");
    String sexoString = System.console().readLine();
    char sexoChar = ' ';
    if(sexoString.length() > 1 || sexoString.isBlank() || (!sexoString.equalsIgnoreCase("m") && !sexoString.equalsIgnoreCase("f"))){
      System.out.println("[ERRO] valor do sexo invalido");
    }else{
      sexoChar = sexoString.charAt(0);
    }

    System.out.println("[DIGITE A CIDADE]");
    String cidade = System.console().readLine();
    System.out.println("[DIGITE O BAIRRO]");
    String bairro = System.console().readLine();
    System.out.println("[DIGITE A RUA]");
    String rua = System.console().readLine();
    System.out.println("[DIGITE O NUMERO DA RESIDENCIA]");
    String numeroString = System.console().readLine();
    int numeroInt = Integer.parseInt(numeroString);


    System.out.println("[DIGITE O NUMERO DE TELEFONE: 8 digitos]");
    String telefoneString = System.console().readLine();
   
    if(!telefoneString.matches(".*\\d.*") || telefoneString.length() < 1 || telefoneString.length() > 8){
      System.out.println("[ERRO] valor de telefone invalido");
      return;
    }

    System.out.println("[DIGITE O NASCIMENTO: dd/mm/aaaa]");
    String nascimento = System.console().readLine();
    if (!nascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
      System.out.println("[ERRO] formato invalido de data");
      return;
    }

    System.out.println("+------------------------------------------------+");
    System.out.println("|              ESCOLHA O CARGO                   |");
    System.out.println("+------------------------------------------------+");
    System.out.println("|   [1 ACOUGUEIRO] - [2 AUXILIAR GERAL]          |");
    System.out.println("|   [3 AUXILIAR DE LIMPEZA] - [4 ESTOQUISTA]     |");
    System.out.println("|   [5 GERENTE] - [6 OPERADOR DE CAIXA]          |");
    System.out.println("|   [7 REPOSITOR] - [8 SEGURANCA]                |");
    System.out.println("+------------------------------------------------+");
    
    String cargoEscolhido;
    int cargoEscolhidoInt;

    try {
      System.out.println("[DIGITE O CARGO]");
      cargoEscolhido = System.console().readLine();
      cargoEscolhidoInt = Integer.parseInt(cargoEscolhido);
      
    } catch (NumberFormatException e) {
      System.out.println("[ERRO] Apenas numeros permitidos");   
      return;
    
    } catch (Exception e){
      e.printStackTrace();  
      return;
    }
    
    if(cargoEscolhidoInt <= 0 || cargoEscolhidoInt > 8){
      System.out.println("[ERRO] cargo nao existe.");
      
      return;
    }

    switch (cargoEscolhidoInt) {
      case 1:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new Acougueiro()));
        break;
    
      case 2:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new AuxiliarGeral()));
        break;
    
      case 3:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new AuxiliarLimpeza()));
        break;
    
      case 4:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new Estoquista()));
        break;
    
      case 5:
        System.out.println("[DIGITE A SENHA PARA O GERENTE ACESSAR O SISTEMA]");
        String senhaGerente = System.console().readLine();

        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new Gerente(senhaGerente)));
        break;
      case 6:
        System.out.println("[DIGITE A SENHA PARA O OPERADOR DE CAIXA]");
        String senhaCaixa = System.console().readLine();      
        
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new OperadorCaixa(senhaCaixa)));
        break;
    
      case 7:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new Repositor()));
        break;
    
      case 8:
        listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, new Seguranca()));
        break;
  
    }
    System.out.println("[NOVO FUNCIONARIO CADASTRADO COM SUCESSO!]");
  }


  //ATUALIZAR FUNCIONARIO
  public static void atualiazarFuncionario(){
    System.out.println("[DIGITE O CPF(11 digitos) DO FUNCIONARIO]");
    String cpfString = System.console().readLine();
    long cpfLong;
    if(cpfString.length() < 11 || cpfString.length() > 11 || !cpfString.matches("^[0-9]+$")){
      System.out.println("[ERRO] valor do CPF invalido");
      return;
    }else{
      cpfLong = Long.parseLong(cpfString);
    }
    
    Funcionario fAchado = null;
    for (Funcionario funcionario : listaFuncionarios) {
      if (funcionario.getCpf() == cpfLong) {
        fAchado = funcionario;
        break;
      }
    }

    if (fAchado == null) {
      System.out.println("[ERRO] funcionário não encontrado ou não existe");
      return;
    }  

    System.out.println("+--------------------------+");
    System.out.println("|  ATRIBUTOS E VALORES     |");
    System.out.println("+--------------------------+");
    System.out.println(" 1. CPF: " + fAchado.getCpf());
    System.out.println(" 2. Nome: " + fAchado.getNome());
    System.out.println(" 3. Sexo: " + fAchado.getSexo());
    System.out.println(" 4. Cidade: " + fAchado.getCidade());
    System.out.println(" 5. Bairro: " + fAchado.getBairro());
    System.out.println(" 6. Rua: " + fAchado.getRua());
    System.out.println(" 7. Numero da Residência: " + fAchado.getNumeroRua());
    System.out.println(" 8. Telefone: " + fAchado.getTelefone());
    System.out.println(" 9. Data de Nascimento: " + fAchado.getNascimento());
    System.out.println(" 10. Cargo: " + fAchado.getCargo().getNome());
    
    System.out.println("[ESCOLHA O ATRIBUTO QUE DESEJA MUDAR]");
    String opcao = System.console().readLine();
    
    switch (opcao) {
      case "1":
        System.out.println("[Digite o novo CPF(11 digitos)]");
        String novoCpf = System.console().readLine();
        fAchado.setCpf(Long.parseLong(novoCpf));
          break;

      case "2":
        System.out.println("[DIGITE O NOVO NOME]");
        String novoNome = System.console().readLine();
        fAchado.setNome(novoNome);
          break;

      case "3":
        System.out.println("[DIGITE O NOVO SEXO: m/f]");
        String novoSexo = System.console().readLine();
        if (novoSexo.equalsIgnoreCase("m") || novoSexo.equalsIgnoreCase("f")) {
          fAchado.setSexo(novoSexo.charAt(0));
        } else {
          System.out.println("[ERRO] Sexo inválido.");
        }
          break;

      case "4":
        System.out.println("[DIGITE A NOVA CIDADE]");
        String novaCidade = System.console().readLine();
        fAchado.setCidade(novaCidade);
          break;
      
      case "5":
        System.out.println("[DIGITE O NOVO BAIRRO]");
        String novoBairro = System.console().readLine();
        fAchado.setBairro(novoBairro);
          break;

        case "6":
          System.out.println("[DIGITE A NOVA RUA]");
          String novaRua = System.console().readLine();
          fAchado.setRua(novaRua);
            break;
       
        case "7":
          System.out.println("[DIGITE O NOVO NUMERO DE RESIDENCIA]");
          int novoNumero = Integer.parseInt(System.console().readLine());
          fAchado.setNumeroRua(novoNumero);
            break;
        
        case "8":
          System.out.println("[DIGITE O NOVO TELEFONE(8 digitos)]");
          String novoTelefone = System.console().readLine();
          fAchado.setTelefone(novoTelefone);
            break;
      
        case "9":
          System.out.println("[DIGITE A DATA DE NASCIMENTO: (dd/mm/aaaa)]");
          String novaDataNascimento = System.console().readLine();
          if (novaDataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            fAchado.setNascimento(novaDataNascimento);
          } else {
            System.out.println("[ERRO] Formato de data inválido.");
          }
            break;
        case "10":
        
          System.out.println("+------------------------------------------------+");
          System.out.println("|              ESCOLHA O CARGO                   |");
          System.out.println("+------------------------------------------------+");
          System.out.println("|   [1 ACOUGUEIRO] - [2 AUXILIAR GERAL]          |");
          System.out.println("|   [3 AUXILIAR DE LIMPEZA] - [4 ESTOQUISTA]     |");
          System.out.println("|   [5 GERENTE] - [6 OPERADOR DE CAIXA]          |");
          System.out.println("|   [7 REPOSITOR] - [8 SEGURANCA]                |");
          System.out.println("+------------------------------------------------+");
          System.out.println("[ESCOLHA O NOVO CARGO]");
      
          String cargoEscolhido;
          int cargoEscolhidoInt;

          try {
            System.out.println("[DIGITE O CARGO]");
            cargoEscolhido = System.console().readLine();
            cargoEscolhidoInt = Integer.parseInt(cargoEscolhido);
            
          } catch (NumberFormatException e) {
            System.out.println("[ERRO] Apenas numeros permitidos");   
            return;
          
          } catch (Exception e){
            e.printStackTrace();  
            return;
          }
          
          if(cargoEscolhidoInt <= 0 || cargoEscolhidoInt > 8){
            System.out.println("[ERRO] cargo nao existe.");
            
            return;
          }

          switch (cargoEscolhidoInt) {
            case 1:
              fAchado.setCargo(new Acougueiro());
              break;
          
            case 2:
              fAchado.setCargo(new AuxiliarGeral());
              break;
          
            case 3:
              fAchado.setCargo(new AuxiliarLimpeza());
              break;
          
            case 4:
              fAchado.setCargo(new Estoquista());
              break;
          
            case 5:
              System.out.println("[DIGITE A SENHA PARA O GERENTE ACESSAR O SISTEMA]");
              String senhaGerente = System.console().readLine();
              fAchado.setCargo(new Gerente(senhaGerente));
              break;

            case 6:
              System.out.println("[DIGITE A SENHA PARA O OPERADOR DE CAIXA]");
              String senhaCaixa = System.console().readLine();      
              fAchado.setCargo(new OperadorCaixa(senhaCaixa));
              break;
          
            case 7:
              fAchado.setCargo(new Repositor());
              break;
          
            case 8:
              fAchado.setCargo(new Seguranca());
              break;
        }
          break;
    }
    System.out.println("[FUNCIONÁRIO ATUALIZADO COM SUCESSO!]");
  }

  //LISTAR FUNCIONARIOS
  public static void listarTodosFuncionarios(){
    for (Funcionario funcionario : listaFuncionarios) {
      System.out.println(funcionario);
    }
  }

  //DELETAR FUNCIONARIO
  public static void deletarFuncionario(){
    System.out.println("[DIGITE O CPF(11 digitos) DO FUNCIONARIO QUE DESEJA DELETAR]");
    
    String cpfString;
    long cpfLong;
    
    try {
      cpfString = System.console().readLine();
      cpfLong = Long.parseLong(cpfString);
      
    } catch (Exception e) {
      System.out.println("[ERRO] funcionario não encontrado");
      return;
    }

    
    Funcionario funcionarioDemitido = null;
    for (Funcionario funcionario : listaFuncionarios) {
      if(funcionario.getCpf() == cpfLong){
        funcionarioDemitido = funcionario;
      }
    }
    
    if(funcionarioDemitido == null){
      System.out.println("[ERRO] funcionario nao encontrado");
      return;
    }

    listaFuncionarios.remove(funcionarioDemitido);
    System.out.println("[FUNCIONARIO DEMITIDO COM SUCESSO!]");
  }


  //INICIAR FUNCIONARIOS
  public static void iniciarFuncionarios(){
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 70970235588L, "Upper Chai", 'm', "recife", "varzea", "rodrigues paiva", 45, "40028922", "20/02/2000", new Gerente("main99")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 40240565892L, "Alan Moore", 'm', "recife", "caxanga", "caxito", 12, "98566658", "21/08/1988", new OperadorCaixa("sub55")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 56985235489L, "Nano Ledger", 'm', "recife", "mangueira", "braga neto", 124, "95666588", "11/01/1985", new OperadorCaixa("sub42")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 46213213589L, "Caio Lacraio", 'm', "recife", "mangueira", "braga neto", 100, "36548957", "09/09/1985", new OperadorCaixa("sub32")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 47546522288L, "Chang Silva", 'm', "recife", "sao lourenco", "xexeu de dentro", 333, "96964227", "05/09/1999", new OperadorCaixa("sub02")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 36548465415L, "Nicolau Copernico", 'm', "recife", "san martin", "agamenon", 19, "15687469", "24/07/1992", new OperadorCaixa("sub99")));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 36695541599L, "Mike Bison", 'm', "camaragibe", "nova descoberta", "sampaio correia", 259, "63658745", "24/11/1975", new Seguranca()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 89542652552L, "Arnaldo Schuaznega", 'm', "camaragibe", "itaipo", "general comodoro", 1010, "36895412", "20/12/1989", new Seguranca()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 36695541599L, "Silvestre Esta'Alone", 'm', "camaragibe", "boca do gume", "serra negra", 99, "25636984", "11/11/1963", new Seguranca()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 96635482871L, "Shira Tensei", 'f', "recife", "madalena", "cristovao colombo", 157, "25698426", "11/10/1999", new Repositor()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 96635482871L, "Sasa Nogeira", 'f', "recife", "rio doce", "capitao henriques", 86, "65368984", "10/11/1978", new Repositor()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 85695248228L, "Joao Cadelari", 'm', "recife", "casa forte", "estevao de sa", 15, "85891636", "01/07/1988", new Estoquista()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 96635482871L, "Marcia Jacob", 'f', "recife", "espinheiro", "flores rosas", 42, "26584169", "22/11/1999", new Estoquista()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 89655254861L, "Lisa Adams", 'f', "recife", "espinheiro", "mangueira formosa", 10, "85963214", "09/01/1982", new AuxiliarLimpeza()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 85695521511L, "Chloe Zaad", 'f', "recife", "aflitos", "bairro antigo", 111, "63258974", "14/12/1981", new AuxiliarLimpeza()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 85621426823L, "Milton Neto", 'm', "sao lourenco", "vila velha", "itarabem", 84, "84563652", "12/10/1998", new AuxiliarGeral()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 98562418752L, "Asafe Paiva", 'm', "sao lourenco", "renovo", "beija flor", 10, "25478961", "12/10/1950", new AuxiliarGeral()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 85693214732L, "Ben Mendes", 'm', "sao lourenco", "itaipu", "nova morada", 85, "65893214", "17/10/1966", new Acougueiro()));
    listaFuncionarios.add(new Funcionario(GeradorId.gerarId(), 38217392173L, "Sam Sulek", 'm', "sao lourenco", "itaipu", "nova morada", 87, "54618118", "28/02/2002", new Acougueiro()));
  
    Collections.sort(listaFuncionarios);
  } 
}