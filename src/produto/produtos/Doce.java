package produto.produtos;

import produto.Setor;
import produto.Estoque;
import produto.Produto;

public class Doce implements Produto{
  private String id;
  private String nome;
  private String tipo;
  private double preco;
  private float peso;
  private Setor local;

  public Doce(String id, String nome, String tipo, double preco, float peso, Setor local){
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.preco = preco;
    this.peso = peso;
    this.local = local;

    Estoque.doceQnt++;
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
  public float getPeso() {
    return peso;
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
  public void setPeso(float peso) {
    this.peso = peso;
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
    System.out.printf("  Peso:  %.2fkg \n", peso);
    System.out.println("+---------------------+");
  }

  public String toString(){
    return "[id: " + id + ", nome: " + nome + ", tipo: " + tipo + ", preco: R$" + preco + ", peso: " + peso + "]";
  }

  public int compareTo(Produto obj) {
    return this.nome.compareTo(obj.getNome());
  }
}
