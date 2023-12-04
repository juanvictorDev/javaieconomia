package produto;

public interface Produto extends Comparable<Produto>{
  public String getId();
  public String getNome();
  public String getTipo();
  public double getPreco();
  public String getMedida();
  public Setor getLocal();
  public void setPreco(double x);
  public void setLocal(Setor x);
}
