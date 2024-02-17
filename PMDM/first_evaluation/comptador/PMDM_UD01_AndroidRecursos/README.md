# PMDM_UD01_AndroidRecursos
Recursos per a la unitat 1 del mòdul de PMDM
**Tenim una estructura en el que principalment, hi tocarem el layout de resources a el que hi declarem l'estructura de l'aplicació, i sobretot l'ActivityMain, el cual conté la lógica de l'aplicació. També hi ha que tindre en compte el fitxer androidmanifest, pel's canvis de activitats a realitzar**

**Hi comptem amb un projecte a android studio, el cual hi tenim una classe main que hi té de base la lógica per incrementar el comptador al fer-li click, i tenim també els botons amb estils creats al layout en un xml**

**Com a solució, hi guardarem l'estat per tal de que no se destroixca aquest. Ens han deixat citada la funció onSaveInstanceState() per tal de guardar l'estat, al que l'únic a fer seria treballar amb el valor del comptador al canviar l'horientació del mòbil.**

**Per a ampliar la funcionalitat, hi haurém de crear els botons a el layout a el fitxer XML, després hi tindrem que crear les variables que faran refercia a els botons, similar a un document.getElementById() de javascript. Després referenciarem els botons amb la logica de igualar el compatador a 0, o si es matjor que 0, que este decremente 1 per cada click**

**Per a implementar el viewBinding acudirem a els scripts de gradle per a implementar viewBinding {
    enabled = true
} 

i així activarlo, desprès farem els canvis necesaris a el activitymain per a implementar ActivityMainBinding i els canvis**
