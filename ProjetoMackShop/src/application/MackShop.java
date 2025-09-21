package application;

import java.util.Locale;
import java.util.Scanner;

public class MackShop {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// VARIÁVEIS DE CONTROLE
		boolean menu = true;
		boolean condBaseIniciada;
		int countNumBase = 0;
		
		//CATALOGO DE PRODUTOS
		int[] idsProdutos = null;
		String[] nomesProdutos = null;
		double[] precosProdutos = null;
		int[] estoquesProdutos = null;
		
		// VENDA ATUAL
		int[] vendaAtualIds;
		int[] vendaAtualQuantidades;
		
		// HISTÓRICO DE VENDAS
		int[] historicoIdsPedidos;
		double[] historicoValoresPedidos;
		int[][] historicoItensVendidos;
		
		showWelcomeMsg();
		
		do {
			
			mainMenu();
			
			String option = sc.nextLine();
			
			switch (option) { 
				case "1":
					countNumBase += 1;
					
					idsProdutos = preencheIdsProdutos(idsProdutos);
					nomesProdutos = preencheNomesProdutos(nomesProdutos);
					precosProdutos = preenchePrecosProdutos(precosProdutos);
					estoquesProdutos = preencheEstoquesProdutos(estoquesProdutos);
					
					vendaAtualIds = iniciaVendaAtualIds();
					vendaAtualQuantidades = iniciaVendaAtualQuantidades();
					
					historicoIdsPedidos = iniciaHistoricoIdsPedidos();
					historicoValoresPedidos = iniciaHistoricoValoresPedidos();
					historicoItensVendidos = historicoItensVendidos();
					
					break;
				
				case "2":
					
					condBaseIniciada = verificaBaseInicializada(countNumBase, option);
					
					if (condBaseIniciada == true) {
						exibirCatalogoProdutos(nomesProdutos, estoquesProdutos);
						break;
					}
					else {
						break;
					}
			
				case "3":
					
					verificaBaseInicializada(countNumBase, option);
					
					break;
			
				case "4":
					
					verificaBaseInicializada(countNumBase, option);
	
					break;
			
				case "5":
					
					verificaBaseInicializada(countNumBase, option);
					
					break;
			
				case "6":
					
					verificaBaseInicializada(countNumBase, option);
	
					break;
			
				case "7":
					
					verificaBaseInicializada(countNumBase, option);
					
					break;
			
				case "8":
					
					verificaBaseInicializada(countNumBase, option);
					
					break;
			
				case "9":
					
					verificaBaseInicializada(countNumBase, option);
					
					break;
			
				case "0":
					System.out.println("\nFinalizando o programa... Volte sempre!");
					menu = false;
					break;
					
				
				default:
					exibeErroOpInvalid();
					break;
				}
				
		} while(menu);
		
		sc.close();

	}
	
// MENSAGENS DO PROGRAMA
	public static void showWelcomeMsg() {
		
		String banner = """
				~=~=~=~=~=~=~=~=~=~=~=~=~=
				~=~=~=~=~=~=~=~=~=~=~=~=~=
				BEM-VINDO(A) AO MACK SHOP
				~=~=~=~=~=~=~=~=~=~=~=~=~=
				~=~=~=~=~=~=~=~=~=~=~=~=~=\n
                """;
		
		System.out.println(banner);
		
	}
	
	
	public static void mainMenu() {
		
		System.out.println("1. Inicializar base");
		System.out.println("2. Exibir catálogo de produtos");
		System.out.println("3. Adicionar item à venda");
		System.out.println("4. Ver resumo da venda atual");
		System.out.println("5. Finalizar venda (gerar histórico e Nota Fiscal)");
		System.out.println("6. Ver histórico de vendas");
		System.out.println("7. Buscar venda específica do histórico");
		System.out.println("8. (Admin) Repor estoque");
		System.out.println("9. (Admin) Relatório de estoque baixo");
		System.out.println("0. Sair");
		System.out.print("-> ");
		
	}
	
	
	public static void exibeErroOpInvalid() {
		System.out.println("\n#########################\n");
		System.out.println("Insira uma opção válida!");
		System.out.println("\n#########################\n");
	}
	
	
	public static void exibeMsgBaseNaoInicializada() {
		System.out.println("\n######################################################################################");
		System.out.println("\nATENÇÂO! Você precisa inicializar a base de produtos para poder usar as outras opções!\n");
		System.out.println("######################################################################################\n");
	}
	
	
// ##########################################################################################################################


// FUNÇÕES PARA INICIALIZAR A BASE E VERIFICAR SE A BASE FOI INICIALIZADA
	
	public static boolean verificaBaseInicializada(int countNumBase, String op) {
		if (countNumBase == 0) {
			exibeMsgBaseNaoInicializada();
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public static int[] preencheIdsProdutos(int[] idsProdutos) {
		return idsProdutos = new int[] {1, 2, 3, 4, 5};
		}
	
	
	public static String[] preencheNomesProdutos(String[] nomesProdutos) {
		return nomesProdutos = new String[] {"IPhone 17", "JBL Charge 5", "TV", "Smart Watch", "PlayStation 5"};
	}
	
	
	public static double[] preenchePrecosProdutos(double[] precosProdutos) {
		return precosProdutos = new double[] {8000.00, 900.00, 1000.00, 1200.00, 3700.00};
	}
	
	
	public static int[] preencheEstoquesProdutos(int[] estoquesProdutos) {
			return estoquesProdutos = new int[] {1, 5, 10, 15, 8};
	}
	
	
	public static int[] iniciaVendaAtualIds() {
		int[] vendaAtualIds;
		return vendaAtualIds = new int[5];
	}
	
	
	public static int[] iniciaVendaAtualQuantidades() {
		int[] vendaAtualQuantidades;
		return vendaAtualQuantidades = new int[5];
	}
	
	
	public static int[] iniciaHistoricoIdsPedidos() {
		int[] historicoIdsPedidos;
		return historicoIdsPedidos = new int[5];
	}
	
	
	public static double[] iniciaHistoricoValoresPedidos() {
		double[] historicoValoresPedidos;
		return historicoValoresPedidos = null;
	}
	
	
	public static int[][] historicoItensVendidos() {
		int[][] historicoItensVendidos;
		return historicoItensVendidos = new int[41][3];
	}
// ##########################################################################################################################	

	public static void exibirCatalogoProdutos(String[] nomesProdutos, int[] estoquesProdutos) {
	    int n = nomesProdutos.length;
	    int[] indice = new int[n];
	    int k = 0;

	    for (int i = 0; i < n; i++) {
	        if (estoquesProdutos[i] > 0) {
	            indice[k++] = i;
	        }
	    }

	    for (int i = 0; i < k - 1; i++) {
	        int maior = i;
	        for (int j = i+1; j < k; j++) {
	            if (estoquesProdutos[indice[j]] > estoquesProdutos[indice[maior]]) {
	                maior = j;
	            }
	        }
	        int tmp = indice[i];
	        indice[i] = indice[maior];
	        indice[maior] = tmp;
	    }

	    System.out.println("\n========== CATÁLOGO DE PRODUTOS (ORDENADO POR ESTOQUE) ==========");
	    if (k == 0) {
	        System.out.println("(nenhum produto disponível em estoque)");
	    } else {
	        System.out.printf("%-4s  %-30s  %s%n", "#", "Produto", "Qtd.");
	        System.out.println("------------------------------------------------------");
	        for (int p = 0; p < k; p++) {
	            int i = indice[p];
	            System.out.printf("%-4d  %-30s  %d%n", (p + 1), nomesProdutos[i], estoquesProdutos[i]);
	        }
	    }
	    System.out.println("================================================================\n");
	}

}
