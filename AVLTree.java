// Questão 5

import java.io.*;
import java.util.*;

public class AVLTree {
    public class Node {
        private Node left, right, parent;
        private int height = 1;
        private int value;

        private Node (int val) {
            this.value = val;
        }
    }
    private int height (Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private Node insert(Node node, int value) {
        /* 1. Inserção de valor na árvore */
        if (node == null) {
            return(new Node(value));
        }

        if (value < node.value)
            node.left  = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        /* 2. Atualizar a altura do nó */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Verificar fator de equilíbrio */
        int balance = getBalance(node);

        // Se estiver desequilibrado, existem 4 casos possíveis

        // Caso Esquerda Esquerda
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Caso Direita Direita
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Caso Esquerda Direita
        if (balance > 1 && value > node.left.value)
        {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Direita Esquerda
        if (balance < -1 && value < node.right.value)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realizar rotação
        x.right = y;
        y.left = T2;

        // Atualizar altura
        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realizar rotação
        y.left = x;
        x.right = T2;

        // Atualizar altura
        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        return y;
    }

    // Pega fator de equilíbrio de um nó N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.printf("%d ", root.value);
            preOrder(root.right);
        }
    }

    private Node minValueNode(Node node) {
        Node current = node;
        /* Achar folha de menor valor */
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node deleteNode(Node root, int value) {
        // Deletar nó

        if (root == null)
            return root;

        // Se o valor a ser deletado é menor que o valor da raiz,
        // então ele fica na subarvore da esquerda
        if ( value < root.value )
            root.left = deleteNode(root.left, value);

            // Se o valor a ser deletado é maior que o valor da raiz,
            // então ele fica na subarvore da direita
        else if( value > root.value )
            root.right = deleteNode(root.right, value);

            // se o valor é o mesmo da raiz, então esse é o que deve ser deletado

        else {
            // nó com apenas um ou nenhum filho
            if( (root.left == null) || (root.right == null) ) {

                Node temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                // Caso Sem Filho
                if(temp == null) {
                    temp = root;
                    root = null;
                }
                else // Caso Um Filho
                    root = temp;

                temp = null;
            }
            else {
                // No com dois filhos: Pegar o sucessor da ordem (menor da subarvore da direita)
                Node temp = minValueNode(root.right);

                // Copiar o valor do sucessor nesse nó
                root.value = temp.value;

                // Deletar o sucessor
                root.right = deleteNode(root.right, temp.value);
            }
        }

        // Se a árvore tiver apenas um nó, então o retorna
        if (root == null)
            return root;

        // Atualizar altura
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Verificar fator de equilíbrio
        int balance = getBalance(root);

        // Se estiver desequilibrado, existem 4 casos possíveis

        // Caso Esquerda Esquerda
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Caso Esquerda Direita
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        // Caso Direita Direita
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Caso Direita Esquerda
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void print(Node root) {

        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
                width = (int)Math.pow(2, height-1);

        // Preparar variáveis
        List<Node> current = new ArrayList<Node>(1),
                next = new ArrayList<Node>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;

        for(int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));

            textBuffer = sb.toString();

            // Impressão dos elementos do nó
            for(Node n : current) {

                System.out.print(textBuffer);

                if(n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.value);
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();

            if(i < height - 1) {

                for(Node n : current) {

                    System.out.print(textBuffer);

                    if(n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renovar indicadores
            elements *= 2;
            current = next;
            next = new ArrayList<Node>(elements);

        }

    }

    public static void main(String args[]) {
        AVLTree t = new AVLTree();
        Node root = null;
        while (true) {
            System.out.println("(1) Inserir");
            System.out.println("(2) Deletar");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();

                if (Integer.parseInt(s) == 1) {
                    System.out.print("Valor a ser inserido: ");
                    root = t.insert(root, Integer.parseInt(bufferRead.readLine()));
                }
                else if (Integer.parseInt(s) == 2) {
                    System.out.print("Valor a ser deletado: ");
                    root = t.deleteNode(root, Integer.parseInt(bufferRead.readLine()));
                }
                else {
                    System.out.println("Numero invalido!");
                    continue;
                }

                t.print(root);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
