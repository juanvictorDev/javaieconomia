package funcionario.cargos;

import funcionario.Cargo;

public class Estoquista implements Cargo{
  static double salario = 1350.00d;
  static String nome = "Estoquista";
  private static final long serialVersionUID = 4L;

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    Estoquista.salario = salario;
  }
}
