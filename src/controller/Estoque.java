package controller;

import produto.Produto;
import produto.Setor;
import produto.produtos.*;
import java.util.*;
import java.util.regex.Pattern;
import geradorId.*;
import java.sql.*;

public class Estoque {
  
  //QUANTIDADE POR TIPO DE PRODUTO
  public static int bebidaQnt;
  public static int carneQnt;
  public static int cerealQnt;
  public static int condimentoQnt;
  public static int doceQnt;
  public static int enlatadoQnt;
  public static int frutaQnt;
  public static int higienePessoalQnt;
  public static int laticinioQnt;
  public static int legumeQnt;
  public static int limpezaQnt;
  public static int massaQnt;
  public static int vegetalQnt;
  
  //ESTOQUE GERAL
  static public ArrayList<Produto> estoqueGeral = new ArrayList<>();
  

  //VIZUALIZAR ESTOQUE GERAL
  static public void visualizarGeral() throws SQLException{
    
    //VIZUALIZANDO PELA COLLECTION
    /* 
    Collections.sort(estoqueGeral);
    for (Produto produto : estoqueGeral) {
      System.out.println(produto);
    }
    */

    //VISUALIZANDO DO ESTOQUE DO BANCO
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("select * from produto");

    while (rs.next()) {
      String id = rs.getString(1);
      String nome = rs.getString(2);
      String tipo = rs.getString(3);
      Double preco = rs.getDouble(4);
      String medida = rs.getString(5);
      String setor = rs.getString(6);
    
      System.out.printf("[ID: %s, NOME: %s, TIPO: %s, PRECO: %.2f, MEDIDA: %s, SETOR: %s]\n\n",id,nome,tipo,preco,medida,setor);
    }

    statement.close();
    connection.close();
  }
  
  //VISUALIZAR QUANTIDADE DE PRODUTOS NO ESTOQUE POR TIPO DE PRODUTO
  public static void quantidadeGeralPorProduto(){
    System.out.println("[QUANTIDADE TOTAL POR TIPO DE PRODUTO]");
    System.out.println("Bebidas = " + bebidaQnt);
    System.out.println("Carnes = " + carneQnt);
    System.out.println("Cereais = " + cerealQnt);
    System.out.println("Condimentos = " + condimentoQnt);
    System.out.println("Doces = " + doceQnt);
    System.out.println("Enlatados = " + enlatadoQnt);
    System.out.println("Frutas = " + frutaQnt);
    System.out.println("Higiene Pessoal = " + higienePessoalQnt);
    System.out.println("Laticinios = " + laticinioQnt);
    System.out.println("Legumes = " + legumeQnt);
    System.out.println("Limpeza = " + limpezaQnt);
    System.out.println("Massas = " + massaQnt);
    System.out.println("Vegetais = " + vegetalQnt);
  }
  

  //VISUALIZAR ESTOQUE POR SETOR
  static public void visualizarPorSetor() throws SQLException{
    
    Map<Integer, Setor> valores = new HashMap<>();
    valores.put(1, Setor.PERECIVEIS);
    valores.put(2, Setor.NAO_PERECIVEIS);
    valores.put(3, Setor.HIGIENE_E_LIMPEZA_PESSOAL);
    valores.put(4, Setor.BEBIDAS);
    valores.put(5, Setor.CONGELADOS);
    valores.put(6, Setor.DOCES);
    valores.put(7, Setor.LIMPEZA_DOMESTICA);
    valores.put(8, Setor.ACOUGUE);
    valores.put(9, Setor.REFRIGERADOS);
    valores.put(10, Setor.HORTIFRUTI);

    String valorEscolhido = "";
    int valorEscolhidoInt = 0;
    
    System.out.println("+------------------------------------------------+");
    System.out.println("|              ESCOLHA O SETOR                   |");
    System.out.println("+------------------------------------------------+");
    System.out.println("|   [1 PERECIVEIS] - [2 NAO PERECIVEIS]          |");
    System.out.println("|   [3 HIGIENE E LIMPEZA PESSOAL] - [4 BEBIDAS]  |");
    System.out.println("|   [5 CONGELADOS] - [6 DOCES]                   |");
    System.out.println("|   [7 LIMPEZA DOMESTICA] - [8 ACOUGUE]          |");
    System.out.println("|   [9 REFRIGERADOS] - [10 HORTI FRUTI]          |");
    System.out.println("+------------------------------------------------+");

    try {
      System.out.println("[DIGITE O SETOR]");
      valorEscolhido = System.console().readLine();
      valorEscolhidoInt = Integer.parseInt(valorEscolhido);
      
    } catch (NumberFormatException e) {
      System.out.println("[ERRO] Apenas numeros permitidos");   
      return;
    
    } catch (Exception e){
      e.printStackTrace();  
      return;
    }
    
    if(valorEscolhidoInt <= 0 || valorEscolhidoInt > 10){
      System.out.println("[ERRO] Setor nao existe.");
      
      return;
    }
 
    int valorFinal = valorEscolhidoInt;

    /* 
    List<Produto> listaDaSecao = estoqueGeral.stream()
      .filter((e) -> e.getLocal() == valores.get(valorFinal))
      .toList();
  
    for (Produto produto : listaDaSecao) {
      System.out.println(produto + " SETOR = " + produto.getLocal().value);
    }
    */ 

    //CONEXAO COM O BANCO, STATEMENT E EXIBICAO DOS DADOS DIRETO DO BANCO 
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("select * from produto where setor = ?");
    preparedStatement.setString(1, valores.get(valorFinal).value);
    ResultSet rs = preparedStatement.executeQuery();

    if(rs.next() == false){
      System.out.println("[SETOR VAZIO]");
      
      return;

    }else{
      
      do {
        String id = rs.getString(1);
        String nome = rs.getString(2);
        String tipo = rs.getString(3);
        Double preco = rs.getDouble(4);
        String medida = rs.getString(5);
        String setor = rs.getString(6);
      
        System.out.printf("[ID: %s, NOME: %s, TIPO: %s, PRECO: %.2f, MEDIDA: %s, SETOR: %s]\n\n",id,nome,tipo,preco,medida,setor);
      
      } while (rs.next());
    
      preparedStatement.close();
      connection.close();
    }
  }



  //VISUALIZAR ESTOQUE POR NOME
  static public void visualizarPorNome() throws SQLException{
    System.out.println("[DIGITE O NOME DO PRODUTO PARA VISUALIZAR O ESTOQUE]");
    String nomeDigitado = System.console().readLine();

    if(Pattern.matches(".*\\d.*", nomeDigitado)){
      System.out.println("[ERRO] Nome invalido");
      return;
    }

    /* 
    List<Produto> produtosAchados = estoqueGeral.stream()
    .filter((e)->e.getNome().equalsIgnoreCase(nomeDigitado))
    .toList();
    if(produtosAchados.isEmpty()){
      System.out.println("[ERRO] Produto nao encontrado.");
    }else{
      for (Produto produto : produtosAchados) {
        System.out.println(produto);
      }
    }
    */

    //CONEXAO COM O BANCO
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("select * from produto where nome = ?");
    preparedStatement.setString(1, nomeDigitado);
    ResultSet rs = preparedStatement.executeQuery();

    if(rs.next() == false){
      System.out.println("[PRODUTO NAO EXISTE]");
      
      return;

    }else{
      
      do {
        String id = rs.getString(1);
        String nome = rs.getString(2);
        String tipo = rs.getString(3);
        Double preco = rs.getDouble(4);
        String medida = rs.getString(5);
        String setor = rs.getString(6);
      
        System.out.printf("[ID: %s, NOME: %s, TIPO: %s, PRECO: %.2f, MEDIDA: %s, SETOR: %s]\n\n",id,nome,tipo,preco,medida,setor);
      
      } while (rs.next());
    
      preparedStatement.close();
      connection.close();
    }
  }



  //ALTERAR PRECO
  static public void alterarPreco() throws SQLException{
    System.out.println("[PARA ALTERAR O PRECO, DIGITE O NOME DO PRODUTO]");
    String nomeProduto = System.console().readLine();
  
    if(Pattern.matches(".*\\d.*", nomeProduto)){
      System.out.println("[ERRO] Nome invalido");
      return;
    }

    /* 
    List<Produto> produtosAchados = estoqueGeral.stream().filter((e)->e.getNome().equalsIgnoreCase(nomeProduto)).toList();
    if(produtosAchados.isEmpty()){
      System.out.println("[ERRO] Produto nao encontrado.");
      return;
    }else{
      for (Produto produto : produtosAchados) {
        System.out.println(produto);
      }
    }
    */

      //CONEXAO COM O BANCO
      Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
      PreparedStatement preparedStatement = connection.prepareStatement("select * from produto where nome = ?");
      preparedStatement.setString(1, nomeProduto);
      ResultSet rs = preparedStatement.executeQuery();

      if(rs.next() == false){
        System.out.println("[PRODUTO NAO EXISTE]");
        
        return;

      }else{
        
        do {
          String id = rs.getString(1);
          String nome = rs.getString(2);
          String tipo = rs.getString(3);
          Double preco = rs.getDouble(4);
          String medida = rs.getString(5);
          String setor = rs.getString(6);
        
          System.out.printf("[ID: %s, NOME: %s, TIPO: %s, PRECO: %.2f, MEDIDA: %s, SETOR: %s]\n\n",id,nome,tipo,preco,medida,setor);
        
        } while (rs.next());
      
        preparedStatement.close();
      }
      
      System.out.println("[DIGITE O NOVO VALOR]");
      String novoValorString = System.console().readLine();
      double novoValorDouble = Double.parseDouble(novoValorString);
      
    
      for (Produto produto : estoqueGeral) {
        if(produto.getNome().equalsIgnoreCase(nomeProduto)){
        produto.setPreco(novoValorDouble);
        System.out.println(produto);
      }
    }
    

    PreparedStatement preparedStatement2 = connection.prepareStatement("update produto set preco = ? where nome = ?");
    preparedStatement2.setDouble(1, novoValorDouble);
    preparedStatement2.setString(2, nomeProduto);
    preparedStatement2.executeUpdate();

    preparedStatement2.close();
    connection.close();
  }
  
  
  // ALTERAR SETOR POR NOME
  static public void alterarSetor() throws SQLException{
    System.out.println("[PARA ALTERAR O SETOR, DIGITE O NOME DO PRODUTO]");
    String nomeProduto = System.console().readLine();
  
    if(Pattern.matches(".*\\d.*", nomeProduto)){
      System.out.println("[ERRO] Nome invalido");
      return;
    }

    /* 
    List<Produto> produtosAchados = estoqueGeral.stream()
    .filter((e)->e.getNome().equalsIgnoreCase(nomeProduto))
    .toList();
    if(produtosAchados.isEmpty()){
      System.out.println("[ERRO] Produto nao encontrado.");
      return;
    }else{
      for (Produto produto : produtosAchados) {
        System.out.println(produto + " SETOR = " + produto.getLocal().value);
      }
    }
    */
    

    //CONEXAO COM O BANCO
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("select * from produto where nome = ?");
    preparedStatement.setString(1, nomeProduto);
    ResultSet rs = preparedStatement.executeQuery();

    if(rs.next() == false){
      System.out.println("[PRODUTO NAO EXISTE]");
        
      return;

    }else{
        
      do {
        String id = rs.getString(1);
        String nome = rs.getString(2);
        String tipo = rs.getString(3);
        Double preco = rs.getDouble(4);
        String medida = rs.getString(5);
        String setor = rs.getString(6);
        
        System.out.printf("[ID: %s, NOME: %s, TIPO: %s, PRECO: %.2f, MEDIDA: %s, SETOR: %s]\n\n",id,nome,tipo,preco,medida,setor);
        
      } while (rs.next());
      
      preparedStatement.close();
    }


    Map<Integer, Setor> valores = new HashMap<>();
    valores.put(1, Setor.PERECIVEIS);
    valores.put(2, Setor.NAO_PERECIVEIS);
    valores.put(3, Setor.HIGIENE_E_LIMPEZA_PESSOAL);
    valores.put(4, Setor.BEBIDAS);
    valores.put(5, Setor.CONGELADOS);
    valores.put(6, Setor.DOCES);
    valores.put(7, Setor.LIMPEZA_DOMESTICA);
    valores.put(8, Setor.ACOUGUE);
    valores.put(9, Setor.REFRIGERADOS);
    valores.put(10, Setor.HORTIFRUTI);
    
    System.out.println("\n");
    System.out.println("+------------------------------------------------+");
    System.out.println("|              ESCOLHA O SETOR                   |");
    System.out.println("+------------------------------------------------+");
    System.out.println("|   [1 PERECIVEIS] - [2 NAO PERECIVEIS]          |");
    System.out.println("|   [3 HIGIENE E LIMPEZA PESSOAL] - [4 BEBIDAS]  |");
    System.out.println("|   [5 CONGELADOS] - [6 DOCES]                   |");
    System.out.println("|   [7 LIMPEZA DOMESTICA] - [8 ACOUGUE]          |");
    System.out.println("|   [9 REFRIGERADOS] - [10 HORTI FRUTI]          |");
    System.out.println("+------------------------------------------------+");
    System.out.println("\n");

    String valorEscolhido = "";
    int valorEscolhidoInt = 0;
    
    try {
      valorEscolhido = System.console().readLine();
      valorEscolhidoInt = Integer.parseInt(valorEscolhido);
      
    } catch (NumberFormatException e) {
      System.out.println("[ERRO] Apenas numeros permitidos");   
      return;
    
    } catch (Exception e){
      e.printStackTrace();  
      return;
    }
    
    if(valorEscolhidoInt <= 0 || valorEscolhidoInt > 10){
      System.out.println("[ERRO] Setor nao existe.");
      
      return;
    }

    for (Produto produto : estoqueGeral) {
      if(produto.getNome().equalsIgnoreCase(nomeProduto)){
        produto.setLocal(valores.get(valorEscolhidoInt));
        System.out.println(produto + " SETOR = " + produto.getLocal().value);
      }
    }

    //ALTERACAO DE SETOR DIRETO DO BANCO
    PreparedStatement preparedStatement2 = connection.prepareStatement("update produto set setor = ? where nome = ?");
    preparedStatement2.setString(1, valores.get(valorEscolhidoInt).value);
    preparedStatement2.setString(2, nomeProduto);
    preparedStatement2.executeUpdate();

    preparedStatement2.close();
    connection.close();
  }

  //ADICIONAR PRODUTO
  static public void addProduto() throws SQLException{
    System.out.println("+-------------------------------------------------------------------+");
    System.out.println("|                        ESCOLHA A CLASSE                           |");
    System.out.println("+-------------------------------------------------------------------+");
    System.out.println("| [1 BEBIDA] - [2 CARNE] - [3 CEREAL] - [4 CONDIMENTO] - [5 DOCE]   |");
    System.out.println("| [6 ENLATADO] - [7 FRUTA] - [8 LIMPEZA] - [9 MASSAS] - [10 LEGUME] |");
    System.out.println("| [11 HIGIENE PESSOAL] - [12 LATICINIO] - [13 VEGETAL]              |");
    System.out.println("+-------------------------------------------------------------------+");

    String valorRecebido = "";
    int valorInt = 0;
    
    try {
      valorRecebido = System.console().readLine();
      valorInt = Integer.parseInt(valorRecebido);
      
    } catch (NumberFormatException e) {
      System.out.println("[ERRO] Apenas numeros permitidos");   
      return;
    
    } catch (Exception e){
      e.printStackTrace();  
      return;
    }
    
    if(valorInt <= 0 || valorInt > 13){
      System.out.println("[ERRO] Classe nao existe.");
      return;
    }

    //CONEXAO COM O BANCO E STATEMENT
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("insert into produto values (?,?,?,?,?,?)");

    if(valorInt == 8 || valorInt == 11 || valorInt == 12){
      System.out.println("Digite o NOME do produto:");
      String nomeProduto = System.console().readLine();
      
      System.out.println("Digite o TIPO do produto:");
      String tipoProduto = System.console().readLine();
      
      System.out.println("Digite o PRECO do produto:");
      String precoProduto = System.console().readLine();
      double precoProdutoDouble = Double.parseDouble(precoProduto);
      
      System.out.println("Digite a MEDIDA(String - L ou Kg) do produto:");
      String medidaProduto = System.console().readLine();
      
      System.out.println("Escolha o SETOR do produto:");
      System.out.println("+------------------------------------------------+");
      System.out.println("|              ESCOLHA O SETOR                   |");
      System.out.println("+------------------------------------------------+");
      System.out.println("|   [1 PERECIVEIS] - [2 NAO PERECIVEIS]          |");
      System.out.println("|   [3 HIGIENE E LIMPEZA PESSOAL] - [4 BEBIDAS]  |");
      System.out.println("|   [5 CONGELADOS] - [6 DOCES]                   |");
      System.out.println("|   [7 LIMPEZA DOMESTICA] - [8 ACOUGUE]          |");
      System.out.println("|   [9 REFRIGERADOS] - [10 HORTI FRUTI]          |");
      System.out.println("+------------------------------------------------+");
      System.out.println("SETOR:");
      String setorEscolhido;
      int setorInt;
      
      try {
        setorEscolhido = System.console().readLine();
        setorInt = Integer.parseInt(setorEscolhido);
        
      } catch (NumberFormatException e) {
        System.out.println("[ERRO] Apenas numeros permitidos");   
        return;
      
      } catch (Exception e){
        e.printStackTrace();  
        return;
      }
      
      if(setorInt <= 0 || setorInt > 10){
        System.out.println("[ERRO] Setor nao existe.");
        return;
      }
      
      Map<Integer, Setor> valores = new HashMap<>();
      valores.put(1, Setor.PERECIVEIS);
      valores.put(2, Setor.NAO_PERECIVEIS);
      valores.put(3, Setor.HIGIENE_E_LIMPEZA_PESSOAL);
      valores.put(4, Setor.BEBIDAS);
      valores.put(5, Setor.CONGELADOS);
      valores.put(6, Setor.DOCES);
      valores.put(7, Setor.LIMPEZA_DOMESTICA);
      valores.put(8, Setor.ACOUGUE);
      valores.put(9, Setor.REFRIGERADOS);
      valores.put(10, Setor.HORTIFRUTI);

      switch (valorInt) {
        case 8:
          System.out.println("Digite a quantidade:");
          String quantidade8 = System.console().readLine();
          int quantidadeInt8 = Integer.parseInt(quantidade8);
          int count8 = 1;
          
          while(count8 <= quantidadeInt8){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Limpeza(id, nomeProduto, tipoProduto, precoProdutoDouble, medidaProduto, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, medidaProduto);
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();

            count8++;
          }
          break;
        case 11:
          System.out.println("Digite a quantidade:");
          String quantidade11 = System.console().readLine();
          int quantidadeInt11 = Integer.parseInt(quantidade11);
          int count11 = 1;
          
          while(count11 <= quantidadeInt11){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new HigienePessoal(id, nomeProduto, tipoProduto, precoProdutoDouble, medidaProduto, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, medidaProduto);
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count11++;
          }
          break;
        case 12:  
          System.out.println("Digite a quantidade:");
          String quantidade12 = System.console().readLine();
          int quantidadeInt12 = Integer.parseInt(quantidade12);
          int count12 = 1;
          
          while(count12 <= quantidadeInt12){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Laticinio(id, nomeProduto, tipoProduto, precoProdutoDouble, medidaProduto, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, medidaProduto);
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();

            count12++;
          }
          break;
      }
      System.out.println("[PRODUTO CRIADO COM SUCESSO]");
      System.out.println("-Digite o nome do produto para verificar ele no estoque-");
      visualizarPorNome();

    }else{
      System.out.println("Digite o NOME do produto:");
      String nomeProduto = System.console().readLine();
      
      System.out.println("Digite o TIPO do produto:");
      String tipoProduto = System.console().readLine();
      
      System.out.println("Digite o PRECO do produto:");
      String precoProduto = System.console().readLine();
      double precoProdutoDouble = Double.parseDouble(precoProduto);
      
      System.out.println("Digite o PESO(float - Kg ou L) do produto:");
      String pesoProduto = System.console().readLine();
      float pesoProdutoFloat = Float.parseFloat(pesoProduto);
      
      System.out.println("Escolha o SETOR do produto:");
      System.out.println("+------------------------------------------------+");
      System.out.println("|              ESCOLHA O SETOR                   |");
      System.out.println("+------------------------------------------------+");
      System.out.println("|   [1 PERECIVEIS] - [2 NAO PERECIVEIS]          |");
      System.out.println("|   [3 HIGIENE E LIMPEZA PESSOAL] - [4 BEBIDAS]  |");
      System.out.println("|   [5 CONGELADOS] - [6 DOCES]                   |");
      System.out.println("|   [7 LIMPEZA DOMESTICA] - [8 ACOUGUE]          |");
      System.out.println("|   [9 REFRIGERADOS] - [10 HORTI FRUTI]          |");
      System.out.println("+------------------------------------------------+");
      System.out.println("SETOR:");
      String setorEscolhido;
      int setorInt;
      
      try {
        setorEscolhido = System.console().readLine();
        setorInt = Integer.parseInt(setorEscolhido);
        
      } catch (NumberFormatException e) {
        System.out.println("[ERRO] Apenas numeros permitidos");   
        return;
      
      } catch (Exception e){
        e.printStackTrace();  
        return;
      }
      
      if(setorInt <= 0 || setorInt > 10){
        System.out.println("[ERRO] Setor nao existe.");
        return;
      }
      
      Map<Integer, Setor> valores = new HashMap<>();
      valores.put(1, Setor.PERECIVEIS);
      valores.put(2, Setor.NAO_PERECIVEIS);
      valores.put(3, Setor.HIGIENE_E_LIMPEZA_PESSOAL);
      valores.put(4, Setor.BEBIDAS);
      valores.put(5, Setor.CONGELADOS);
      valores.put(6, Setor.DOCES);
      valores.put(7, Setor.LIMPEZA_DOMESTICA);
      valores.put(8, Setor.ACOUGUE);
      valores.put(9, Setor.REFRIGERADOS);
      valores.put(10, Setor.HORTIFRUTI);

      switch (valorInt) {
        case 1:
          System.out.println("Digite a quantidade:");
          String quantidade1 = System.console().readLine();
          int quantidadeInt1 = Integer.parseInt(quantidade1);
          int count1 = 1;
          
          while(count1 <= quantidadeInt1){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Bebida(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();

            count1++;
          }
          break;
        case 2:
          System.out.println("Digite a quantidade:");
          String quantidade2 = System.console().readLine();
          int quantidadeInt2 = Integer.parseInt(quantidade2);
          int count2 = 1;
          
          while(count2 <= quantidadeInt2){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Carne(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();

            count2++;
          }
          break;
        case 3:  
          System.out.println("Digite a quantidade:");
          String quantidade3 = System.console().readLine();
          int quantidadeInt3 = Integer.parseInt(quantidade3);
          int count3 = 1;
          
          while(count3 <= quantidadeInt3){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Cereal(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count3++;
          }
          break;
        case 4:  
          System.out.println("Digite a quantidade:");
          String quantidade4 = System.console().readLine();
          int quantidadeInt4 = Integer.parseInt(quantidade4);
          int count4 = 1;
          
          while(count4 <= quantidadeInt4){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Condimento(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count4++;
          }
          break;
        case 5:  
          System.out.println("Digite a quantidade:");
          String quantidade5 = System.console().readLine();
          int quantidadeInt5 = Integer.parseInt(quantidade5);
          int count5 = 1;
          
          while(count5 <= quantidadeInt5){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Doce(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count5++;
          }
          break;
        case 6:  
          System.out.println("Digite a quantidade:");
          String quantidade6 = System.console().readLine();
          int quantidadeInt6 = Integer.parseInt(quantidade6);
          int count6 = 1;
          
          while(count6 <= quantidadeInt6){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Enlatado(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count6++;
          }
          break;
        case 7:  
          System.out.println("Digite a quantidade:");
          String quantidade7 = System.console().readLine();
          int quantidadeInt7 = Integer.parseInt(quantidade7);
          int count7 = 1;
          
          while(count7 <= quantidadeInt7){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Fruta(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count7++;
          }
          break;
        case 9:  
          System.out.println("Digite a quantidade:");
          String quantidade9 = System.console().readLine();
          int quantidadeInt9 = Integer.parseInt(quantidade9);
          int count9 = 1;
          
          while(count9 <= quantidadeInt9){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Massa(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count9++;
          }
          break;
        case 10:  
          System.out.println("Digite a quantidade:");
          String quantidade10 = System.console().readLine();
          int quantidadeInt10 = Integer.parseInt(quantidade10);
          int count10 = 1;
          
          while(count10 <= quantidadeInt10){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Legume(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();

            count10++;
          }
          break;
        case 13:  
          System.out.println("Digite a quantidade:");
          String quantidade13 = System.console().readLine();
          int quantidadeInt13 = Integer.parseInt(quantidade13);
          int count13 = 1;
          
          while(count13 <= quantidadeInt13){
            String id = GeradorId.gerarId();
            estoqueGeral.add(new Vegetal(id, nomeProduto, tipoProduto, precoProdutoDouble, pesoProdutoFloat, valores.get(setorInt)));
            
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, tipoProduto);
            preparedStatement.setDouble(4, precoProdutoDouble);
            preparedStatement.setString(5, Float.toString(pesoProdutoFloat));
            preparedStatement.setString(6, valores.get(setorInt).value);
            preparedStatement.executeUpdate();
            
            count13++;
          }
          break;         
      }      
      System.out.println("[PRODUTO CRIADO COM SUCESSO]");
      System.out.println("-Digite o nome do produto para verificar ele no estoque-");
      visualizarPorNome();
    }

    preparedStatement.close();
    connection.close();
  }

  //EXCLUIR PRODUTO
  static public void excluirProdutoPeloNome() throws SQLException{
    System.out.println("[DIGITE O NOME DO PRODUTO QUE DESEJA EXCLUIR]");
    String nomeProduto = System.console().readLine();
    
    int i;
    for(i = 0; i <= estoqueGeral.size() - 1; i++){
      if(estoqueGeral.get(i).getNome().equalsIgnoreCase(nomeProduto)){
        estoqueGeral.remove(i);
        i--;
      } 
    }

    //CONEXAO COM O BANCO PARA EXCLUIR DADOS
    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("delete from produto where nome = ?");
    preparedStatement.setString(1, nomeProduto);
    int result = preparedStatement.executeUpdate();
    
    if(result == 0){
      System.out.println("[PRODUTO NAO EXISTE]");
      return;
    }

    System.out.println("[PRODUTO EXCLUIDO COM SUCESSO]");
  }

 
  //ESTOQUE INICIAL
  static public void iniciarEstoque() throws SQLException{
    for(int i = 1; i<= 10; i++){
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "coca-cola", "zero", 6.00, 1, Setor.REFRIGERADOS));
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "Sprite", "original", 7.50, 2, Setor.REFRIGERADOS));
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "Fanta", "Uva", 6.50, 2, Setor.REFRIGERADOS));
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "Jack Daniels", "Honey", 129.90, 1, Setor.BEBIDAS));
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "Johnnie Walker", "Red Label", 150.90, 1, Setor.BEBIDAS));
      estoqueGeral.add(new Bebida(GeradorId.gerarId(), "Pitu", "Limão", 45.90, 1, Setor.BEBIDAS));
      
      estoqueGeral.add(new Carne(GeradorId.gerarId(), "Bovina", "Maminha", 70.90, 1, Setor.ACOUGUE));
      estoqueGeral.add(new Carne(GeradorId.gerarId(), "Bovina", "Picanha", 90.99, 1, Setor.ACOUGUE));
      estoqueGeral.add(new Carne(GeradorId.gerarId(), "Suina", "Costela", 49.90, 1, Setor.ACOUGUE));
      
      estoqueGeral.add(new Cereal(GeradorId.gerarId(), "Arroz", "branco", 6.89, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Cereal(GeradorId.gerarId(), "Aveia", "flocos", 4.50, 0.5f, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Cereal(GeradorId.gerarId(), "Grao de Bico", "Normal", 7.00, 0.5f, Setor.NAO_PERECIVEIS));
      
      estoqueGeral.add(new Condimento(GeradorId.gerarId(), "Sal", "Refinado", 2.79, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Condimento(GeradorId.gerarId(), "Acucar", "Macavo", 9.00, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Condimento(GeradorId.gerarId(), "Pimenta", "Malagueta", 3.00, 0.5f, Setor.NAO_PERECIVEIS));
      
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "Chocolate", "Branco", 10.00, 0.5f, Setor.DOCES));
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "Chocolate", "Ao leite", 13.00, 0.5f, Setor.DOCES));
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "Fini", "Dentadura", 10.00, 0.5f, Setor.DOCES));
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "Pacoca", "Amedoin", 8.00, 0.5f, Setor.DOCES));
      
      estoqueGeral.add(new Enlatado(GeradorId.gerarId(), "feijao", "carioca", 8.00, 0.3f, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Enlatado(GeradorId.gerarId(), "fiambre", "suino", 7.50, 0.320f, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Enlatado(GeradorId.gerarId(), "sardinha", "com oleo", 5.00, 0.084f, Setor.NAO_PERECIVEIS));
      
      estoqueGeral.add(new Fruta(GeradorId.gerarId(), "uva", "verde", 10.00, 1, Setor.HORTIFRUTI));
      estoqueGeral.add(new Fruta(GeradorId.gerarId(), "pera", "verde", 11.00, 1, Setor.HORTIFRUTI));
      estoqueGeral.add(new Fruta(GeradorId.gerarId(), "maca", "vermelha", 9.50, 1, Setor.HORTIFRUTI));
      estoqueGeral.add(new Fruta(GeradorId.gerarId(), "manga", "rosa", 8.20, 1, Setor.HORTIFRUTI));
      
      estoqueGeral.add(new HigienePessoal(GeradorId.gerarId(), "creme dental", "illuminous white", 3.20, "0.5g", Setor.HIGIENE_E_LIMPEZA_PESSOAL));
      estoqueGeral.add(new HigienePessoal(GeradorId.gerarId(), "sabonete", "protex", 2.20, "0.1g", Setor.HIGIENE_E_LIMPEZA_PESSOAL));
      estoqueGeral.add(new HigienePessoal(GeradorId.gerarId(), "Shampoo", "anticaspa", 14.60, "0.5ml", Setor.HIGIENE_E_LIMPEZA_PESSOAL));
      
      estoqueGeral.add(new Laticinio(GeradorId.gerarId(), "Leite", "caixa", 12.60, "1L", Setor.REFRIGERADOS));
      estoqueGeral.add(new Laticinio(GeradorId.gerarId(), "Iorgute", "Morango", 10.60, "1L", Setor.REFRIGERADOS));
      estoqueGeral.add(new Laticinio(GeradorId.gerarId(), "Queijo", "Parmesao", 10.60, "0.5kg", Setor.PERECIVEIS));
      estoqueGeral.add(new Laticinio(GeradorId.gerarId(), "Achocolatado", "Chocolate", 13.60, "1L", Setor.REFRIGERADOS));
      estoqueGeral.add(new Laticinio(GeradorId.gerarId(), "Requeijao", "Padrao", 10.00, "0.4kg", Setor.REFRIGERADOS));
      
      estoqueGeral.add(new Legume(GeradorId.gerarId(), "Feijao", "preto", 10.00, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Legume(GeradorId.gerarId(), "Brocolis", "padrao", 9.00, 0.6f, Setor.HORTIFRUTI));
      estoqueGeral.add(new Legume(GeradorId.gerarId(), "Tomate", "padrao", 7.00, 1, Setor.HORTIFRUTI));
      
      estoqueGeral.add(new Limpeza(GeradorId.gerarId(), "Agua Sanitaria", "padrao", 5.00, "1L", Setor.LIMPEZA_DOMESTICA));
      estoqueGeral.add(new Limpeza(GeradorId.gerarId(), "Amaciante", "lavanda", 15.00, "2.5L", Setor.LIMPEZA_DOMESTICA));
      estoqueGeral.add(new Limpeza(GeradorId.gerarId(), "Desinfetante", "pinho sol", 12.00, "1.75L", Setor.LIMPEZA_DOMESTICA));
      
      estoqueGeral.add(new Massa(GeradorId.gerarId(), "Pene", "padrao", 12.00, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Massa(GeradorId.gerarId(), "Fetuccine", "padrao", 12.00, 1, Setor.NAO_PERECIVEIS));
      estoqueGeral.add(new Massa(GeradorId.gerarId(), "Espaguete", "padrao", 14.50, 1, Setor.NAO_PERECIVEIS));
      
      estoqueGeral.add(new Vegetal(GeradorId.gerarId(), "Alface", "padrao", 7.50, 1, Setor.HORTIFRUTI));
      estoqueGeral.add(new Vegetal(GeradorId.gerarId(), "Cebola", "roxa", 6.50, 1, Setor.HORTIFRUTI));
      estoqueGeral.add(new Vegetal(GeradorId.gerarId(), "Couve", "padrao", 5.55, 1, Setor.HORTIFRUTI));
      
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "sorvete", "napolitano", 15.55, 1, Setor.CONGELADOS));
      estoqueGeral.add(new Doce(GeradorId.gerarId(), "sorvete", "flocos", 20.25, 2, Setor.CONGELADOS));
      estoqueGeral.add(new Massa(GeradorId.gerarId(), "pizza", "calabresa", 39.95, 0.8F, Setor.CONGELADOS));
    }

    Collections.sort(estoqueGeral);
    /* 
     Connection con = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
     PreparedStatement pst = con.prepareStatement("insert into produto values (?,?,?,?,?,?)");
     
      for (Produto produto : estoqueGeral) {
        pst.setString(1, produto.getId());
        pst.setString(2, produto.getNome());
        pst.setString(3, produto.getTipo());
        pst.setDouble(4, produto.getPreco());
        pst.setString(5, produto.getMedida());
        pst.setString(6, produto.getLocal().value);
        pst.executeUpdate();
      }
      
      pst.close();
      con.close();
    */
  }
}