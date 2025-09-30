package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MackShop {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
     // VARIÁVEIS DE CONTROLE
        boolean menu = true;
        boolean condBaseIniciada = false;

		//CATALOGO DE PRODUTOS
        int[] idsProdutos = new int[]{};
        String[] nomesProdutos = new String[]{};
        double[] precosProdutos = new double[]{};
        int[] estoquesProdutos = new int[]{};   
        int[] tempEstoquesProdutos = new int[]{};      

		// VENDA ATUAL
        int[] vendaAtualIds = null;         
        int[] vendaAtualQuantidades = null;
        double totalVendaAtual = 0.0;

		// HISTÓRICO DE VENDAS
        int[] historicoIdsPedidos = null;           
        double[] historicoValoresPedidos = null;     
        int[][] historicoItensVendidos = null;       
        int proximoIdPedido = 1000;

        exibirBoasVindas();

        do {
            exibirMenuPrincipal();
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1": 
                    if (condBaseIniciada = false) {
                    	idsProdutos = inicializarIdsProdutos();
                        nomesProdutos = inicializarNomesProdutos();
                        precosProdutos = inicializarPrecosProdutos();
                        estoquesProdutos = inicializarEstoquesProdutos();
                        tempEstoquesProdutos = inicializarEstoquesProdutos();

                        vendaAtualIds = inicializarVetorVendaIds();
                        vendaAtualQuantidades = inicializarVetorVendaQuantidades();

                        historicoIdsPedidos = inicializarHistoricoIdsPedidos();
                        historicoValoresPedidos = inicializarHistoricoValoresPedidos();
                        historicoItensVendidos = inicializarHistoricoItensVendidos();

                        condBaseIniciada = true;
                        
                        exibirMensagemBaseInicializada();
                        pausaParaRetornarMenu(sc);
                       
                    } else {
                    	 exibirMensagemBaseJaInicializada();
                    }
                    break;

                case "2":
                	
                    if (condBaseIniciada == true) {
                        exibirCatalogoOrdenadoPorEstoque(idsProdutos, nomesProdutos, estoquesProdutos);
                        pausaParaRetornarMenu(sc);
                    }
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
                    
                    break;

                case "3":
                    
                    if (condBaseIniciada == true) {
                        adicionarItemAVenda(sc, idsProdutos, nomesProdutos, precosProdutos, tempEstoquesProdutos, vendaAtualIds, vendaAtualQuantidades);
                        pausaParaRetornarMenu(sc);
                        break;
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
                    

                case "4":
                	
                    if (condBaseIniciada == true) {
                        totalVendaAtual = calcularEExibirResumoDaVendaAtual(vendaAtualIds, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos);
                        pausaParaRetornarMenu(sc);
                        break;
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
                    

                case "5":
                	
                    if (condBaseIniciada == true) {
                    	
                        if (vendaVazia(vendaAtualIds)) {
                            System.out.println("\nNenhum item na venda atual.");
                            break;
                        }
                        
                        proximoIdPedido = proximoIdPedido + 1;
                        int idPedidoGerado = proximoIdPedido;

                        totalVendaAtual = calcularEExibirResumoDaVendaAtual(vendaAtualIds, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos);

                        registrarHistoricoDaVenda(idPedidoGerado, totalVendaAtual, vendaAtualIds, vendaAtualQuantidades, historicoIdsPedidos, historicoValoresPedidos, historicoItensVendidos);

                        copiarConteudo(tempEstoquesProdutos, estoquesProdutos);

                        imprimirNotaFiscal(idPedidoGerado, vendaAtualIds, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos, totalVendaAtual);

                        limparVendaAtual(vendaAtualIds, vendaAtualQuantidades);
                        copiarConteudo(estoquesProdutos, tempEstoquesProdutos);
                        
                        pausaParaRetornarMenu(sc);
                        break;
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();

                        break;
                    }
                    
                case "6":
                	
                    if (condBaseIniciada == true) {
                    	
                        exibirHistoricoDeVendas(historicoIdsPedidos, historicoValoresPedidos);
                        pausaParaRetornarMenu(sc);
                        break;
                        
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
     
                case "7":
                    if (condBaseIniciada == true) {
                        buscarEReimprimirVenda(sc, historicoItensVendidos, historicoIdsPedidos, historicoValoresPedidos, idsProdutos, nomesProdutos, precosProdutos);
                        pausaParaRetornarMenu(sc);
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }                   

                case "8":                    
                    if (condBaseIniciada == true) {
                        reporEstoque(sc, idsProdutos, nomesProdutos, estoquesProdutos);
                        copiarConteudo(estoquesProdutos, tempEstoquesProdutos);
                        pausaParaRetornarMenu(sc);
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
            

                case "9":                   
                    if (condBaseIniciada == true) {
                        emitirRelatorioEstoqueBaixo(idsProdutos, nomesProdutos, estoquesProdutos, 10);
                        pausaParaRetornarMenu(sc);
                    } 
                    else {
                    	exibeMsgBaseNaoInicializada();
                        break;
                    }
                 

                case "0":
                    System.out.println("\nFinalizando o programa. Volte sempre!");
                    menu = false;
                    break;

                default:
                    exibirErroOpcaoInvalida();
            }
            
        } while (menu);

        sc.close();
    }

    
    
// ##########################################################################################################################
// ##########################################################################################################################

// MENSAGENS DO PROGRAMA

    public static void exibirBoasVindas() {
        String banner =
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "BEM-VINDO(A) AO MACK SHOP\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n" +
                "~=~=~=~=~=~=~=~=~=~=~=~=~=\n";
        System.out.println(banner);
    }

    public static void exibirMenuPrincipal() {
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

    public static void exibirErroOpcaoInvalida() {
        System.out.println("\n#########################\n");
        System.out.println("Insira uma opção válida!");
        System.out.println("\n#########################\n");
    }

    public static void exibeMsgBaseNaoInicializada() {
    	System.out.println("\n######################################################################################");
    	System.out.println("\nATENÇÃO! Você precisa inicializar a base de produtos para poder usar as outras opções!\n");
    	System.out.println("######################################################################################\n");
    }

    public static void exibirMensagemBaseInicializada() {
        System.out.println("\n######################\n");
        System.out.println("  BASE INICIALIZADA");
        System.out.println("\n######################\n");
    }

    public static void exibirMensagemBaseJaInicializada() {
        System.out.println("\n#######################################\n");
        System.out.println("  ATENÇÃO! A BASE JÁ FOI INICIALIZADA");
        System.out.println("\n#######################################\n");
    }

    public static void pausaParaRetornarMenu(Scanner sc) {
        System.out.print("\nDigite algo para voltar ao menu.");
        sc.nextLine();
        System.out.println();
    }
    
// ##########################################################################################################################
// ##########################################################################################################################

 // 1 - FUNÇÕES PARA INICIALIZAR A BASE E VERIFICAR SE A BASE FOI INICIALIZADA
    
    public static int[] inicializarIdsProdutos() {
        return new int[]{101, 203, 401, 555, 777};
    }

    public static String[] inicializarNomesProdutos() {
        return new String[]{"Mouse Gamer", "Teclado Mecânico", "Headset 7.1", "Smart Watch", "PlayStation 5"};
    }

    public static double[] inicializarPrecosProdutos() {
        return new double[]{150.00, 350.00, 420.50, 1200.00, 3700.00};
    }

    public static int[] inicializarEstoquesProdutos() {
        return new int[]{8, 12, 5, 15, 7};
    }

    public static int[] inicializarVetorVendaIds() {
        return new int[100]; // capacidade razoável sem vetor dinâmico
    }

    public static int[] inicializarVetorVendaQuantidades() {
        return new int[100];
    }

    public static int[] inicializarHistoricoIdsPedidos() {
        return new int[100];
    }

    public static double[] inicializarHistoricoValoresPedidos() {
        return new double[100];
    }

    public static int[][] inicializarHistoricoItensVendidos() {
        return new int[1000][3]; // até 1000 linhas de itens vendidos
    }

// ##########################################################################################################################
// ##########################################################################################################################

// 2 - FUNÇÃO PARA EXIBIR CATÁLOGO DE PRODUTOS
    public static void exibirCatalogoOrdenadoPorEstoque(int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos) {
        int n = nomesProdutos.length;
        int[] indices = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (estoquesProdutos[i] > 0) {
                indices[k] = i;
                k = k + 1;
            }
        }
        // selection sort nos k primeiros índices (algo simples, não avançado)
        for (int i = 0; i < k - 1; i++) {
            int maior = i;
            for (int j = i + 1; j < k; j++) {
                if (estoquesProdutos[indices[j]] > estoquesProdutos[indices[maior]]) {
                    maior = j;
                }
            }
            int tmp = indices[i];
            indices[i] = indices[maior];
            indices[maior] = tmp;
        }

        System.out.println("\n========== CATÁLOGO DE PRODUTOS (ESTOQUE > 0) ==========");
        if (k == 0) {
            System.out.println("(nenhum produto disponível em estoque)");
        } else {
            System.out.printf("%-4s  %-6s  %-30s  %-10s%n", "#", "ID", "Produto", "Qtd.");
            System.out.println("---------------------------------------------------------------");
            for (int p = 0; p < k; p++) {
                int i = indices[p];
                System.out.printf("%-4d  %-6d  %-30s  %-10d%n", (p + 1), idsProdutos[i], nomesProdutos[i], estoquesProdutos[i]);
            }
        }
        System.out.println("===============================================================\n");
    }

    
// ##########################################################################################################################
// ##########################################################################################################################
    
// 3 - FUNÇÕES PARA ADICIONAR ITEM À VENDA
    
    public static void adicionarItemAVenda(Scanner sc, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoqueTempParaVenda, int[] vendaAtualIds, int[] vendaAtualQuantidades) {
        
    	System.out.println("\n++++++++++++++++++++++\nADICIONAR ITEM À VENDA\n++++++++++++++++++++++\n");

        System.out.print("Digite o ID do produto: ");
        int idInformado = sc.nextInt();
        sc.nextLine();
        int indiceProduto = indiceDoProduto(idsProdutos, idInformado);

        System.out.printf("\nQuantidade de '%s' disponível agora: %d\n",
                nomesProdutos[indiceProduto], estoqueTempParaVenda[indiceProduto]);

        System.out.print("Quantos você quer levar? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        
        vendaAtualIds[indiceProduto] = idInformado;
        vendaAtualQuantidades[indiceProduto] = vendaAtualQuantidades[indiceProduto] + qtd;
        estoqueTempParaVenda[indiceProduto] = estoqueTempParaVenda[indiceProduto] - qtd;

        System.out.printf("\nAdicionado: %d x %s\n", qtd, nomesProdutos[indiceProduto]);
    }

    public static int lerIdValido(Scanner sc, int[] idsProdutos) {
        System.out.print("Digite o ID do produto: ");
        while (true) {
            int id = sc.nextInt();
            sc.nextLine();
            if (indiceDoProduto(idsProdutos, id) != -1) {
                return id;
            }
            System.out.print("Produto não existe. Digite novamente: ");
        }
    }

    public static int lerQuantidadeValida(Scanner sc, int maxDisponivel) {
        while (true) {
            int qtd = sc.nextInt();
            sc.nextLine();
            if (qtd > 0 && qtd <= maxDisponivel) {
                return qtd;
            }
            System.out.print("Quantidade fora do limite. Digite novamente: ");
        }
    }

    public static int indiceDoProduto(int[] idsProdutos, int id) {
        for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == id) {
                return i;
            }
        }
        return -1;
    }



// ##########################################################################################################################
// ##########################################################################################################################

// 4 - FUNÇÃO PARA VER RESUMO TOTAL DA VENDA

    public static boolean vendaVazia(int[] vendaAtualIds) {
        for (int v : vendaAtualIds) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }

    public static double calcularEExibirResumoDaVendaAtual(int[] vendaAtualIds, int[] vendaAtualQuantidades, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos) {
        
    	double total = 0.0;
        System.out.println("\nRESUMO DA VENDA ATUAL\n");
        System.out.println("* # | ID   | DESCRIÇÃO                 | QTD | VL. UNIT. | VL. TOTAL");
        System.out.println("-------------------------------------------------------------------");
        int contador = 1;
        
        for (int i = 0; i < vendaAtualIds.length && i < idsProdutos.length; i++) {
            if (vendaAtualIds[i] != 0) {
                double vlItem = precosProdutos[i] * vendaAtualQuantidades[i];
                total = total + vlItem;
                System.out.printf("* %d | %-4d | %-24s | %3d | R$ %8.2f | R$ %8.2f%n",contador, vendaAtualIds[i], nomesProdutos[i], vendaAtualQuantidades[i], precosProdutos[i], vlItem);
                contador = contador + 1;
            }
        }
        
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("Subtotal/Total: R$ %.2f%n", total);
        return total;
    }

    
// ##########################################################################################################################
// ##########################################################################################################################
    
// 5 - FUNÇÕES PARA FINALIZAR VENDA
    
    public static void registrarHistoricoDaVenda(int idPedido, double totalVenda, int[] vendaAtualIds, int[] vendaAtualQuantidades, int[] historicoIdsPedidos, double[] historicoValoresPedidos, int[][] historicoItensVendidos) {
        int posCabecalho = primeiraPosicaoLivre(historicoIdsPedidos);
        
        if (posCabecalho != -1) {
            historicoIdsPedidos[posCabecalho] = idPedido;
            historicoValoresPedidos[posCabecalho] = totalVenda;
        }

        for (int i = 0; i < vendaAtualIds.length; i++) {
            if (vendaAtualIds[i] != 0 && vendaAtualQuantidades[i] > 0) {
                int linha = primeiraLinhaLivre(historicoItensVendidos);
                if (linha != -1) {
                    historicoItensVendidos[linha][0] = idPedido;
                    historicoItensVendidos[linha][1] = vendaAtualIds[i];
                    historicoItensVendidos[linha][2] = vendaAtualQuantidades[i];
                }
            }
        }
    }

    public static int primeiraPosicaoLivre(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int primeiraLinhaLivre(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void imprimirNotaFiscal(int idPedido, int[] vendaIds, int[] vendaQtde, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos,double total) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String data = agora.format(fmt);

        System.out.println("\n*********************************************************************************************");
        System.out.println("* MACKSHOP                                                                 *");
        System.out.println("* CNPJ: 12.345.678/0001-99                                                 *");
        System.out.println("*********************************************************************************************");
        System.out.println("* NOTA FISCAL - VENDA AO CONSUMIDOR                                        *");
        System.out.printf("* Pedido ID: %-6d                                                           *%n", idPedido);
        System.out.printf("* Data de Emissão: %-19s                                        *%n", data);
        System.out.println("*********************************************************************************************");
        System.out.println("* # | ID   | DESCRIÇÃO                 | QTD | VL. UNIT.   | VL. TOTAL     *");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        int contador = 1;
        double subtotal = 0.0;
        
        for (int i = 0; i < vendaIds.length && i < idsProdutos.length; i++) {
            if (vendaIds[i] != 0) {
                double vlUnit = precosProdutos[i];
                double vlTot = vlUnit * vendaQtde[i];
                subtotal = subtotal + vlTot;
                
                System.out.printf("* %-1d | %-4d | %-23s | %3d | R$ %9.2f | R$ %10.2f *%n", contador, vendaIds[i], nomesProdutos[i], vendaQtde[i], vlUnit, vlTot);
                contador = contador + 1;
            }
        }
        
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("* %-18s | R$ %10.2f   *%n", "SUBTOTAL", subtotal);
        System.out.printf("* %-18s | R$ %10.2f   *%n", "TOTAL", total);
        System.out.println("*********************************************************************************************");
        System.out.println("* OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!                                *");
        System.out.println("*********************************************************************************************\n");
    }

    public static void limparVendaAtual(int[] vendaIds, int[] vendaQtde) {
        for (int i = 0; i < vendaIds.length; i++) {
            vendaIds[i] = 0;
            vendaQtde[i] = 0;
        }
    }

    
// ##########################################################################################################################
// ##########################################################################################################################

 // 6 - FUNÇÃO DE VER HISTORICO DE VENDAS
    
    
    public static void exibirHistoricoDeVendas(int[] historicoIdsPedidos, double[] historicoValoresPedidos) {
        System.out.println("\nHISTÓRICO DE VENDAS\n");
        boolean vazio = true;
        
        for (int i = 0; i < historicoIdsPedidos.length; i++) {
            if (historicoIdsPedidos[i] != 0) {
                vazio = false;
                System.out.printf("Pedido ID: %d - Valor Total: R$ %.2f%n", historicoIdsPedidos[i], historicoValoresPedidos[i]);
            }
        }
        if (vazio) {
            System.out.println("(vazio)");
        }
    }
    

// ##########################################################################################################################
// ##########################################################################################################################
    
// 7 - FUNCÕES PARA BUSCAR VENDA ESPECÍFICA DO HISTÓRICO

    
    public static void buscarEReimprimirVenda(Scanner sc, int[][] historicoItensVendidos, int[] historicoIdsPedidos, double[] historicoValoresPedidos, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos) {
        System.out.print("\nDigite o ID do pedido que deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        int indiceCabecalho = -1;
        
        for (int i = 0; i < historicoIdsPedidos.length; i++) {
            if (historicoIdsPedidos[i] == id) {
                indiceCabecalho = i;
                break;
            }
        }
        
        if (indiceCabecalho == -1) {
            System.out.println("\nPedido não encontrado no histórico!");
            return;
        }
        
        double total = historicoValoresPedidos[indiceCabecalho];

        int itensDoPedido = 0;
        for (int[] linha : historicoItensVendidos) {
            if (linha[0] == id) {
                itensDoPedido = itensDoPedido + 1;
            }
        }
        
        if (itensDoPedido == 0) {
            System.out.println("\nNenhum item registrado para este pedido!");
            return;
        }

        int[] vendaIds = new int[idsProdutos.length];
        int[] vendaQtd = new int[idsProdutos.length];
        
        for (int[] linha : historicoItensVendidos) {
        	
            if (linha[0] == id) {
                int idProd = linha[1];
                int qt = linha[2];
                int indice = indiceDoProduto(idsProdutos, idProd);
                if (indice != -1) {
                    vendaIds[indice] = idProd;
                    vendaQtd[indice] = qt;
                }
            }
        }
        
        imprimirNotaFiscal(id, vendaIds, vendaQtd, idsProdutos, nomesProdutos, precosProdutos, total);
    }

    
// ##########################################################################################################################
// ##########################################################################################################################

// 8 - FUNÇÕES REPOR ESTOQUE
    
    public static void reporEstoque(Scanner sc, int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos) {
        System.out.print("\nID do produto para repor: ");
        int id = lerIdValido(sc, idsProdutos);
        int idx = indiceDoProduto(idsProdutos, id);
        System.out.printf("Produto: %s | Qtd. atual: %d%n", nomesProdutos[idx], estoquesProdutos[idx]);
        System.out.print("Repor quantas unidades? ");
        int qtd = lerQuantidadePositiva(sc);
        estoquesProdutos[idx] = estoquesProdutos[idx] + qtd;
        System.out.printf("Produto %d atualizado! Agora tem %d unidades.%n", id, estoquesProdutos[idx]);
    }

    public static int lerQuantidadePositiva(Scanner sc) {
        while (true) {
            int qtd = sc.nextInt();
            sc.nextLine();
            if (qtd > 0) {
                return qtd;
            }
            System.out.print("A quantidade deve ser > 0. Digite de novo: ");
        }
    }
    
    
// ##########################################################################################################################
// ##########################################################################################################################

// 9 - FUNÇÕES DE RELATÓRIO DE ESTOQUE BAIXO
    
    public static void emitirRelatorioEstoqueBaixo(int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos, int minimo) {
        System.out.printf("\nRELATÓRIO DE ESTOQUE BAIXO\n");
        
        boolean encontrou = false;
        for (int i = 0; i < idsProdutos.length; i++) {
            if (estoquesProdutos[i] < minimo) {
                encontrou = true;
                System.out.printf("ID %-4d | %-24s | Qtd.: %d%n", idsProdutos[i], nomesProdutos[i], estoquesProdutos[i]);
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto abaixo do limiar.");
        }
    }

// ##########################################################################################################################
// ##########################################################################################################################

// VETOR UTILS
    
    public static void copiarConteudo(int[] origem, int[] destino) {
        for (int i = 0; i < origem.length && i < destino.length; i++) {
            destino[i] = origem[i];
        }
    }
}
