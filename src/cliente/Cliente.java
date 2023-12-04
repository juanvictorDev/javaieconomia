package cliente;

import java.util.*;

import controller.Estoque;
import java.sql.*;
import java.text.DecimalFormat;

public class Cliente {
  static ArrayList<ProdutoCarrinho> carrinho = new ArrayList<>();
  
  //VISUALIZAR PRODUTOS NO SETOR
  public static void visitarSetor() throws SQLException{
    Estoque.visualizarPorSetor();
  }

  //ADICIONAR NO CARRINHO
  public static void adicionarNoCarrinho() throws SQLException{
    String produtoEscolhido = "";
    String tipoEscolhido = "";
    
    try {
      System.out.println("qual produto voce escolhe, digite seu nome: ");
      produtoEscolhido = System.console().readLine();
      System.out.println("digite seu tipo: ");
      tipoEscolhido = System.console().readLine();
    } catch (Exception e) {
      System.out.println("digite um produto valido");
      return;
    }

    int count = 0;
    
    /* 
     for (Produto produto : Estoque.estoqueGeral) {
       if(produto.getNome().equalsIgnoreCase(produtoEscolhido) && produto.getTipo().equalsIgnoreCase(tipoEscolhido)){
         count++;
        }
      }
    */
    List<ProdutoCarrinho> achados = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = connection.prepareStatement("select * from produto where nome = ? and tipo = ?", ResultSet.TYPE_SCROLL_INSENSITIVE);
    preparedStatement.setString(1, produtoEscolhido);
    preparedStatement.setString(2, tipoEscolhido);
    ResultSet rs = preparedStatement.executeQuery();
    
    while (rs.next()) {
      achados.add(new ProdutoCarrinho(rs.getString("id"), rs.getString("nome"), rs.getString("tipo"), rs.getDouble("preco"), rs.getString("medida"), rs.getString("setor")));
      ++count;
    }

    if(count == 0){
      System.out.println("[ERRO] PRODUTO NAO EXISTE \n");
      return;
    }

    System.out.println("digite a quantidade desejada");
    String quantidade = System.console().readLine();
    int quantidadeDesejada = Integer.parseInt(quantidade);
    
    if(quantidadeDesejada > count || quantidadeDesejada <= 0){
      System.out.println("[ERRO] nao e possivel realizar operacao, quantidade escolhida invalida \n");
      return;
    }
    
    /* 
    String tipoEscolhidoF = tipoEscolhido;
    String produtoEscolhidoF = produtoEscolhido;
    List<Produto> escolhidos = Estoque.estoqueGeral.stream()
    .filter((e)->e.getNome().equalsIgnoreCase(produtoEscolhidoF) && e.getTipo().equalsIgnoreCase(tipoEscolhidoF))
    .limit(quantidadeDesejada)
    .toList();
    carrinho.addAll(escolhidos);
    */

    int i = 0;
    while(i < quantidadeDesejada){
      carrinho.add(achados.get(i));
      ++i;
    }

    achados.clear();
    preparedStatement.close();
    connection.close();

    System.out.println("PRODUTO ADICIONADO COM SUCESSO");
    
  }

  //CHECKOUT
  public static void checkout(){
    
    double total = 0;
    DecimalFormat df = new DecimalFormat("#.##");

    System.out.println("+---------------------------+");
    System.out.println("|         CARRINHO          |");
    System.out.println("+---------------------------+");
    for (ProdutoCarrinho produto : carrinho) {
      System.out.println("." + produto.getNome() + "   preco: " + produto.getPreco());
      total += produto.getPreco();
    }
    String valorFormatado = df.format(total);
    System.out.println("+---------------------------+");
    System.out.println("  TOTAL = " + "        " + valorFormatado);
  }


  //COMPRAR
  public static void comprar() throws SQLException{
    if(carrinho.isEmpty()){
      System.out.println("nao e possivel comprar com o carrinho vazio");
      return;
    }
    
    //DELETANDO DO ESTOQUE DO JAVA
    for (int i = 0; i <= carrinho.size() - 1; i++) {
      for (int j = 0; j <= Estoque.estoqueGeral.size() - 1; j++) {
        if(Estoque.estoqueGeral.get(j).getId().equals(carrinho.get(i).getId())){
          Estoque.estoqueGeral.remove(j);
        }
      }
    }

    //DELETANDO DO ESTOQUE DO BANCO
    Connection con = DriverManager.getConnection("jdbc:sqlite:database\\javaieconomia.db");
    PreparedStatement preparedStatement = con.prepareStatement("delete from produto where id = ?");

    for (ProdutoCarrinho produtoCarrinho : carrinho) {
      preparedStatement.setString(1, produtoCarrinho.getId());
      preparedStatement.executeUpdate();
    }

    carrinho.clear();
    preparedStatement.close();
    con.close();
    
    System.out.println("COMPRA REALIZADA COM SUCESSO, OBRIGADO PELA PREFERENCIA!");
  }
}
