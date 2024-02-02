import 'package:flutter/material.dart';
import 'package:comarques_complet/screens/login.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Comarques',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      //redirecció a login, pantalla d'accés requerit per a gastar l'app
      home: LoginScreen(),
    );
  }
}
