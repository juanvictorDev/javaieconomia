package funcionario;

import java.util.*;
import funcionario.cargos.*;
import geradorId.GeradorId;
import java.io.*;
import java.sql.*;

public class SistemaADM {
  //LISTA DE FUNCIONARIOS
  static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();


  //ADICIONAR FUNCIONARIO
  public static void addFuncionario() throws Exception {

    //CONEXAO COM O BANCO E STATEMENT 
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement statement = connection.prepareStatement("insert into funcionario values (?,?,?,?,?,?,?,?,?,?,?)");
    
    //INSTACIAR OBJETOS PARA SERIALIZACAO 
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);

    //FLUXO PADRAO DO METODO
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
        
        String id = GeradorId.gerarId();
        Acougueiro cargo = new Acougueiro();
      
        objOutStream.writeObject(cargo);
        byte[] objSerializado = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo));
        
        statement.setString(1, id);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado);

        statement.executeUpdate();

        break;
    
      case 2:
        
        String id2 = GeradorId.gerarId();
        AuxiliarGeral cargo2 = new AuxiliarGeral();
      
        objOutStream.writeObject(cargo2);
        byte[] objSerializado2 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id2, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo2));
        
        statement.setString(1, id2);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado2);

        statement.executeUpdate();

        break;
    
      case 3:

        String id3 = GeradorId.gerarId();
        AuxiliarLimpeza cargo3 = new AuxiliarLimpeza();
      
        objOutStream.writeObject(cargo3);
        byte[] objSerializado3 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id3, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo3));
        
        statement.setString(1, id3);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado3);

        statement.executeUpdate();
        
        break;
    
      case 4:

        String id4 = GeradorId.gerarId();
        Estoquista cargo4 = new Estoquista();
      
        objOutStream.writeObject(cargo4);
        byte[] objSerializado4 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id4, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo4));
        
        statement.setString(1, id4);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado4);

        statement.executeUpdate();

        break;
    
      case 5:
        System.out.println("[DIGITE A SENHA PARA O GERENTE ACESSAR O SISTEMA]");
        String senhaGerente = System.console().readLine();
      
        String id5 = GeradorId.gerarId();
        Gerente cargo5 = new Gerente(senhaGerente);
      
        objOutStream.writeObject(cargo5);
        byte[] objSerializado5 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id5, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo5));
        
        statement.setString(1, id5);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado5);

        statement.executeUpdate();

        break;

      case 6:
        System.out.println("[DIGITE A SENHA PARA O OPERADOR DE CAIXA]");
        String senhaCaixa = System.console().readLine();      
        
        String id6 = GeradorId.gerarId();
        OperadorCaixa cargo6 = new OperadorCaixa(senhaCaixa);
      
        objOutStream.writeObject(cargo6);
        byte[] objSerializado6 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id6, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo6));
        
        statement.setString(1, id6);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado6);

        statement.executeUpdate();
        
        break;
    
      case 7:

        String id7 = GeradorId.gerarId();
        Repositor cargo7 = new Repositor();
      
        objOutStream.writeObject(cargo7);
        byte[] objSerializado7 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id7, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo7));
        
        statement.setString(1, id7);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado7);

        statement.executeUpdate();
        
        break;
    
      case 8:

        String id8 = GeradorId.gerarId();
        Seguranca cargo8 = new Seguranca();
      
        objOutStream.writeObject(cargo8);
        byte[] objSerializado8 = byteOutStream.toByteArray();

        listaFuncionarios.add(new Funcionario(id8, cpfLong, nome, sexoChar, cidade, bairro, rua, numeroInt, telefoneString, nascimento, cargo8));
        
        statement.setString(1, id8);
        statement.setLong(2, cpfLong);
        statement.setString(3, nome);
        statement.setString(4, Character.toString(sexoChar));
        statement.setString(5, cidade);
        statement.setString(6, bairro);
        statement.setString(7, rua);
        statement.setInt(8, numeroInt);
        statement.setString(9, telefoneString);
        statement.setString(10, nascimento);
        statement.setBytes(11, objSerializado8);

        statement.executeUpdate();
        
        break;
    }

    System.out.println("[NOVO FUNCIONARIO CADASTRADO COM SUCESSO!]");
    
    statement.close();
    connection.close();
    objOutStream.close();
    byteOutStream.close();
  }



  //ATUALIZAR FUNCIONARIO
  public static void atualizarFuncionario() throws SQLException, IOException, ClassNotFoundException{
    System.out.println("[DIGITE O CPF(11 digitos) DO FUNCIONARIO]");
    String cpfString = System.console().readLine();
    long cpfLong;
    if(cpfString.length() < 11 || cpfString.length() > 11 || !cpfString.matches("^[0-9]+$")){
      System.out.println("[ERRO] valor do CPF invalido");
      return;
    }else{
      cpfLong = Long.parseLong(cpfString);
    }

    /* 
     Funcionario fAchado = null;
     for (Funcionario funcionario : listaFuncionarios) {
       if (funcionario.getCpf() == cpfLong) {
         fAchado = funcionario;
         break;
        }
      }
      */

    //VERIFICAR SE O FUNCIONARIO EXISTE NO BANCO
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement verificarStatement = connection.prepareStatement("select * from funcionario where cpf = ?");
    
    verificarStatement.setLong(1, cpfLong);
    ResultSet verificarRS = verificarStatement.executeQuery();
    
    if(verificarRS.next() == false){
      System.out.println("[ERRO] funcionário não encontrado ou não existe");
      return; 
    }
    
    verificarStatement.close();

    //EXIBINDO DADOS DIRETO DO BANCO 
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("select * from funcionario where cpf = '" + cpfLong + "'");

    System.out.println("+--------------------------+");
    System.out.println("|  ATRIBUTOS E VALORES     |");
    System.out.println("+--------------------------+");
    System.out.println(" 1. CPF: " + rs.getLong(2));
    System.out.println(" 2. Nome: " + rs.getString(3));
    System.out.println(" 3. Sexo: " + rs.getString(4));
    System.out.println(" 4. Cidade: " + rs.getString(5));
    System.out.println(" 5. Bairro: " + rs.getString(6));
    System.out.println(" 6. Rua: " + rs.getString(7));
    System.out.println(" 7. Numero da Residência: " + rs.getInt(8));
    System.out.println(" 8. Telefone: " + rs.getString(9));
    System.out.println(" 9. Data de Nascimento: " + rs.getString(10));
  
    //DESSERIALIZACAO DO TIPO BLOB DO BANCO QUE ARMAZENHA O OBJETO CARGO
    ByteArrayInputStream byteArray = new ByteArrayInputStream(rs.getBytes(11));
    ObjectInputStream objInput = new ObjectInputStream(byteArray);
    Cargo cargo = (Cargo) objInput.readObject();

    System.out.println(" 10. Cargo: " + cargo.getNome());
    
    byteArray.close();
    objInput.close();
    statement.close();


    System.out.println("[ESCOLHA O ATRIBUTO QUE DESEJA MUDAR]");
    String opcao = System.console().readLine();
    int opcaoInt = Integer.parseInt(opcao);
    
    if(opcaoInt > 10 || opcaoInt <= 0){
      System.out.println("[ERRO] VALOR INVALIDO");
      return;
    }

    //CRIANDO UM NOVO STATEMENT PARA ATUALIZAR OS DADOS
  
    switch (opcao) {
      case "1":
      System.out.println("[Digite o novo CPF(11 digitos)]");
      String novoCpf = System.console().readLine();
      
      PreparedStatement preparedStatement = connection.prepareStatement("update funcionario set cpf = ? where cpf = ?");
      preparedStatement.setLong(1, Long.parseLong(novoCpf));
      preparedStatement.setLong(2, cpfLong);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      
      //fAchado.setCpf(Long.parseLong(novoCpf));
      
        break;

      case "2":
        System.out.println("[DIGITE O NOVO NOME]");
        String novoNome = System.console().readLine();
        
        PreparedStatement preparedStatement2 = connection.prepareStatement("update funcionario set nome = ? where cpf = ?");
        preparedStatement2.setString(1, novoNome);
        preparedStatement2.setLong(2, cpfLong);
        preparedStatement2.executeUpdate();
        preparedStatement2.close();

        //fAchado.setNome(novoNome);
             
          break;

      case "3":
        System.out.println("[DIGITE O NOVO SEXO: m/f]");
        String novoSexo = System.console().readLine();
        if (novoSexo.equalsIgnoreCase("m") || novoSexo.equalsIgnoreCase("f")) {
          
          PreparedStatement preparedStatement3 = connection.prepareStatement("update funcionario set sexo = ? where cpf = ?");
          preparedStatement3.setString(1, novoSexo);
          preparedStatement3.setLong(2, cpfLong);
          preparedStatement3.executeUpdate();
          preparedStatement3.close();
          
          
          //fAchado.setSexo(novoSexo.charAt(0));


        } else {
          System.out.println("[ERRO] Sexo inválido.");
        }
          break;

      case "4":
        System.out.println("[DIGITE A NOVA CIDADE]");
        String novaCidade = System.console().readLine();
        
        PreparedStatement preparedStatement4 = connection.prepareStatement("update funcionario set cidade = ? where cpf = ?");
        preparedStatement4.setString(1, novaCidade);
        preparedStatement4.setLong(2, cpfLong);
        preparedStatement4.executeUpdate();
        preparedStatement4.close();
        
        //fAchado.setCidade(novaCidade);

          break;
      
      case "5":
        System.out.println("[DIGITE O NOVO BAIRRO]");
        String novoBairro = System.console().readLine();
        
        PreparedStatement preparedStatement5 = connection.prepareStatement("update funcionario set bairro = ? where cpf = ?");
        preparedStatement5.setString(1, novoBairro);
        preparedStatement5.setLong(2, cpfLong);
        preparedStatement5.executeUpdate();
        preparedStatement5.close();

        //fAchado.setBairro(novoBairro);

          break;

        case "6":
          System.out.println("[DIGITE A NOVA RUA]");
          String novaRua = System.console().readLine();
          
          PreparedStatement preparedStatement6 = connection.prepareStatement("update funcionario set rua = ? where cpf = ?");
          preparedStatement6.setString(1, novaRua);
          preparedStatement6.setLong(2, cpfLong);
          preparedStatement6.executeUpdate();
          preparedStatement6.close();
          
          //fAchado.setRua(novaRua);

            break;
       
        case "7":
          System.out.println("[DIGITE O NOVO NUMERO DE RESIDENCIA]");
          int novoNumero = Integer.parseInt(System.console().readLine());
          
          PreparedStatement preparedStatement7 = connection.prepareStatement("update funcionario set numero_rua = ? where cpf = ?");
          preparedStatement7.setInt(1, novoNumero);
          preparedStatement7.setLong(2, cpfLong);
          preparedStatement7.executeUpdate();
          preparedStatement7.close();
          
          //fAchado.setNumeroRua(novoNumero);
          
            break;
        
        case "8":
          System.out.println("[DIGITE O NOVO TELEFONE(8 digitos)]");
          String novoTelefone = System.console().readLine();
          
          PreparedStatement preparedStatement8 = connection.prepareStatement("update funcionario set telefone = ? where cpf = ?");
          preparedStatement8.setString(1, novoTelefone);
          preparedStatement8.setLong(2, cpfLong);
          preparedStatement8.executeUpdate();
          preparedStatement8.close();
          
          //fAchado.setTelefone(novoTelefone);
          
            break;
      
        case "9":
          System.out.println("[DIGITE A DATA DE NASCIMENTO: (dd/mm/aaaa)]");
          String novaDataNascimento = System.console().readLine();
          if (novaDataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            
            PreparedStatement preparedStatement9 = connection.prepareStatement("update funcionario set nascimento = ? where cpf = ?");
            preparedStatement9.setString(1, novaDataNascimento);
            preparedStatement9.setLong(2, cpfLong);
            preparedStatement9.executeUpdate();
            preparedStatement9.close();
            
            //fAchado.setNascimento(novaDataNascimento);

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

          PreparedStatement preparedStatement10 = connection.prepareStatement("update funcionario set cargo = ? where cpf = ?");
          ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
          ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);

          switch (cargoEscolhidoInt) {
            case 1:
              Acougueiro novoCargo = new Acougueiro();
              
              objOutStream.writeObject(novoCargo);
              byte[] objByte = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();
          
              //fAchado.setCargo(novoCargo);
              break;
          
            case 2:
              AuxiliarGeral novoCargo2 = new AuxiliarGeral();

              objOutStream.writeObject(novoCargo2);
              byte[] objByte2 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte2);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();

              
              //fAchado.setCargo(novoCargo2);

              break;
          
            case 3:
              AuxiliarLimpeza novoCargo3 = new AuxiliarLimpeza();

              objOutStream.writeObject(novoCargo3);
              byte[] objByte3 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte3);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();
              
              //fAchado.setCargo(novoCargo3);

              break;
          
            case 4:
              Estoquista novoCargo4 = new Estoquista();

              objOutStream.writeObject(novoCargo4);
              byte[] objByte4 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte4);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();
              
              //fAchado.setCargo(novoCargo4);

              break;
          
            case 5:
              System.out.println("[DIGITE A SENHA PARA O GERENTE ACESSAR O SISTEMA]");
              String senhaGerente = System.console().readLine();

              Gerente novoCargo5 = new Gerente(senhaGerente);

              objOutStream.writeObject(novoCargo5);
              byte[] objByte5 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte5);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();

              //fAchado.setCargo(novoCargo5);

              break;

            case 6:
              System.out.println("[DIGITE A SENHA PARA O OPERADOR DE CAIXA]");
              String senhaCaixa = System.console().readLine();
              
              OperadorCaixa novoCargo6 = new OperadorCaixa(senhaCaixa);

              objOutStream.writeObject(novoCargo6);
              byte[] objByte6 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte6);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();
              
              //fAchado.setCargo(novoCargo6);

              break;
          
            case 7:
              Repositor novoCargo7 = new Repositor();

              objOutStream.writeObject(novoCargo7);
              byte[] objByte7 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte7);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();

              //fAchado.setCargo(novoCargo7);

              break;
          
            case 8:
              Seguranca novoCargo8 = new Seguranca();

              objOutStream.writeObject(novoCargo8);
              byte[] objByte8 = byteOutStream.toByteArray();

              preparedStatement10.setBytes(1, objByte8);
              preparedStatement10.setLong(2, cpfLong);
              preparedStatement10.executeUpdate();

              //fAchado.setCargo(novoCargo8);

              break;
          }
          preparedStatement10.close();

        break;
    }
    System.out.println("[FUNCIONÁRIO ATUALIZADO COM SUCESSO!]");
  }


  //LISTAR FUNCIONARIOS
  public static void listarTodosFuncionarios() throws SQLException{
    
    //VALOR VINDO DA LIST DE FUNCIONARIOS
    /* 
    for (Funcionario funcionario : listaFuncionarios) {
      System.out.println(funcionario);
    }
    */
    
    //CONEXAO COM O BANCO, STATEMENT E EXIBICAO DOS DADOS DIRETO DO BANCO 
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("select * from funcionario");
    
    while(rs.next()){
      String id = rs.getString(1);
      long cpf = rs.getLong(2);
      String nome = rs.getString(3);
      String sexo = rs.getString(4);
      String cidade = rs.getString(5);
      String bairro = rs.getString(6);
      String rua = rs.getString(7);
      int numeroRua = rs.getInt(8);
      String telefone = rs.getString(9);
      String nascimento = rs.getString(10);
    
      System.out.printf("[ID: %s, CPF: %d, NOME: %s, SEXO: %s, CIDADE: %s, BAIRRO: %s, RUA: %s, NUMERO: %d, TELEFONE: %s, NASCIMENTO: %s]\n\n",id,cpf,nome,sexo,cidade,bairro,rua,numeroRua,telefone,nascimento);
    }
  }

  //DELETAR FUNCIONARIO
  public static void deletarFuncionario() throws SQLException{

    //EXLUINDO FUNCIONARIO DA LISTA INTERNA
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
    
    //EXCLUINDO FUNCIONARIO DO BANCO DE DADOS
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    Statement statement = connection.createStatement();
    int result = statement.executeUpdate("delete from funcionario where cpf = '" + cpfLong + "'");
    
    if(result == 0){
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