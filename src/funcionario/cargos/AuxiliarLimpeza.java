package funcionario.cargos;

import funcionario.Cargo;

public class AuxiliarLimpeza implements Cargo{
  static double salario = 1300.00d;
  static String nome = "Auxiliar de Limpeza";
  private static final long serialVersionUID = 3L;

  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }

  public static void setSalario(double salario) {
    AuxiliarLimpeza.salario = salario;
  }
}
