package funcionario;
import java.io.Serializable;

public interface Cargo extends Serializable{
  String getNome();
  double getSalario();
  
  default void getSenhaSistema(){
    System.out.println("Não possui acesso ao Sistema Administrativo");
  }

  default void getSenhaCaixa(){
    System.out.println("Não possui acesso ao Caixa");
  }
}
