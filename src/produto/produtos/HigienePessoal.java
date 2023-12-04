package produto.produtos;

import produto.Setor;
import controller.Estoque;
import produto.Produto;

public class HigienePessoal implements Produto{
  private String id;
  private String nome;
  private String tipo;
  private double preco;
  private String medida;
  private Setor local;

  public HigienePessoal(String id, String nome, String tipo, double preco, String medida, Setor local){
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.preco = preco;
    this.medida = medida;
    this.local = local;

    Estoque.higienePessoalQnt++;
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
    return medida;
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
  public void setMedida(String medida) {
    this.medida = medida;
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
    System.out.printf("  Peso:  %s \n", medida);
    System.out.println("+---------------------+");
  }

  public String toString(){
    return "[id: " + id + ", nome: " + nome + ", tipo: " + tipo + ", preco: R$" + preco + ", peso: " + medida + "]";
  }

  public int compareTo(Produto obj) {
    return this.nome.compareTo(obj.getNome());
  }
}
