*Fixa't en el següent fragment de codi:*

**Python**
´´´s = [1,2]
 r = s[:]
 s[0]=2
 print(s)
[2, 2]
 print(r)
???
 print(s)
???´´´
*Quina serà l'eixida? Per qué?*
**print r-> [1, 2]**
**print s-> [2, 2]**
*Python*
´´´>>> r = s
 s[0]= 5 //se cambia primer element de S a 5
 print(r)
???
 print(s)
???´´´
*I ara? Per qué?*
**print r -> [5, 2]**
**print s -> [5, 2]**