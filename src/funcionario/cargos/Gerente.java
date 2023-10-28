package funcionario.cargos;

import funcionario.Cargo;

public class Gerente implements Cargo{
  static double salario = 5500.00d;
  static String nome = "Gerente";
  private String senhaSistema;

  public Gerente(String senhaSistema){
    this.senhaSistema = senhaSistema;
  }

  //GETTERS
  public String getNome() {
    return nome;
  }

  public double getSalario() {
    return salario;
  }
  
  public void getSenhaSistema(){
    System.out.println(this.senhaSistema);
  }


  //SETTERS
  public static void setSalario(double salario) {
    Gerente.salario = salario;
  }

}
