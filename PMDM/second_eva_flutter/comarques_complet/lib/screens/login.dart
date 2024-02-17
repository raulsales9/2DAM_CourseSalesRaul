import 'package:flutter/material.dart';
import 'package:comarques_complet/screens/selector_provincia.dart';
import 'package:comarques_complet/screens/registre.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _controlador = TextEditingController();
  final TextEditingController _controladorPass = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        margin: const EdgeInsets.all(20.0),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              Image.asset(
                "../assets/img/logo.png",
                height: 450,
                width: 250,
              ),
              Text(
                "Les nostres comarques",
                style: Theme.of(context).textTheme.displayLarge,
              ),
              TextFormField(
                controller: _controlador,
                decoration: const InputDecoration(
                  filled: true,
                  fillColor: Colors.white,
                  labelText: 'Usuari',
                  border: OutlineInputBorder(),
                ),
              ),
              TextFormField(
                controller: _controladorPass,
                obscureText: true,
                decoration: const InputDecoration(
                  filled: true,
                  fillColor: Colors.white,
                  labelText: 'Contrassenya',
                  border: OutlineInputBorder(),
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  ElevatedButton(
                    onPressed: (() {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: ((context) => SelectorProvincia()),
                        ),
                      );
                    }),
                    child: const Text('Login'),
                  ),
                  ElevatedButton(
                    onPressed: (() {
                      Future<Map?> resposta = mostraDialog(context);
                      resposta.then((value) {
                        setState(() {
                          _controlador.text = value?["username"];
                          _controladorPass.text = value?["pass"];
                        });
                      });
                    }),
                    child: const Text('Register'),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }

  Future<Map?> mostraDialog(BuildContext context) {
    return showDialog(
        // Proporcionem el context
        context: context,
        // I la funció (builder) que constueix el diàleg
        builder: (context) {
          // Aquesta consistirà en la creació d'un diàleg personalitzat
          return const DialegAmbFormulari();
        });
  }
}
