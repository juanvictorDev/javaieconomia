package geradorId;
import java.util.Random;

public class GeradorId {
  private static final String FONTE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static Random random = new Random();

  public static String gerarId(){
    StringBuilder str = new StringBuilder(6);
    
    int i = 1;
    while(i <= 6){
      int idAtual = random.nextInt(FONTE.length());
      str.append(FONTE.charAt(idAtual));
      i++;
    }
    
    return str.toString();
  }
}
