/* Questão 02 e 04 */

public class ArvBinBusca<Valor extends Comparable<Valor>> {
	private No raiz; /* Raiz da árvore. */

	private class No
	{
		private Valor valor; /* Valor armazenado. */
		private No esq, dir; /* Referências para subárvores esquerda e direita. */

		/* Cria um nó com valor e valor fornecidos e esq = dir = null. */
		public No(Valor valor)
		{
			this.valor = valor;
			this.esq = null;
			this.dir = null;
		}
	}
	
	/**
	 *  Criação de uma árvore vazia. 
	 */
	public ArvBinBusca()
	{
		raiz = null;
	}
	
	/**
	 * Apresenta o conteúdo da árvore em ordem.
	 */
	public void mostra()
	{
		mostra(raiz);
	}
	
	private void mostra(No x)
	{
		/* Caso base (critério de parada). */
		if(x == null)
			return;
		
		System.out.print("[");
		
		/* Chamada recursiva para a esquerda. */
		mostra(x.esq);
		
		/* Imprime a valor do nó corrente. */
		System.out.print("<" + x.valor + ">");
		
		/* Chamada recursiva para a direita. */
		mostra(x.dir);
		
		System.out.print("]");
	}
	
	
    /**
     * Essa árvore binária de busca contém o valor fornecido?
     *
     */
    public boolean contem(Valor valor) {
        if (valor == null) 
        	throw new IllegalArgumentException("O valor fornecido � null!");
        
        return get(valor) != null;
    }
	
    /**
     * Retorna o valor associado ao valor fornecido.
     */
    public Valor get(Valor valor) 
    {
        return get(raiz, valor);
    }

    private Valor get(No x, Valor valor) 
    {
        /* O valor não se encontra na árvore. */
        if(x == null)
        	return null;
        
        int cmp = valor.compareTo(x.valor);
        
        if(cmp < 0) /* Deve-se ir para a esquerda. */
        	return get(x.esq, valor);
        else if(cmp > 0) /* Deve-se ir para a direita. */
        	return get(x.dir, valor);
        else /* Valor encontrado. */
        	return x.valor;
    }
    
    /**
     * Insere na árvore binária de busca o valor fornecido. Caso a árvore
     * já possua a valor especificada, o valor antigo é sobrescrito com o novo valor 
     * fornecido. 
     */
    public void put(Valor valor)
    {
        if(valor == null) 
        	throw new IllegalArgumentException("O valor fornecido � nulo!");
        
        raiz = put(raiz,valor);
    }

    private No put(No x, Valor valor) {
    	/* Caso base: encontrou a posi��o de inser��o. */
        if (x == null)
        	return new No(valor);
        
        int cmp = valor.compareTo(x.valor);
        
        if (cmp < 0) /* Deve-se ir para a esquerda. */
        	x.esq = put(x.esq, valor);
        else if (cmp > 0) /* Deve-se ir para a direita. */
        	x.dir = put(x.dir, valor);
        else /* Caso tenha encontrado nó de mesmo valor. */
        	x.valor = valor;
        
        return x;
    }
    
    public static void main(String args[])
	{
		ArvBinBusca<Integer> arvoreBinaria = new ArvBinBusca<>();
		
		arvoreBinaria.put(9);
		arvoreBinaria.put(23);
		arvoreBinaria.put(25);
		arvoreBinaria.put(10);
		arvoreBinaria.put(3);
		arvoreBinaria.put(7);
		arvoreBinaria.put(22);
		
		System.out.println("Arvore impressa Em Ordem:");

		arvoreBinaria.mostra();

		System.out.println("\n");
		
		
		if(arvoreBinaria.contem(13))
			System.out.println("O n°de valor 18 está contido na árvore.");
		else
			System.out.println("O n° de valor 18 não está contido na árvore.");
		
		if(arvoreBinaria.contem(23))
			System.out.println("O n° de valor 23 está contido na árvore.");
		else
			System.out.println("O n° de valor 23 não está contido na árvore.");
		
	}
}
