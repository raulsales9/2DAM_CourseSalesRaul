import 'package:flutter/material.dart';

class InfoComarcaDetall extends StatelessWidget {
  const InfoComarcaDetall({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Container(
      margin: const EdgeInsets.all(16.0),
      decoration:
          const BoxDecoration(color: Color.fromARGB(200, 255, 255, 255)),
      child: const Placeholder(),
    ));
  }
}
