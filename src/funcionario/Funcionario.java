package funcionario;

public class Funcionario implements Comparable<Funcionario>{
  String id;
  long cpf;
  String nome;
  char sexo;
  String cidade;
  String bairro;
  String rua;
  int numeroRua;
  String telefone;
  String nascimento;
  Cargo cargo;

  public Funcionario(String id, long cpf, String nome, char sexo, String cidade, String bairro, String rua, int numeroRua,String telefone, String nascimento, Cargo cargo){
    this.id = id;
    this.cpf = cpf;
    this.nome = nome;
    this.sexo = sexo;
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
    this.numeroRua = numeroRua;
    this.telefone = telefone;
    this.nascimento = nascimento;
    this.cargo = cargo;
  }

  //GETTERS
  public String getId() {
    return id;
  }
  public long getCpf() {
    return cpf;
  }
  public String getNome() {
    return nome;
  }
  public char getSexo() {
    return sexo;
  }
  public String getCidade() {
    return cidade;
  }
  public String getBairro() {
    return bairro;
  }
  public String getRua() {
    return rua;
  }
  public int getNumeroRua() {
    return numeroRua;
  }
  public String getTelefone() {
    return telefone;
  }
  public String getNascimento() {
    return nascimento;
  }
  public Cargo getCargo() {
    return cargo;
  }

  //SETTERS
  public void setId(String id) {
    this.id = id;
  }
  public void setCpf(long cpf) {
    this.cpf = cpf;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setSexo(char sexo) {
    this.sexo = sexo;
  }
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  public void setRua(String rua) {
    this.rua = rua;
  }
  public void setNumeroRua(int numeroRua) {
    this.numeroRua = numeroRua;
  }
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  public void setNascimento(String nascimento) {
    this.nascimento = nascimento;
  }
  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public String toString(){
    return "[ID: " + this.id + ", CPF: " + this.cpf + ", NOME: "+ this.nome + ", SEXO: " + this.sexo + ", CIDADE: " + this.cidade + ", BAIRRO: " + this.bairro + " RUA: " + this.rua + ", NUMERO: " + this.numeroRua + ", TELEFONE: " + this.telefone + ", NASCIMENTO: " + this.nascimento + ", CARGO: " + this.cargo.getNome() + "]\n";
  }

  public int compareTo(Funcionario obj) {
    return this.cargo.getNome().compareTo(obj.cargo.getNome());
  }
}
