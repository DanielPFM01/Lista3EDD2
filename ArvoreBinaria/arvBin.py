# Questão 6
# Programa em python para demonstrar inserção em BST

# classe que representa um nó em uma BST
class No:
	def __init__(self, chave):
		self.esquerda = None
		self.direita = None
		self.val = chave

# Função para inserir um novo nó com uma chave
def inserir(raiz, chave):
	if raiz is None:
		return No(chave)
	else:
		if raiz.val == chave:
			return raiz
		elif raiz.val < chave:
			raiz.direita = inserir(raiz.direita, chave)
		else:
			raiz.esquerda = inserir(raiz.esquerda, chave)
	return raiz

# Traversão inorder da árvore
def inorder(raiz):
	if raiz:
		inorder(raiz.esquerda)
		print(raiz.val)
		inorder(raiz.direita)


# Teste das funções
# Criação da BST abaixo:
# 50
# /	 \
# 30	 70
# / \ / \
# 20 40 60 80

r = No(50)
r = inserir(r, 30)
r = inserir(r, 20)
r = inserir(r, 40)
r = inserir(r, 70)
r = inserir(r, 60)
r = inserir(r, 80)

# Imprime a traversão inorder da BST
inorder(r)
