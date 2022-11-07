package RubroNegra;

// Questão 6
// Implementação de inserção e traversão de uma árvore Rubro Negra.
public class RedBlackTree {
    public Node root; //nó raíz

    // construtor da árvore
    public RedBlackTree() {
        super();
        root = null;
    }

    // subclasse do nó
    class Node {
        int data;
        Node left;
        Node right;
        char colour;
        Node parent;

        // construtor do nó
        Node(int data) {
            super();
            this.data = data; // apenas dados, não chave.
            this.left = null; // subárvore esquerda
            this.right = null; // subárvore direita
            this.colour = 'R'; // cor. 'R' (red) ou 'B' (black)
            this.parent = null; // necessário no momento de verificação
        }
    }

    // rotação esquerda
    Node rotateLeft(Node node) {
        Node x = node.right;
        Node y = x.left;
        x.left = node;
        node.right = y;
        node.parent = x; // resetar pai é importante
        if(y!=null)
            y.parent = node;
        return(x);
    }

    // rotação direita
    Node rotateRight(Node node) {
        Node x = node.left;
        Node y = x.right;
        x.right = node;
        node.left = y;
        node.parent = x;
        if(y!=null)
            y.parent = node;
        return(x);
    }

    // flags
    // respectivas rotações são realizadas durante rastreamento
    // rotações acontecem quando flags = true
    boolean ll = false;
    boolean rr = false;
    boolean lr = false;
    boolean rl = false;


    // função para auxiliar inserção.
    Node insertHelp(Node root, int data) {
        // f = true quando há um conflito RED RED
        boolean f = false;

        // call recursiva para inserir em uma posição apropriada de acordo com as propriedades de BST
        if (root == null)
            return(new Node(data));
        else if (data < root.data) {
            root.left = insertHelp(root.left, data);
            root.left.parent = root;
            if (root != this.root) {
                if(root.colour == 'R' && root.left.colour == 'R')
                    f = true;
            }
        } else {
            root.right = insertHelp(root.right,data);
            root.right.parent = root;
            if (root != this.root) {
                if(root.colour == 'R' && root.right.colour == 'R')
                    f = true;
            }
            // no mesmo momento de inserção, também atribui-se os nós pais
            // também checa-se para conflitos RED RED
        }

        // rotação
        if(this.ll) { // para rotação esquerda
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';
            this.ll = false;
        } else if(this.rr) { // para rotação direita
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
            this.rr = false;
        }
        else if(this.rl) { // para direita e depois esquerda
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';

            this.rl = false;
        }  else if(this.lr) { // para esquerda e depois direita
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
            this.lr = false;
        }
        // ao terminar de rotacionar e recolorir, as flags são resetadas

        // tratando conflitos RED RED
        if(f) {
            if(root.parent.right == root) { // checar qual filho é o nó atual de seu antecessor
                if(root.parent.left == null || root.parent.left.colour == 'B') { // quando o irmão de um antecessor é black
                    // performa rotações e recolorações. é feito durante rastreamento.
                    if(root.left != null && root.left.colour == 'R')
                        this.rl = true;
                    else if(root.right != null && root.right.colour == 'R')
                        this.ll = true;
                }
                else { // quando o irmão de um antecessor é Red
                    root.parent.left.colour = 'B';
                    root.colour = 'B';
                    if(root.parent != this.root)
                        root.parent.colour = 'R';
                }
            }
            else {
                if(root.parent.right == null || root.parent.right.colour == 'B') {
                    if(root.left != null && root.left.colour == 'R')
                        this.rr = true;
                    else if(root.right != null && root.right.colour == 'R')
                        this.lr = true;
                } else {
                    root.parent.right.colour = 'B';
                    root.colour = 'B';
                    if(root.parent != this.root)
                        root.parent.colour = 'R';
                }
            }
            f = false;
        }
        return(root);
    }

    // inserir dados na árvore
    public void insert(int data) {
        if(this.root == null) {
            this.root = new Node(data);
            this.root.colour = 'B';
        } else
            this.root = insertHelp(this.root, data);
    }

    // método auxiliar para imprimir traversão EmOrdem
    void inorderTraversalHelper(Node node) {
        if(node != null) {
            inorderTraversalHelper(node.left);
            System.out.printf("%d ", node.data);
            inorderTraversalHelper(node.right);
        }
    }

    // imprimir traversão EmOrdem
    public void inorderTraversal() {
        inorderTraversalHelper(this.root);
    }

    // método auxiliar para imprimir a árvore
    void printTreeHelper(Node root, int space) {
        int i;
        if(root != null) {
            space = space + 10;
            printTreeHelper(root.right, space);
            System.out.printf("\n");
            for ( i = 10; i < space; i++) {
                System.out.printf(" ");
            }
            System.out.printf("%d", root.data);
            System.out.printf("\n");
            printTreeHelper(root.left, space);
        }
    }

    // imprimir a árvore
    public void printTree() {
        printTreeHelper(this.root, 0);
    }

    // teste
    public static void main(String[] args) {

        RedBlackTree t = new RedBlackTree();

        int[] arr = {1,4,6,3,5,7,8,2,9};

        for(int i=0;i<9;i++) {
            t.insert(arr[i]);
            System.out.println();
            t.inorderTraversal();
        }

        t.printTree();
    }
}
