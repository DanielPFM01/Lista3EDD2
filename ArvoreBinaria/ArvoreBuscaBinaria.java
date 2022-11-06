package ArvoreBinaria;

// Questão 6
// Inserção em uma BST
class ArvoreBuscaBinaria {

    // subclasse Nó contendo filhos esquerdo e direito do nó atual e sua chave
    class No {
        int chave;
        No esquerda, direita;

        // construtor do nó
        public No(int item) {
            chave = item;
            esquerda = direita = null;
        }
    }

    // raíz da BST
    No raiz;

    // Construtor da árvore
    ArvoreBuscaBinaria() { raiz = null; }

    ArvoreBuscaBinaria(int value) { raiz = new No(value); }

    // método para inserir chave
    void inserir(int chave) { raiz = insertRec(raiz, chave); }

    // método recursivo para inserir uma nova chave na BST
    No insertRec(No raiz, int chave) {

        // se a árvore estiver vazia, retorna um novo nó
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }

        // caso contrário
        else if (chave < raiz.chave)
            raiz.esquerda = insertRec(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = insertRec(raiz.direita, chave);

        // retorna o ponteiro do nó inalterado
        return raiz;
    }

    // método que chama o método inorderRec()
    void inorder() { inorderRec(raiz); }

    // método que realiza a traversão emOrder (inOrder) da BST
    void inorderRec(No raiz) {
        if (raiz != null) {
            inorderRec(raiz.esquerda);
            System.out.println(raiz.chave);
            inorderRec(raiz.direita);
        }
    }

    // teste
    public static void main(String[] args) {
        ArvoreBuscaBinaria arvore = new ArvoreBuscaBinaria();

		/* Criar a bst abaixo:
			50
		/	 \
		30	 70
		/ \ / \
	20 40 60 80 */

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(70);
        arvore.inserir(60);
        arvore.inserir(80);

        // traversão inorder
        arvore.inorder();
    }
}