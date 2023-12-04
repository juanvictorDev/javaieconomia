package produto;

public enum Setor {
  PERECIVEIS("Pereciveis"), NAO_PERECIVEIS("Nao Pereciveis"), HIGIENE_E_LIMPEZA_PESSOAL("Higiene e Limpeza Pessoa"), 
  BEBIDAS("Bebidas"), CONGELADOS("Congelados"), DOCES("Doces"), LIMPEZA_DOMESTICA("Limpeza Domestica"),
  ACOUGUE("Acougue"), REFRIGERADOS("Refrigerados"), HORTIFRUTI("Horti Fruti");

  public String value;
  
  Setor(String value){
    this.value = value;
  }

}
