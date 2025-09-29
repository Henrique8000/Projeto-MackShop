/*
	Henrique Flávio Guimarães / 10427920
	Fabrício Nicolini Barros  / 10437001
	Andreas Caycedo Martinez  / 10435302
	Rafael Viola  			  / 10737197
 */


package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MackShop {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//TODO: Contadores de quantidades
		
		// VARIÁVEIS DE CONTROLE
		boolean menu = true;
		boolean condBaseIniciada = false;
		
		//CATALOGO DE PRODUTOS
		int[] idsProdutos = new int[] {};
		String[] nomesProdutos = new String[] {};
		double[] precosProdutos = new double[] {};
		int[] estoquesProdutos = new int[] {};
		
		// VENDA ATUAL
		int[] vendaAtualIds = null;
		int[] vendaAtualQuantidades = null;
		double totalVendaAtual = 0.0;
		
		// HISTÓRICO DE VENDAS
		int[] historicoIdsPedidos = null;
		double[] historicoValoresPedidos = null;
		int[][] historicoItensVendidos = null;
		int idPedidos = 0;
		
		showWelcomeMsg();
		
		do {
			
			mainMenu();
			
			String option = sc.nextLine();
			
			switch (option) { 
				case "1":
					
					if (condBaseIniciada == false) {	
						
						idsProdutos = preencheIdsProdutos(idsProdutos);
						nomesProdutos = preencheNomesProdutos(nomesProdutos);
						precosProdutos = preenchePrecosProdutos(precosProdutos);
						estoquesProdutos = preencheEstoquesProdutos(estoquesProdutos);
						
						vendaAtualIds = iniciaVendaAtualIds();
						vendaAtualQuantidades = iniciaVendaAtualQuantidades();
						
						historicoIdsPedidos = iniciaHistoricoIdsPedidos();
						historicoValoresPedidos = iniciaHistoricoValoresPedidos();
						historicoItensVendidos = historicoItensVendidos();
						
						condBaseIniciada = true;
						
						exibeMsgBaseInicializada();
						pausaParaRetornarMenu(sc);
					}
					else {
						exibeMsgBaseJaEstaInicializada();
					}
					
					break;
				
				case "2":
					
					if (condBaseIniciada == true) {
						exibirCatalogoProdutos(nomesProdutos, estoquesProdutos);
						pausaParaRetornarMenu(sc);
						break;
					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
			
				case "3":
					
					if (condBaseIniciada == true) {
						
						exibeAdicionarItemVenda();
						
						System.out.print("Digite o ID do item que vc deseja comprar: ");
						int countId = 0;
						
						int idItem = sc.nextInt();
						
						idItem = velidaçõesDeEntradaDoId(idsProdutos, idItem, estoquesProdutos, sc, countId);
						
						System.out.print("\nQuantos vc quer levar? ");
						int qtdItens = sc.nextInt();
						
						//TODO: Validação se a quantidade que o cliente quer levar condiz com oq tem em estoque 
						while (qtdItens > estoquesProdutos[idItem - 1] || qtdItens <= 0) {
							
							System.out.println("\n=============================\n");
							System.out.println("Insira uma quantidade válida!");
							System.out.println("\n=============================");
							
							System.out.print("-> ");
							qtdItens = sc.nextInt();
						}
						
						for (int i=0; i<idsProdutos.length; i++) {
							
							if (idsProdutos[i] == idItem && estoquesProdutos[i] > 0) {
								vendaAtualIds[i] = idItem;
								vendaAtualQuantidades[i] = qtdItens;
								//estoquesProdutos[i] -= qtdItens;
							}						
						}
						
					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					sc.nextLine();
					pausaParaRetornarMenu(sc);
					
					break;
			
				case "4":
					
					if (condBaseIniciada == true) {
						totalVendaAtual = verResumoTotalDaVenda(totalVendaAtual, sc, vendaAtualIds, vendaAtualQuantidades, precosProdutos, nomesProdutos);
					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					//verificaBaseInicializada(base);
					break;
			
				case "5":
					
					if (condBaseIniciada == true) {
						idPedidos += 1;
						historicoIdsPedidos[idPedidos-1] = idPedidos;
						historicoValoresPedidos[idPedidos-1] = totalVendaAtual;
						
						int qtdItensVendidos = 0;
						
						for (int i=0; i<vendaAtualIds.length; i++) 
							if (vendaAtualIds[i] != 0) qtdItensVendidos += 1; 	
						
						for (int l=0; l<historicoItensVendidos.length; l++) {
							if (l <= qtdItensVendidos) {
								for (int c=0; c<historicoItensVendidos[l].length; c++) {
									
									if(c == 0) {
										historicoItensVendidos[l][c] = historicoIdsPedidos[l];
									}
									
									if (c == 1) {
										historicoItensVendidos[l][c] = idsProdutos[l];
									}
									
									if (c == 2) {
										historicoItensVendidos[l][c] = vendaAtualQuantidades[l];
									}
									
								}
							}
						}
						
						for (int i=0; i<vendaAtualQuantidades.length; i++) {
							if (vendaAtualQuantidades[i] != 0) estoquesProdutos[i] -= vendaAtualQuantidades[i];	
						}
						

						imprimirNotaFiscal(idPedidos, totalVendaAtual, idsProdutos, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos, sc);
						
						vendaAtualIds = new int[100];
						vendaAtualQuantidades = new int[100];
						
					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					break;
			
				case "6":
					
					if (condBaseIniciada == true) {
						verHistoricoVendas();

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					//verificaBaseInicializada(base);
	
					break;
			
				case "7":
					
					if (condBaseIniciada == true) {
						

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					//verificaBaseInicializada(base);
					
					break;
			
				case "8":
					
					if (condBaseIniciada == true) {
						

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					break;
			
				case "9":
					
					if (condBaseIniciada == true) {
						exibeMsgBaseNaoInicializada();

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
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
	
	
	public static void exibeMsgBaseInicializada() {
		System.out.println("\n######################\n");
		System.out.println("  BASE INICIALIZADA");
		System.out.println("\n######################\n");
	}
	
	
	public static void exibeMsgBaseJaEstaInicializada() {
		System.out.println("\n#######################################\n");
		System.out.println("  ATENÇÃO! A BASE JÁ FOI INICIALIZADA");
		System.out.println("\n#######################################\n");
	}
	
	
	public static void pausaParaRetornarMenu(Scanner sc) {
		System.out.print("\nDigite algo para voltar ao menu...");
		sc.nextLine();
		System.out.println();
	}
	
	
	public static void exibeAdicionarItemVenda() {
		System.out.println("\n++++++++++++++++++++++\n");
		System.out.println("ADICIONAR ITEM A VENDA");
		System.out.println("\n++++++++++++++++++++++\n");
	}
	
// ##########################################################################################################################


// 1 - FUNÇÕES PARA INICIALIZAR A BASE E VERIFICAR SE A BASE FOI INICIALIZADA
	
	public static boolean verificaBaseInicializada(boolean base) {
		if (!base) {
			return base;
		}
		else {
			exibeMsgBaseNaoInicializada();
			return base;
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
		return vendaAtualIds = new int[30];
	}
	
	
	public static int[] iniciaVendaAtualQuantidades() {
		int[] vendaAtualQuantidades;
		return vendaAtualQuantidades = new int[30];
	}
	
	
	public static int[] iniciaHistoricoIdsPedidos() {
		int[] historicoIdsPedidos;
		return historicoIdsPedidos = new int[30];
	}
	
	
	public static double[] iniciaHistoricoValoresPedidos() {
		double[] historicoValoresPedidos;
		return historicoValoresPedidos = new double[30];
	}
	
	
	public static int[][] historicoItensVendidos() {
		int[][] historicoItensVendidos;
		return historicoItensVendidos = new int[30][3];
	}
	

// ##########################################################################################################################
// ##########################################################################################################################
	
// 2 - FUNÇÃO PARA EXIBIR CATÁLOGO DE PRODUTOS

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
	
// ##########################################################################################################################
// ##########################################################################################################################
	
// 3 - FUNÇÕES PARA ADICIONAR ITEM À VENDA

	public static int verificaSeIdExiste(int idItem, Scanner sc, int[] idsProdutos, int countId) {
		
		while (idItem < 1 || idItem > 5) {
			System.out.println("\n=====================================\n");
			System.out.println("Insira um id de um produto existente!");
			System.out.println("\n=====================================");
			
			System.out.print("-> ");
			idItem = sc.nextInt();
			
			for (int j=0; j<idsProdutos.length; j++) {
				if (idItem == idsProdutos[j]) countId += 1;	
			}
		}
		return idItem;
	}
	
	public static int verificaSeTemOProdutoNoEstoque(int[] idsProdutos, int idItem, int[] estoquesProdutos, Scanner sc) {
		for (int i=0; i<idsProdutos.length; i++) {
			if (idsProdutos[idItem-1] == idItem) {
				while (estoquesProdutos[idItem-1] <= 0) {
					
					System.out.println("\n=======================================================\n");
					System.out.println("ATENÇÃO! O produto escolhido está em falta no estoque :(");
					System.out.println("\n=======================================================\n");
						
					System.out.print("Insira o id de outro produto -> ");
					idItem = sc.nextInt();
						
				}
			}
		}
		
		return idItem;
	}
	
	public static int velidaçõesDeEntradaDoId(int[] idsProdutos, int idItem, int[] estoquesProdutos, Scanner sc, int countId) {
		idItem = verificaSeIdExiste(idItem, sc, idsProdutos, countId);
		idItem = verificaSeTemOProdutoNoEstoque(idsProdutos, idItem, estoquesProdutos, sc);
		return idItem;
	}
	
// ##########################################################################################################################
// ##########################################################################################################################

// 4 - FUNÇÃO PARA VER RESUMO TOTAL DA VENDA
	
	public static double verResumoTotalDaVenda(double totalVendaAtual,Scanner sc, int[] vendaAtualIds, int[] vendaAtualQuantidades, double[] precosProdutos, String[] nomesProdutos) {
		totalVendaAtual = 0.0;
		double subtotal = 0.0;
		System.out.println("\nRESUMO DA VENDA ATUAL\n");
		
		System.out.println("ITENS:");
		
		
		for (int i=0; i<vendaAtualIds.length; i++) {
			subtotal = 0.0;
			if (vendaAtualIds[i] != 0) {
				subtotal += vendaAtualQuantidades[i] * precosProdutos[i]; 
				System.out.printf("%s -> subtotal: R$%.2f%n", nomesProdutos[i], subtotal);	
			}
			
			totalVendaAtual += subtotal;
		}
		
		System.out.printf("%nTotal da venda atual: R$%.2f%n", totalVendaAtual);
		
		System.out.println("===============================");
		
		pausaParaRetornarMenu(sc);
		
		return totalVendaAtual;
		
	}

	
// ##########################################################################################################################
// ##########################################################################################################################
	
// 5 - FUNÇÕES PARA FINALIZAR VENDA
	
	public static void imprimirNotaFiscal(int idPedidos, double totalVendaAtual, int[] idsProdutos, int[] vendaAtualQuantidades, int[] vendaAtualIds, String[] nomesProdutos, double[] precosProdutos, Scanner sc) {
	    LocalDateTime agora = LocalDateTime.now();
	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String dataEmissao = agora.format(formatador);
	
	    System.out.println("\n*********************************************************************************************");
	    System.out.println("*                                MACKSHOP                                   *");
	    System.out.println("* CNPJ: 12.345.678/0001-99                                                  *");
	    System.out.println("*********************************************************************************************");
	    System.out.println("* NOTA FISCAL - VENDA AO CONSUMIDOR                                         *");
	    System.out.printf("* Pedido ID: %-8s                                                                *\n", idPedidos);
	    System.out.printf("* Data de Emissão: %-19s                                                         *\n", dataEmissao);
	    System.out.println("*********************************************************************************************");
	    System.out.println("* # | ID  | DESCRIÇÃO                    | QTD | VL. UNIT. | VL. TOTAL *");
	    System.out.println("-----------------------------------------------------------------------------------------------------------");
	
	    double subtotalNota = 0;
	    for (int i = 0; i < vendaAtualIds.length; i++) {
	    	if(vendaAtualIds[i] > 0) {  
		        int idProdutoNota = vendaAtualIds[i];
		        int quantidadeNota = vendaAtualQuantidades[i];
		
		        int indiceProduto = -1;
		        for (int j = 0; j < idsProdutos.length; j++) {
		            if (idsProdutos[j] == idProdutoNota) {
		                indiceProduto = j;
		                break;
		            }
		
		        if (indiceProduto != -1) {
		            String nomeProduto = nomesProdutos[indiceProduto];
		            double precoUnitario = precosProdutos[indiceProduto];
		            double valorTotalItem = precoUnitario * quantidadeNota;
		            subtotalNota = valorTotalItem;
		            //TODO: não está imprimindo os produtos
		            System.out.printf("* %-1d | %-3d | %-26s | %-3d | R$ %-7.2f | R$ %-7.2f *\n", (i + 1), idProdutoNota, nomeProduto, quantidadeNota, precoUnitario, valorTotalItem);
		        	}
		        
		        }
	    	}
	    }
	    System.out.println("-----------------------------------------------------------------------------------------------------------");
	    System.out.printf("* %-75s | R$ %-7.2f *\n", "SUBTOTAL", subtotalNota);
	    System.out.printf("* %-75s | R$ %-7.2f *\n", "TOTAL", totalVendaAtual);
	    System.out.println("*********************************************************************************************");
	    System.out.println("* OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!                                  *");
	    System.out.println("*********************************************************************************************\n");
	    
	    pausaParaRetornarMenu(sc);
	}

// ##########################################################################################################################
// ##########################################################################################################################
	
    public static void verHistoricoVendas() {
    	
		int[] historicoIdsPedidos = new int[100];
		double[] historicoValoresPedidos = new double[100];
        int[][] historicoItensVendidos = new int[500][3]; 
        int contadorHistoricoPedidos = 0;
        int proximoIdPedido = 1001;
        int contadorHistoricoItens = 0;

    	
    	if (contadorHistoricoPedidos == 0) {
    		System.out.println("\nNenhum histórico de vendas disponível.\n");
    	}
    	else {
            System.out.println("\n****************************************");
            System.out.println("*        HISTÓRICO DE VENDAS         *");
            System.out.println("****************************************");
            
            for (int i = 0; i < contadorHistoricoPedidos; i++) {
                System.out.printf("Pedido ID: %-5d - Valor Total: R$ %-7.2f\n", historicoIdsPedidos[i], historicoValoresPedidos[i]);
            }
            
            System.out.println("****************************************");
    	}


    }
    
 // ##########################################################################################################################
 // ##########################################################################################################################
    
	public static void reporEstoque(int[] idsProdutos,int[] estoquesProdutos, Scanner sc){
		
		System.out.println("informe o id do produto: ");
		int id = sc.nextInt();

		while(id < 1 && id>5){
			System.out.println("id inválido");
			id = sc.nextInt();		
		}

		boolean idValido = false;
        for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == id) {
                idValido = true;
                break;
            }
        }

        while (!idValido) {
            System.out.println("ID inválido, tente novamente: ");
            id = sc.nextInt();
            for (int i = 0; i < idsProdutos.length; i++) {
                if (idsProdutos[i] == id) {
                    idValido = true;
                    break;
                }
            }
        }

		System.out.println("digite a quantidade: ");
		int qtd = sc.nextInt();

		while (qtd <= 0 ) {
			System.out.println("quantidade inválida");
			qtd = sc.nextInt();
		}

		 for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == id) {
                estoquesProdutos[i] += qtd;
                System.out.println(" Produto " + id + " atualizado! Agora tem " + estoquesProdutos[i] + " unidades.");
                break;
            }
        }

		
	}
	
	public static void buscarVendaEspecifica(Scanner sc, int[][] historicoItensVendidos, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, double[] historicoValoresPedidos, int[] historicoIdsPedidos, int[] vendaAtualQuantidades, int idPedidos, double totalVendaAtual) {
		System.out.print("\nDigite o ID do pedido que deseja buscar: ");
		int idBusca = sc.nextInt();
		sc.nextLine();
	    
	    // Verifica se o ID existe
	    boolean existePedido = false;
	    int indiceHistorico = -1;
	    for (int i = 0; i < historicoIdsPedidos.length; i++) {
	        if (historicoIdsPedidos[i] == idBusca) {
	            existePedido = true;
	            indiceHistorico = i;
	            break;
	        }
	    }
	    
	    if (!existePedido) {
	        System.out.println("\nPedido não encontrado no histórico!");
	        return;
	    }
	    
	    // Contar itens do pedido
	    int numItensPedido = 0;
	    for (int i = 0; i < historicoItensVendidos.length; i++) {
	        if (historicoItensVendidos[i][0] == idBusca) {
	            numItensPedido++;
	        }
	    }
	    
	    if (numItensPedido == 0) {
	        System.out.println("\nNenhum item registrado para este pedido!");
	        return;
	    }
	    
	    int[] vendaIds = new int[numItensPedido];
	    int[] vendaQuantidades = new int[numItensPedido];
	    
	    int contador = 0;
	    for (int i = 0; i < historicoItensVendidos.length; i++) {
	        if (historicoItensVendidos[i][0] == idBusca) {
	            vendaIds[contador] = historicoItensVendidos[i][1];      
	            vendaQuantidades[contador] = historicoItensVendidos[i][2]; 
	            contador++;
	        }
	    }
	    
	    // Imprime a nota fiscal do pedido
	    imprimirNotaFiscal(idPedidos, totalVendaAtual, idsProdutos, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos, sc);
	}
}
