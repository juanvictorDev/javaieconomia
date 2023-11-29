package funcionario.cargos;

import funcionario.Cargo;

public class Seguranca implements Cargo{
  static double salario = 2450.00d;
  static String nome = "Seguranca";
  private static final long serialVersionUID = 8L;

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    Seguranca.salario = salario;
  }
}
