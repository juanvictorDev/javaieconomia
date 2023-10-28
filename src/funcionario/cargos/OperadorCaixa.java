package funcionario.cargos;

import funcionario.Cargo;

public class OperadorCaixa implements Cargo{
  static double salario = 2100.00d;
  static String nome = "Operador de Caixa";
  private String senhaCaixa;

  public OperadorCaixa(String senhaCaixa){
    this.senhaCaixa = senhaCaixa;
  }

  //GETTERS
  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }
  
  public void getSenhaCaixa(){
    System.out.println(this.senhaCaixa);
  }


  //SETTERS
  public static void setSalario(double salario) {
    OperadorCaixa.salario = salario;
  }

}
