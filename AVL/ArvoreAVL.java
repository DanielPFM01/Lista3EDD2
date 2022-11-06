package AVL;

// Questão 6
// Programa em Java para inserir elementos em uma AVL

public class ArvoreAVL {

    No raiz;

    // método para retornar a altura da árvore
    int height(No N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // retornar o maior de dois inteiros
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // rotacão direita da subárvore com y
    No rotacaoDireita(No y) {
        No x = y.left;
        No T2 = x.right;

        // performa rotação
        x.right = y;
        y.left = T2;

        // atualiza alturas
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // retorna a nova raíz
        return x;
    }

    // rotação esquerda da subárvore com x
    No rotacaoEsquerda(No x) {
        No y = x.right;
        No T2 = y.left;

        // performa rotação
        y.left = x;
        x.right = T2;

        // atualiza alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // retorna a nova raíz
        return y;
    }

    // retorna fator de balanceamento do nó N
    int getBalance(No N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    No insert(No No, int key) {

        // 1. Performa a inserção normal de uma BST
        if (No == null)
            return (new No(key));

        if (key < No.key)
            No.left = insert(No.left, key);
        else if (key > No.key)
            No.right = insert(No.right, key);
        else // chaves duplicadas não são permitidas
            return No;

        // 2. Atualiza a altura do nó pai
        No.height = 1 + max(height(No.left),
                height(No.right));

        // 3. pega o fator de balanceamento do nó pai para checar
        // se esse nó se tornará desbalanceado ou não
        int balance = getBalance(No);


        // se esse nó se tornar desbalanceado, então há 4 casos possíveis:

        // left left case
        if (balance > 1 && key < No.left.key)
            return rotacaoDireita(No);

        // Right Right Case
        if (balance < -1 && key > No.right.key)
            return rotacaoEsquerda(No);

        // Left Right Case
        if (balance > 1 && key > No.left.key) {
            No.left = rotacaoEsquerda(No.left);
            return rotacaoDireita(No);
        }

        // Right Left Case
        if (balance < -1 && key < No.right.key) {
            No.right = rotacaoDireita(No.right);
            return rotacaoEsquerda(No);
        }

        // retorna o ponteiro inalterado do nó
        return No;
    }

    // imprimir o percurso preordem da árvore
    // também imprive a altura de cada nó
    void preOrder(No No) {
        if (No != null) {
            System.out.print(No.key + " ");
            preOrder(No.left);
            preOrder(No.right);
        }
    }

    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        
        arvore.raiz = arvore.insert(arvore.raiz, 10);
        arvore.raiz = arvore.insert(arvore.raiz, 20);
        arvore.raiz = arvore.insert(arvore.raiz, 30);
        arvore.raiz = arvore.insert(arvore.raiz, 40);
        arvore.raiz = arvore.insert(arvore.raiz, 50);
        arvore.raiz = arvore.insert(arvore.raiz, 25);

		/* A árvore AVL construída:
			30
			/ \
		20 40
		/ \	 \
		10 25 50
		*/
        System.out.println("Percurso preordem da árvore construída é: ");
        arvore.preOrder(arvore.raiz);
    }
}

