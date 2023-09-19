import random

class ErrorEnterMassaGran(Exception):
    pass

class ErrorMassaMenut(Exception):
    pass

class ErrorNoEsEnter(Exception):
    pass

random_num = random.randint(0, 100)
while True:
    try:
        num = input("Introdueix un nombre aleatori entre 0 i 100: ")
        # convertim l'entrada a enter
        numero = int(num)
        if numero < random_num:
            raise ErrorMassaMenut("Massa menut")
        elif numero > random_num:
            raise ErrorEnterMassaGran("Massa gran")
        else:
             print("Enorabona, has endevinat el número {numero_aleatori}")
             break
    except ErrorNoEsEnter:
        print("Error, no es numeric")

    except ErrorMassaMenut as ene:
        print(ene)
    
    except ErrorEnterMassaGran as emg:
        print(emg)
        