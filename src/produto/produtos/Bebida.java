package produto.produtos;

import produto.Setor;
import controller.Estoque;
import produto.Produto;

public class Bebida implements Produto{
  private String id;
  private String nome;
  private String tipo;
  private double preco;
  private float litro;
  private Setor local;

  public Bebida(String id, String nome, String tipo, double preco, float litro, Setor local){
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.preco = preco;
    this.litro = litro;
    this.local = local;

    Estoque.bebidaQnt++;
  }

  //getters
  public String getId() {
    return id;
  }
  public String getNome() {
    return nome;
  }
  public String getTipo() {
    return tipo;
  }
  public double getPreco() {
    return preco;
  }
  public String getMedida() {
    return Float.toHexString(litro);
  }
  public Setor getLocal() {
    return local;
  }

  //setters
  public void setId(String id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public void setPreco(double preco) {
    this.preco = preco;
  }
  public void setLitro(float litro) {
    this.litro = litro;
  }
  public void setLocal(Setor local) {
    this.local = local;
  }

  public void visualizar(){
    System.out.println("+---------------------+");
    System.out.println("|       Produto       |");
    System.out.println("+---------------------+");
    System.out.printf("  Nome:  %s \n", nome);
    System.out.printf("  Tipo:  %s \n", tipo);
    System.out.printf("  Preço: $%.2f \n", preco);
    System.out.printf("  Peso:  %.2fkg \n", litro);
    System.out.println("+---------------------+");
  }

  public String toString(){
    return "[id: " + id + ", nome: " + nome + ", tipo: " + tipo + ", preco: R$" + preco + ", litro(s): " + litro + "]";
  }

  public int compareTo(Produto obj) {
    return this.nome.compareTo(obj.getNome());
  }
}
