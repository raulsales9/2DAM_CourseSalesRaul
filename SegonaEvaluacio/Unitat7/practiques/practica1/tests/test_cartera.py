import pytest
from cartera import Cartera, SaldoInsuficiente

def test_saldo_inicial_per_defecte():
    cartera = Cartera()
    assert cartera.saldo == 0

def test_saldo_inicial_positiu():
    cartera = Cartera(100)
    assert cartera.saldo == 100

def test_ingresar_diners():
    cartera = Cartera(100)
    cartera.ingresar(50)
    assert cartera.saldo == 150

def test_gastar_diners():
    cartera = Cartera(100)
    cartera.gastar(50)
    assert cartera.saldo == 50

def test_perdamunt_posibilitats():
    cartera = Cartera(100)
    with pytest.raises(SaldoInsuficiente):
        cartera.gastar(150)
