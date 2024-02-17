import 'package:comarques_provider/provider/comarques_provider.dart';
import 'package:comarques_provider/provider/oratge_provider.dart';
import 'package:comarques_provider/screens/login.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

ThemeData temaComarques = ThemeData(
  textTheme: const TextTheme(
      displayLarge: TextStyle(
        fontFamily: "LeckerliOne",
        fontSize: 36,
        fontWeight: FontWeight.w100,
      ),
      displayMedium: TextStyle(
          fontWeight: FontWeight.w300,
          color: Colors.white,
          fontFamily: "LeckerliOne",
          fontSize: 40,
          shadows: [
            Shadow(offset: Offset(2, 2), color: Colors.black, blurRadius: 3),
          ])),
);

void main() => runApp(const ComarquesApp());

class ComarquesApp extends StatelessWidget {
  const ComarquesApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider( create: (context)=>ComarquesProvider()),
        ChangeNotifierProvider( create: (context)=>InfoOratgeProvider()),
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: temaComarques,
        title: 'Les nostres comarques',
        home: const LoginScreen(),
      ),
    );
  }
}