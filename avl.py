# Questão 6
# Programa para inserir um nó em uma AVL

# classe para o nó da árvore
class TreeNode(object):
	def __init__(self, val):
		self.val = val
		self.left = None
		self.right = None
		self.height = 1

# classe da árvore AVL com operação de inserção
class AVL_Tree(object):
    # função recursiva para inserir chave na subárvore enraizada com o nó 
    # e retornar a nova raíz da subárvore
	def insert(self, root, key):
	
        # Passo 1 - Realizar passos normais da BST
		if not root:
			return TreeNode(key)
		elif key < root.val:
			root.left = self.insert(root.left, key)
		else:
			root.right = self.insert(root.right, key)

        # Passo 2 - atualizar a altura dos nós antecessores
		root.height = 1 + max(self.getHeight(root.left),
						self.getHeight(root.right))

        # Passo 3 - Pegar o fator de balanceamento
		balance = self.getBalance(root)

        # Passo 4 - Se o nó estiver desbalanceado, então tentar os 4 casos abaixo:
        # Caso 1 - Left Left
		if balance > 1 and key < root.left.val:
			return self.rightRotate(root)

		# Caso 2 - Right Right
		if balance < -1 and key > root.right.val:
			return self.leftRotate(root)

		# Caso 3 - Left Right
		if balance > 1 and key > root.left.val:
			root.left = self.leftRotate(root.left)
			return self.rightRotate(root)

		# Caso 4 - Right Left
		if balance < -1 and key < root.right.val:
			root.right = self.rightRotate(root.right)
			return self.leftRotate(root)

		return root

    # rotação esquerda
	def leftRotate(self, z):

		y = z.right
		T2 = y.left

		# realiza rotação
		y.left = z
		z.right = T2

		# atualiza alturas
		z.height = 1 + max(self.getHeight(z.left),
						self.getHeight(z.right))
		y.height = 1 + max(self.getHeight(y.left),
						self.getHeight(y.right))

		# Retorna nova raíz
		return y

    # rotação direita
	def rightRotate(self, z):

		y = z.left
		T3 = y.right

		# realiza rotação
		y.right = z
		z.left = T3

		# atualiza alturas
		z.height = 1 + max(self.getHeight(z.left),
						self.getHeight(z.right))
		y.height = 1 + max(self.getHeight(y.left),
						self.getHeight(y.right))

		# retorna nova raíz
		return y

    # retorna altura
	def getHeight(self, root):
		if not root:
			return 0

		return root.height

    # retorna balanceamento
	def getBalance(self, root):
		if not root:
			return 0

		return self.getHeight(root.left) - self.getHeight(root.right)

    # traversão preOrder
	def preOrder(self, root):

		if not root:
			return

		print("{0} ".format(root.val), end="")
		self.preOrder(root.left)
		self.preOrder(root.right)


# Teste
myTree = AVL_Tree()
root = None

root = myTree.insert(root, 10)
root = myTree.insert(root, 20)
root = myTree.insert(root, 30)
root = myTree.insert(root, 40)
root = myTree.insert(root, 50)
root = myTree.insert(root, 25)

"""Árvore AVL a construir:
			30
		/ \
		20 40
		/ \	 \
	10 25 50"""

# Traversão preorder
print("A traversão preOrdem da árvore AVL construída é:")
myTree.preOrder(root)
print()

