package funcionario.cargos;

import funcionario.Cargo;

public class Acougueiro implements Cargo{
  static double salario = 2324.59d;
  static String nome = "Acougueiro";

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    Acougueiro.salario = salario;
  }
}
