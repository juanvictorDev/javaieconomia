package cliente;

public class ProdutoCarrinho{
  private String id;
  private String nome;
  private String tipo;
  private double preco;
  private String medida;
  private String local;

  public ProdutoCarrinho(String id, String nome, String tipo, double preco, String medida, String local){
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.preco = preco;
    this.medida = medida;
    this.local = local;
  }


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
  public String getLocal() {
    return local;
  }

}
