**nota**utilitzem os.path.join("","") per evitar conflictes amb les rutes

Hem creat un fitxer que hi portará els tamanys per defecte mitjançant globals, ja siguen velocitats etc, per tal de no carregar joc.py que es el fitxer principal
Hi tenim tot separat mitjançant clases, ja que os bona practica degut a implementacions futures de funcionalitats, i apart facilita més la legibilitat de codi.
A partir de ací a la classe Joc hi tenim tot separat amb diverses funcions, __init__ el cual conté lo necesari per a la musica per mixer, l'afegiment d'enemics u altres sprites, les fonts o recursos que pugam utilitzar més aball, i apart un bucle amb condicions per a accedir al menu, al joc, a la derrota ...

Tenim un  main manu simplement com a pantalla d'acces, li pasem un text utilitzant la font i les condicions per a accedir a el joc.

A joc es on es treballará amb tot, es a dir, hi tenim la agropacio d'sprites, la instancia de el jugador, i inicilitzacions de  variables necesaries per a controlar el fondo, la puntuació etc. Básicament, hi trobem l'afegiment de els sprites, enemics, logica per accedir al joc, per eixir del joc, logica de colicions per pygmae.spritescolleany() al cual implementem la logica de colicions, que accions volem que pasen, com volem pintar les coses, lógica de canvi de fons.

La pantalla de derrota ve a ser la mateixa, amb el codi practicament igual, sols que este delimita que cuan aprete la p, el nivell i score es reinicien i crida a la funcio per a tornar al joc.

Guardem els punts a dos fitxers .txt, en els que llegim, escribim segons si ja hi hi puntuació anterior o no.


paquets : 

Clock() utilitzat per a controlar el frame rate, get_time() de la llibreria time, utilitzat per treballar amb el temps. Treballem amb globals que no es molt recomanable sempre, pero en certs casos com la puntuació considere que seria util.

extres:

el llançament de cohets de defensa , que s'ha implementat tambe a el bucle de el joc, al cual al space, es cree un nou cohet i el afegix als sprites, Impacte, el cual s'afegix a els sprites de colició amb el que volem que pase cuan hi ha un impacte. Cal recalcar que hem diversificat les clases, per tant necesitarem dos scripts de python amb la lógica de el cohet, i el impacte, efectes, i un nerf per a que ens rebaixe la puntuació si disparem .
lógica de nivells per a incrementar dificultat. Cambi de color de fons de LIGHTMODE a DARKMODE mitjançant el temps que transcorre. Pantalla d'inci i final, el cual hi cambia el bucle i el missatge, per el demès es el mateix.

A futur: 

3 Vides per a la nau
Afegir un boss final al superar el nivell 5 del joc 
