package funcionario;

public interface Cargo {
  String getNome();
  double getSalario();
  
  default void getSenhaSistema(){
    System.out.println("Não possui acesso ao Sistema Administrativo");
  }

  default void getSenhaCaixa(){
    System.out.println("Não possui acesso ao Caixa");
  }
}
