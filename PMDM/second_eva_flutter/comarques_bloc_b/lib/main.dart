import 'package:comarques_bloc_b/screens/login.dart';
import 'package:comarques_bloc_b/screens/themes/tema_comarques.dart';
import 'package:flutter/material.dart';

void main() => runApp(const ComarquesApp());

class ComarquesApp extends StatelessWidget {
  const ComarquesApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: temaComarques,
        title: 'Les nostres comarques',
        home: const LoginScreen(),
        );
  }
}
