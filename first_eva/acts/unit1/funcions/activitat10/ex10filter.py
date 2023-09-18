
'''Defineix una llista i utilitzant filter, que la separe en dues llistes, una amb els parells i altra amb els imparells'''

llista = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

llista_par = list(filter(lambda x: x % 2 == 0, llista))
llista_impar = list(filter(lambda x: x % 2 != 0, llista))

print("Nombres parells: ", llista_par)
print("Nombres senars: ", llista_impar)