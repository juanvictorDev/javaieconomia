package cliente;

import java.util.*;
import produto.*;
import java.text.DecimalFormat;

public class Cliente {
  static ArrayList<Produto> carrinho = new ArrayList<>();
  
  //VISUALIZAR PRODUTOS NO SETOR
  public static void visitarSetor(){
    Estoque.visualizarPorSetor();
  }

  //ADICIONAR NO CARRINHO
  public static void adicionarNoCarrinho(){
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
    for (Produto produto : Estoque.estoqueGeral) {
      if(produto.getNome().equalsIgnoreCase(produtoEscolhido) && produto.getTipo().equalsIgnoreCase(tipoEscolhido)){
        count++;
      }
    }
    
    if(count == 0){
      System.out.println("produto nao existe");
      return;
    }

    System.out.println("digite a quantidade desejada");
    String quantidade = System.console().readLine();
    int quantidadeDesejada = Integer.parseInt(quantidade);

    if(quantidadeDesejada > count || quantidadeDesejada == 0){
      System.out.println("nao e possivel realizar operacao, quantidade escolhida invalida");
      return;
    }
    String tipoEscolhidoF = tipoEscolhido;
    String produtoEscolhidoF = produtoEscolhido;
    List<Produto> escolhidos = Estoque.estoqueGeral.stream()
        .filter((e)->e.getNome().equalsIgnoreCase(produtoEscolhidoF) && e.getTipo()
        .equalsIgnoreCase(tipoEscolhidoF))
        .limit(quantidadeDesejada)
        .toList();
    carrinho.addAll(escolhidos);

    System.out.println("produto adicionado com sucesso");
    for (Produto produto : carrinho) {
      System.out.println(produto);
    }
  }

  //CHECKOUT
  public static void checkout(){

    double total = 0;
    DecimalFormat df = new DecimalFormat("#.##");

    System.out.println("+---------------------------+");
    System.out.println("|         CARRINHO          |");
    System.out.println("+---------------------------+");
    for (Produto produto : carrinho) {
      System.out.println("." + produto.getNome() + "   preco: " + produto.getPreco());
      total += produto.getPreco();
    }
    String valorFormatado = df.format(total);
    System.out.println("+---------------------------+");
    System.out.println("  TOTAL = " + "        " + valorFormatado);
  }


  //COMPRAR
  public static void comprar(){
    if(carrinho.isEmpty()){
      System.out.println("nao e possivel comprar com o carrinho vazio");
      return;
    }
    
    for (int i = 0; i <= carrinho.size() - 1; i++) {
      for (int j = 0; j <= Estoque.estoqueGeral.size() - 1; j++) {
        if(Estoque.estoqueGeral.get(j).getId().equals(carrinho.get(i).getId())){
          Estoque.estoqueGeral.remove(j);
        }
      }
    }

    System.out.println("COMPRA REALIZADA COM SUCESSO, OBRIGADO PELA PREFERENCIA!");
    carrinho.clear();
  }
}
