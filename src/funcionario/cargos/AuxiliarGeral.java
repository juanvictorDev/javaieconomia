package funcionario.cargos;

import funcionario.Cargo;

public class AuxiliarGeral implements Cargo{
  static double salario = 1100.10d;
  static String nome = "Auxiliar Geral";
  private static final long serialVersionUID = 2L;

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    AuxiliarGeral.salario = salario;
  }
}
