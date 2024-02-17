´´´def funcio_externa():
    a = 20

    def funcio_interna():
        a = 30
        print('a =', a)

    funcio_interna()
    print('a =', a)


a = 10
funcio_externa()
print('a =', a)´´´

**a = 30, a = 20, a = 10**