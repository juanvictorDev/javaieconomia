package funcionario.cargos;

import funcionario.Cargo;

public class Repositor implements Cargo{
  static double salario = 1100.00d;
  static String nome = "Repositor";

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    Repositor.salario = salario;
  }
}
