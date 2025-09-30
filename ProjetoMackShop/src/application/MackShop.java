
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
		int[] tempEstoquesProdutos = new int[] {};
		
		// VENDA ATUAL
		int[] vendaAtualIds = null;
		int[] vendaAtualQuantidades = null;
		double totalVendaAtual = 0.0;
		
		// HISTÓRICO DE VENDAS
		int[] historicoIdsPedidos = null;
		double[] historicoValoresPedidos = null;
		int[][] historicoItensVendidos = null;
		int idPedidos = 0;
		int contadorPedidos = 0;
		
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
						tempEstoquesProdutos = preencheEstoquesProdutos(tempEstoquesProdutos);
						
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
						exibirCatalogoProdutos(nomesProdutos, estoquesProdutos, tempEstoquesProdutos);
						pausaParaRetornarMenu(sc);
						break;
					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
			
				case "3":
					
					if (condBaseIniciada == true) {
						adicionarItemAVenda(sc, idsProdutos, tempEstoquesProdutos, vendaAtualIds, vendaAtualQuantidades);
					} else {
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
					break;
			
				case "5":
					
					if (condBaseIniciada == true) {
						idPedidos = finalizarVenda(sc, idPedidos, historicoIdsPedidos, historicoValoresPedidos, totalVendaAtual, vendaAtualIds, vendaAtualQuantidades, historicoItensVendidos, estoquesProdutos, tempEstoquesProdutos, idsProdutos, nomesProdutos, precosProdutos, contadorPedidos);
						contadorPedidos++;
						totalVendaAtual = 0.0;
					} else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					break;
			
				case "6":
					
					if (condBaseIniciada == true) {
						verHistoricoVendas(historicoItensVendidos, historicoValoresPedidos, historicoIdsPedidos, contadorPedidos);

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					//verificaBaseInicializada(base);
	
					break;
			
				case "7":
					
					if (condBaseIniciada == true) {
						buscarVendaEspecifica(sc, historicoItensVendidos, idsProdutos, nomesProdutos, precosProdutos, historicoValoresPedidos, historicoIdsPedidos, contadorPedidos);

					}
					
					else {
						exibeMsgBaseNaoInicializada();
						break;
					}
					
					//verificaBaseInicializada(base);
					
					break;
			
				case "8":
					if (condBaseIniciada == true) {
						reporEstoque(idsProdutos, estoquesProdutos, nomesProdutos, sc);
					} else {
						exibeMsgBaseNaoInicializada();
					}
					break;

				case "9":
					if (condBaseIniciada == true) {
						relatorioEstoqueBaixo(idsProdutos, nomesProdutos, estoquesProdutos, sc, 10);
					} else {
						exibeMsgBaseNaoInicializada();
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
		
String banner = "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "BEM-VINDO(A) AO MACK SHOP\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n\n                ";
		
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
		return historicoIdsPedidos = new int[1000]; // Aumentado para 1000 pedidos
	}
	
	
	public static double[] iniciaHistoricoValoresPedidos() {
		double[] historicoValoresPedidos;
		return historicoValoresPedidos = new double[1000]; // Aumentado para 1000 pedidos
	}
	
	
	public static int[][] historicoItensVendidos() {
		int[][] historicoItensVendidos;
		return historicoItensVendidos = new int[1000][3]; // Aumentado para 1000 itens para acomodar mais vendas
	}
	

// ##########################################################################################################################
// ##########################################################################################################################
	
// 2 - FUNÇÃO PARA EXIBIR CATÁLOGO DE PRODUTOS

	public static void exibirCatalogoProdutos(String[] nomesProdutos, int[] estoquesProdutos, int[] tempEstoquesProdutos) {
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

	public static void adicionarItemAVenda(Scanner sc, int[] idsProdutos, int[] tempEstoquesProdutos, int[] vendaAtualIds, int[] vendaAtualQuantidades) {
		exibeAdicionarItemVenda();
		
		System.out.print("Digite o ID do item que vc deseja comprar: ");
		int idItem = sc.nextInt();
		
		idItem = velidaçõesDeEntradaDoId(idsProdutos, idItem, tempEstoquesProdutos, sc);
		
		System.out.print("\nQuantos vc quer levar? ");
		int qtdItens = sc.nextInt();
		
		while (qtdItens > tempEstoquesProdutos[idItem - 1] || qtdItens <= 0) {
			
			System.out.println("\n=============================\n");
			System.out.println("Insira uma quantidade válida!");
			System.out.println("\n=============================");
			
			System.out.print("-> ");
			qtdItens = sc.nextInt();
		}
		
		for (int i=0; i<idsProdutos.length; i++) {
			
			if (idsProdutos[i] == idItem && tempEstoquesProdutos[i] > 0) {
				vendaAtualIds[i] = idItem;
				vendaAtualQuantidades[i] = qtdItens;
				tempEstoquesProdutos[i] -= qtdItens;
			}						
		}	
	}
	
	public static int velidaçõesDeEntradaDoId(int[] idsProdutos, int idItem, int[] estoquesProdutos, Scanner sc) {
	    int produtoIndex = -1;
	    boolean idValido = false;
	    while (!idValido) {
	        // Verifica se o ID está dentro do range válido
	        if (idItem > 0 && idItem <= idsProdutos.length) {
	            produtoIndex = idItem - 1; // Ajusta para índice base 0
	            // Verifica se o produto tem estoque
	            if (estoquesProdutos[produtoIndex] > 0) {
	                idValido = true;
	            } else {
	                System.out.println("\n=======================================================\n");
	                System.out.println("ATENÇÃO! O produto escolhido está em falta no estoque :(");
	                System.out.println("\n=======================================================\n");
	                System.out.print("Insira o ID de outro produto -> ");
	                idItem = sc.nextInt();
	            }
	        } else {
	            System.out.println("\n=====================================\n");
	            System.out.println("Insira um ID de produto existente (entre 1 e " + idsProdutos.length + ")!");
	            System.out.println("\n=====================================");
	            System.out.print("-> ");
	            idItem = sc.nextInt();
	        }
	    }
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
	
	public static void imprimirNotaFiscal(int pedidoId, double valorTotalPedido, int[] idsProdutosBase, String[] nomesProdutosBase, double[] precosProdutosBase, int[] itensVendaIds, int[] itensVendaQuantidades, Scanner sc) {
	    LocalDateTime agora = LocalDateTime.now();
	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String dataEmissao = agora.format(formatador);
	
	    System.out.println("\n*********************************************************************************************");
	    System.out.println("*                                MACKSHOP                                   *");
	    System.out.println("* CNPJ: 12.345.678/0001-99                                                  *");
	    System.out.println("*********************************************************************************************");
	    System.out.println("* NOTA FISCAL - VENDA AO CONSUMIDOR                                         *");
	    System.out.printf("* Pedido ID: %-8d                                                               *\n", pedidoId);
	    System.out.printf("* Data de Emissão: %-19s                                                         *\n", dataEmissao);
	    System.out.println("*********************************************************************************************");
	    System.out.println("* # | ID  | DESCRIÇÃO                    | QTD | VL. UNIT. | VL. TOTAL *");
	    System.out.println("-----------------------------------------------------------------------------------------------------------");
	
	    double subtotalNota = 0.0;
	    int contadorItens = 0;
	    for (int i = 0; i < itensVendaIds.length; i++) {
	        if (itensVendaIds[i] != 0) {
	            int idProduto = itensVendaIds[i];
	            int quantidade = itensVendaQuantidades[i];
	            String nomeProduto = "";
	            double precoUnitario = 0.0;
	            int indexProdutoBase = -1;

	            for (int j = 0; j < idsProdutosBase.length; j++) {
	                if (idsProdutosBase[j] == idProduto) {
	                    indexProdutoBase = j;
	                    nomeProduto = nomesProdutosBase[j];
	                    precoUnitario = precosProdutosBase[j];
	                    break;
	                }
	            }

	            if (indexProdutoBase != -1) {
	                double valorTotalItem = quantidade * precoUnitario;
	                subtotalNota += valorTotalItem;
	                contadorItens++;
	                System.out.printf("* %-2d| %-3d | %-26s | %-3d | R$ %-7.2f | R$ %-8.2f *\n",
	                                  contadorItens, idProduto, nomeProduto, quantidade, precoUnitario, valorTotalItem);
	            }
	        }
	    }
	
	    System.out.println("-----------------------------------------------------------------------------------------------------------");
	    System.out.printf("* VALOR TOTAL: R$ %-8.2f                                                              *\n", valorTotalPedido);
	    System.out.println("*********************************************************************************************\n");
	
	    pausaParaRetornarMenu(sc);
	}

	public static int finalizarVenda(Scanner sc, int idPedidos, int[] historicoIdsPedidos, double[] historicoValoresPedidos, double totalVendaAtual, int[] vendaAtualIds, int[] vendaAtualQuantidades, int[][] historicoItensVendidos, int[] estoquesProdutos, int[] tempEstoquesProdutos, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int contadorPedidos) {
		idPedidos += 1;
		historicoIdsPedidos[idPedidos-1] = idPedidos;
		historicoValoresPedidos[idPedidos-1] = totalVendaAtual;
		
		int qtdItensVendidos = 0;
		
		for (int i=0; i<vendaAtualIds.length; i++) 
			if (vendaAtualIds[i] != 0) qtdItensVendidos += 1; 	
		
		int historicoItemIndex = 0;
		for (int i = 0; i < vendaAtualIds.length; i++) {
			if (vendaAtualIds[i] != 0) {
				historicoItensVendidos[historicoItemIndex][0] = idPedidos;
				historicoItensVendidos[historicoItemIndex][1] = vendaAtualIds[i];
				historicoItensVendidos[historicoItemIndex][2] = vendaAtualQuantidades[i];
				historicoItemIndex++;
			}
		}
		
		for (int i = 0; i < estoquesProdutos.length; i++) {
			estoquesProdutos[i] = tempEstoquesProdutos[i];
		}
		
		int[] itensParaNotaIds = new int[vendaAtualIds.length];
		int[] itensParaNotaQuantidades = new int[vendaAtualQuantidades.length];
		System.arraycopy(vendaAtualIds, 0, itensParaNotaIds, 0, vendaAtualIds.length);
		System.arraycopy(vendaAtualQuantidades, 0, itensParaNotaQuantidades, 0, vendaAtualQuantidades.length);
		double valorTotalParaNota = totalVendaAtual;

		imprimirNotaFiscal(idPedidos, valorTotalParaNota, idsProdutos, nomesProdutos, precosProdutos, itensParaNotaIds, itensParaNotaQuantidades, sc);
		
		// Limpa os vetores da venda atual
		for (int i = 0; i < vendaAtualIds.length; i++) {
			vendaAtualIds[i] = 0;
			vendaAtualQuantidades[i] = 0;
		}
		
		return idPedidos;
	}
	
// ##########################################################################################################################
// ##########################################################################################################################

// 6 - FUNÇÕES PARA VER HISTÓRICO DE VENDAS

	public static void verHistoricoVendas(int[][] historicoItensVendidos, double[] historicoValoresPedidos, int[] historicoIdsPedidos, int contadorPedidos) {
	    System.out.println("\n========== HISTÓRICO DE VENDAS ==========");
	    if (contadorPedidos == 0) {
	      System.out.println("(nenhuma venda registrada)");
	    } else {
	        System.out.printf("%-10s | %-15s%n", "ID Pedido", "Valor Total");
	        System.out.println("---------------------------------");
	        for (int i = 0; i < contadorPedidos; i++) {
	            System.out.printf("%-10d | R$ %-15.2f%n", historicoIdsPedidos[i], historicoValoresPedidos[i]);
	        }
	    }
	    System.out.println("======================================\n");
	}

	
// ##########################################################################################################################
// ##########################################################################################################################

// 7 - FUNÇÕES PARA BUSCAR VENDA ESPECÍFICA

	public static void buscarVendaEspecifica(Scanner sc, int[][] historicoItensVendidos, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, double[] historicoValoresPedidos, int[] historicoIdsPedidos, int contadorPedidos) {
	    System.out.print("Digite o ID do pedido que deseja buscar: ");
	    int idBusca = sc.nextInt();
	    sc.nextLine(); // Limpar o buffer

	    int pedidoIndex = -1;
	    for (int i = 0; i < contadorPedidos; i++) {
	        if (historicoIdsPedidos[i] == idBusca) {
	            pedidoIndex = i;
	            break;
	        }
	    }

	    if (pedidoIndex != -1) {
	        System.out.printf("\nDETALHES DO PEDIDO %d%n", idBusca);
	        System.out.println("---------------------------------");
	        System.out.printf("Valor Total: R$ %.2f%n", historicoValoresPedidos[pedidoIndex]);
	        System.out.println("Itens:");

	        for (int i = 0; i < historicoItensVendidos.length; i++) {
	            if (historicoItensVendidos[i][0] == idBusca) {
	                int idProduto = historicoItensVendidos[i][1];
	                int quantidade = historicoItensVendidos[i][2];
	                String nomeProduto = "";
	                double precoUnitario = 0.0;

	                for (int j = 0; j < idsProdutos.length; j++) {
	                    if (idsProdutos[j] == idProduto) {
	                        nomeProduto = nomesProdutos[j];
	                        precoUnitario = precosProdutos[j];
	                        break;
	                    }
	                }
	                System.out.printf("- %s (Qtd: %d) - Subtotal: R$ %.2f%n", nomeProduto, quantidade, (quantidade * precoUnitario));
	            }
	        }
	    } else {
	        System.out.println("\nPedido não encontrado!\n");
	    }
	}

	
// ##########################################################################################################################
// ##########################################################################################################################

// 8 - FUNÇÕES PARA REPOR ESTOQUE

	public static void reporEstoque(int[] idsProdutos, int[] estoquesProdutos, String[] nomesProdutos, Scanner sc) {
	    System.out.print("Digite o ID do produto para repor o estoque: ");
	    int idProduto = sc.nextInt();
	    sc.nextLine(); // Limpar o buffer

	    int produtoIndex = -1;
	    for (int i = 0; i < idsProdutos.length; i++) {
	        if (idsProdutos[i] == idProduto) {
	            produtoIndex = i;
	            break;
	        }
	    }

	    if (produtoIndex != -1) {
	        System.out.printf("Estoque atual de %s: %d%n", nomesProdutos[produtoIndex], estoquesProdutos[produtoIndex]);
	        System.out.print("Digite a quantidade a ser adicionada: ");
	        int quantidade = sc.nextInt();
	        sc.nextLine(); // Limpar o buffer

	        if (quantidade > 0) {
	            estoquesProdutos[produtoIndex] += quantidade;
	            System.out.printf("Novo estoque de %s: %d%n", nomesProdutos[produtoIndex], estoquesProdutos[produtoIndex]);
	        } else {
	            System.out.println("Quantidade inválida!");
	        }
	    } else {
	        System.out.println("Produto não encontrado!");
	    }
	}

	
// ##########################################################################################################################
// ##########################################################################################################################

// 9 - FUNÇÕES PARA RELATÓRIO DE ESTOQUE BAIXO

	public static void relatorioEstoqueBaixo(int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos, Scanner sc, int limite) {
	    System.out.printf("\nRELATÓRIO DE ESTOQUE BAIXO (limite: %d)%n", limite);
	    System.out.println("---------------------------------");
	    boolean encontrou = false;
	    for (int i = 0; i < idsProdutos.length; i++) {
	        if (estoquesProdutos[i] < limite) {
	            System.out.printf("- %s (ID: %d) - Estoque: %d%n", nomesProdutos[i], idsProdutos[i], estoquesProdutos[i]);
	            encontrou = true;
	        }
	    }
	    if (!encontrou) {
	        System.out.println("(nenhum produto com estoque baixo)");
	    }
	    System.out.println();
	}
}


