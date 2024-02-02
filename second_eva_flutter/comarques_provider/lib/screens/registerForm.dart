import 'package:flutter/material.dart';

// Definim la classe per al giny del diàleg,
// així com el seu estat
class DialegAmbFormulari extends StatefulWidget {
  const DialegAmbFormulari({super.key});

  @override
  State<DialegAmbFormulari> createState() => _DialegAmbFormulariState();
}

// Classe pe a l'estat del formulari
class _DialegAmbFormulariState extends State<DialegAmbFormulari> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _controlador = TextEditingController();
  final TextEditingController _controladorPass = TextEditingController();
  @override
  Widget build(BuildContext context) {
    // Per mostrar el diàleg com un diàleg simple
    return Dialog(
      // Per mostrar el diàleg a pantalla completa
      //return Dialog.fullscreen(
      child: Form(
        key: _formKey,
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              createDialogHeader(),
              const Divider(height: 64),
              createRegisterNameFormField(),
              const Divider(),
              createPasswordFormField(),
              //createCheckboxConditionsFormField(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  createSubmitButton(context),
                  createCancelButton(context)
                ],
              )
            ],
          ),
        ),
      ),
    );
  }

  Text createDialogHeader() {
    return const Text(
      "Registre",
      textAlign: TextAlign.center,
      style: TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.bold,
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    // Inicialitzem el text del controlador
    _controlador.text = "";
  }

  @override
  void dispose() {
    // Alliberem el controlador quan el giny
    // s'elimine de l'arbre de ginys.
    _controlador.dispose();
    super.dispose();
  }

  TextFormField createRegisterNameFormField() {
    // Crea el giny de tipus TextFormField
    return TextFormField(
      // Definim el controller
      controller: _controlador,
      // Amb la propietat validator definim les validacions
      validator: (value) {
        if (value?.isEmpty ?? true) {
          return 'El nom no pot ser buit';
        }

        return null;
      },

      decoration: InputDecoration(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          icon: const Icon(Icons.people),
          labelText: "Nom d'usuari"),
    );
  }

  TextFormField createPasswordFormField() {
    // Crea el giny de tipus TextFormField
    return TextFormField(
      // Definim el controller
      controller: _controladorPass,
      obscureText: true,
      // Amb la propietat validator definim les validacions
      validator: (value) {
        if (value?.isEmpty ?? true) {
          return 'La contrassenya no pot ser buida';
        }

        return null;
      },

      decoration: InputDecoration(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          icon: const Icon(Icons.password),
          labelText: "Password"),
    );
  }

  FormField<bool> createCheckboxConditionsFormField() {
    // Creem un FormField per envoltar el Checkbox
    return FormField(
      // Donem un valor inicial
      initialValue: false,
      // Definim les validacions sobre el valor
      validator: (value) {
        if (!value!) {
          return 'Heu d\'acceptar les condicions';
        }
        return null;
      },
      // Constructor del giny. Rebem en field l'estat
      // del FormField.
      builder: (FormFieldState<dynamic> field) {
        return CheckboxListTile(
          // El valor del giny serà el que tinga el FormField
          value: field.value,
          title: const Text("He llegit i accepte les condicions"),
          // Utilitzem el subtítol per indicar els missatges d'error
          subtitle: Text(
            field.errorText ?? "",
            style: TextStyle(
                fontStyle: FontStyle.normal,
                fontSize: 12,
                color: Colors.red[700],
                height: 0.5),
          ),
          // Quan es faça click en el checkbox,
          // s'invoca onChanged, el qual haurà de notificar el
          // formField que aquest ha canviat de valor, fent ús
          // del mètode didChange (sense necessitat d'invocar setState!)
          onChanged: (bool? value) {
            field.didChange(value);
          },
        );
      },
    );
  }

  // Botó per tancar el diàleg processant el formulari,
  // prèvia validació dels ginys
  Widget createSubmitButton(BuildContext context) {
    return TextButton(
      onPressed: () {
        // Accedim al formulari fent ús de _formKey.
        // Des d'aci, accedim a l'estat actual mitjançant currentState,
        // i validem aquest, amb el mètode "validate".
        // Aquest mètode invocarà totes les validacions de cada giny.
        // Si totes són vàlides, el formulari és vàlid.

        if (_formKey.currentState?.validate() ?? false) {
          // Si el formulari és vàlid, retornem el contingut del text.
          Navigator.pop(context,
              {"username": _controlador.text, "pass": _controladorPass.text});
        }
      },
      child: const Text('Registra\'t'),
    );
  }

  // Botó per cancel·lar el formulari
  Widget createCancelButton(BuildContext context) {
    return TextButton(
      onPressed: () {
        Navigator.pop(context);
      },
      child: const Text(
        'Cancel·la',
        style: TextStyle(color: Colors.redAccent),
      ),
    );
  }
}
