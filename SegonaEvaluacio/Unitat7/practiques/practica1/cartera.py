class SaldoInsuficiente(Exception):
    pass

class Cartera(object):

    def __init__(self, saldo_inicial=0):
        if isinstance(saldo_inicial, int) > 0:
            self.saldo = saldo_inicial
        else:
            self.saldo = 0

    def gastar(self, quantitat):
        if self.saldo < quantitat:
            raise SaldoInsuficiente(
                'No tens dines suficients. Saldo actual: {}'.format(quantitat))
        self.saldo -= quantitat

    def ingresar(self, quantitat):
        self.saldo += quantitat