import 'package:flutter/material.dart';

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
                Shadow(
                    offset: Offset(2, 2), color: Colors.black, blurRadius: 3),
              ])
  ),
);

