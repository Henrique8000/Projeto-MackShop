package application;

public class teste {

	public static void main(String[] args) {
		int[] idsProdutos = null;
		String[] nomesProdutos = null; 
		
		idsProdutos = inicializarCatalogo(idsProdutos);
				
		for (int id : idsProdutos) {
			System.out.println(id);
		}
		
		/*for (String nomes : nomesProdutos) {
			System.out.println(nomes);
		}*/
	}

	public static int[] inicializarCatalogo(int[] idsProdutos) {
		return idsProdutos = new int[] {1, 2, 3, 4, 5};		
		}
}
