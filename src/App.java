import cliente.*;
import funcionario.*;
import produto.*;

//arrumar a validação do funcionarios pq quando cadastra e reinicia o java exclui e a do banco n
//ent quando reinicia se perde os que foram cadastrados mas no banco permanece

public class App {
  public static void main(String[] args) throws Exception {
    Estoque.iniciarEstoque();
    SistemaADM.iniciarFuncionarios();

    int opcaoBase;
    
    while(true){
      System.out.println("[ SISTEMA INICIAL ]");
      System.out.println("+-----------------+");
      System.out.println("| 1 - ADM         |");
      System.out.println("| 2 - CLIENTE     |");
      System.out.println("+-----------------+");
      System.out.println("| 0 - SAIR        |");
      System.out.println("+-----------------+");
      System.out.print("[ENTRAR COMO]: ");
      opcaoBase = Integer.parseInt(System.console().readLine());
      
      //VERIFICACAO
      if(opcaoBase < 0 || opcaoBase > 2){
        System.out.println("[ERRO] DIGITE UM VALOR VALIDO!");
      }
    
      //SAIR
      if(opcaoBase == 0){
        System.out.println("Saindo do Sistema..."); 
        break;
      } 

      //ENTRAR COMO ADM
      if(opcaoBase == 1){
        int opcao1;
        
        while(true){
          System.out.println("[SISTEMA DE ADMINISTRACAO]");
          System.out.println("+------------------+");
          System.out.println("| 1 - ESTOQUE      |");
          System.out.println("| 2 - FUNCIONARIOS |");
          System.out.println("+------------------+");
          System.out.println("| 0 - SAIR         |");
          System.out.println("+------------------+");
          System.out.print("[ENTRAR NO]: ");
          opcao1 = Integer.parseInt(System.console().readLine());
          
          //VERIFICACAO
          if(opcao1 < 0 || opcao1 > 2){
            System.out.println("[ERRO] DIGITE UM VALOR VALIDO!");
          }
          
          //SAIR
          if (opcao1 == 0) break;
          
          //ENTRAR NO ESTOQUE
          if(opcao1 == 1){
            
            int opcao1_1;
            while (true) {
              System.out.println("+--------------------------------------------+");
              System.out.println("[            SISTEMA DE ESTOQUE              ]");
              System.out.println("+--------------------------------------------+");
              System.out.println("| 1 - VISUALIZAR ESTOQUE GERAL               |");
              System.out.println("| 2 - VISUALIZAR ESTOQUE POR TIPO            |");
              System.out.println("| 3 - VISUALIZAR ESTOQUE POR SETOR           |");
              System.out.println("| 4 - VISUALIZAR ESTOQUE POR NOME DO PRODUTO |");
              System.out.println("| 5 - ALTERAR PRECO DO PRODUTO               |");
              System.out.println("| 6 - ALTERAR SETOR DO PRODUTO               |");
              System.out.println("| 7 - ADICIONAR PRODUTO                      |");
              System.out.println("| 8 - EXCLUIR PRODUTO                        |");
              System.out.println("+--------------------------------------------+");
              System.out.println("| 0 - SAIR                                   |");
              System.out.println("+--------------------------------------------+");
              opcao1_1 = Integer.parseInt(System.console().readLine());
              
              //VERIFICACAO
              if(opcao1_1 < 0 || opcao1_1 > 8){
                System.out.println("[ERRO] DIGITE UM VALOR VALIDO!");
              }
              
              //SAIR
              if (opcao1_1 == 0) break;
              
              //ESCOLHAS
              switch (opcao1_1) {
                case 1:
                  Estoque.visualizarGeral();
                  break;
                case 2:
                  Estoque.quantidadeGeralPorProduto();
                  break;
                case 3:
                  Estoque.visualizarPorSetor();
                  break;
                case 4:
                  Estoque.visualizarPorNome();
                  break;
                case 5:
                  Estoque.alterarPreco();
                  break;
                case 6:
                  Estoque.alterarSetor();
                  break;
                case 7:
                  Estoque.addProduto();
                  break;
                case 8:
                  Estoque.excluirProdutoPeloNome();
                  break;
              }
            }
          }


          //ENTRAR NO FUNCIONARIOS
          if(opcao1 == 2){
            
            int opcao1_2;
            while (true) {
              System.out.println("+----------------------------+");
              System.out.println("[   SISTEMA DE FUNCIONARIOS  ]");
              System.out.println("+----------------------------+");
              System.out.println("| 1 - CONTRATAR FUNCIONARIOS |");
              System.out.println("| 2 - ATUALIZAR FUNCIONARIOS |");
              System.out.println("| 3 - LISTAR FUNCIONARIOS    |");
              System.out.println("| 4 - DELETAR FUNCIONARIOS   |");
              System.out.println("+----------------------------+");
              System.out.println("| 0 - SAIR                   |");
              System.out.println("+----------------------------+");
              opcao1_2 = Integer.parseInt(System.console().readLine());
              
              //VERIFICACAO
              if(opcao1_2 < 0 || opcao1_2 > 4){
                System.out.println("[ERRO] DIGITE UM VALOR VALIDO!");
              }
              
              //SAIR
              if (opcao1_2 == 0) break;
              
              //ESCOLHAS
              switch (opcao1_2) {
                case 1:
                  SistemaADM.addFuncionario();
                  break;
                case 2:
                  SistemaADM.atualizarFuncionario();
                  break;
                case 3:
                  SistemaADM.listarTodosFuncionarios();
                  break;
                case 4:
                  SistemaADM.deletarFuncionario();
                  break;
              }
            }            
          }
        }
      }


      
      //ENTRAR COMO CLIENTE
      if(opcaoBase == 2){
        int opcao2;
        while (true) {
          System.out.println("+----------------------------+");
          System.out.println("[ BEM VINDO AO JAVAIECONOMIA ]");
          System.out.println("+----------------------------+");
          System.out.println("| 1 - VISUALIZAR PRODUTOS    |");
          System.out.println("| 2 - ADICIONAR NO CARRINHO  |");
          System.out.println("| 3 - CHECKOUT               |");
          System.out.println("| 4 - COMPRAR                |");
          System.out.println("+----------------------------+");
          System.out.println("| 0 - SAIR                   |");
          System.out.println("+----------------------------+");
          opcao2 = Integer.parseInt(System.console().readLine());
              
          //VERIFICACAO
          if(opcao2 < 0 || opcao2 > 4){
            System.out.println("[ERRO] DIGITE UM VALOR VALIDO!");
          }
              
          //SAIR
          if (opcao2 == 0) break;
              
          //ESCOLHAS
          switch (opcao2) {
            case 1:
              Cliente.visitarSetor();
              break;
            case 2:
              Cliente.adicionarNoCarrinho();
              break;
            case 3:
              Cliente.checkout();
              break;
            case 4:
              Cliente.comprar();
              break;
          }
        }        
      }


    }
  }
}
