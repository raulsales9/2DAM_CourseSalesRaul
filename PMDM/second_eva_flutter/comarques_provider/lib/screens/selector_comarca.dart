import 'package:comarques_provider/provider/comarques_provider.dart';
import 'package:comarques_provider/screens/info_comarca.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class SelectorComarca extends StatelessWidget {
  SelectorComarca({required this.provincia, super.key});

  final String provincia;

  @override
  Widget build(BuildContext context) {
    // Creem una instància del provider
    var comarquesProvider = Provider.of<ComarquesProvider>(context);

    // I la utilitzem per modificar la província actual
    comarquesProvider.provinciaActual = provincia;

    return Scaffold(
        body: Center(
          child: _creaLlistaComarques(comarquesProvider.llistaComarques),
        ),      
    );
  }

  dynamic _creaLlistaComarques(List<dynamic>? values) {

    if (values == null) return const CircularProgressIndicator();
    
    return ListView.builder(
      itemCount: values.length,
      itemBuilder: (BuildContext context, int index) {
        if (values.isNotEmpty) {
          String img = values[index]["img"];
          String comarca = values[index]["nom"];
          return ClickableComarcaCard(img: img, comarca: comarca);
        } else {
          return const Center(
            child: Text("La llista és buida"),
          );
        }
      },
    );
  }
}

class ClickableComarcaCard extends StatelessWidget {
  const ClickableComarcaCard(
      {required this.img, required this.comarca, super.key});

  final String img;
  final String comarca;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: (() {
        Navigator.push(
            context,
            MaterialPageRoute(
                // Proporcionem el nom
                builder: ((context) => InfoComarca(nomcomarca: comarca))));
      }),
      child: ComarcaCard(img: img, comarca: comarca),
    );
  }
}

class ComarcaCard extends StatelessWidget {
  const ComarcaCard({
    super.key,
    required this.img,
    required this.comarca,
  });

  final String img;
  final String comarca;

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Container(
          height: 150,
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: Colors.blue,
            image: DecorationImage(image: NetworkImage(img), fit: BoxFit.cover),
          ),
          child: Align(
            alignment: Alignment.bottomLeft,
            child: Text(
              comarca,
              style: const TextStyle(
                  fontFamily: "LeckerliOne",
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                  fontSize: 20,
                  shadows: [
                    Shadow(
                        offset: Offset(2, 2),
                        color: Colors.black,
                        blurRadius: 3),
                  ]),
            ),
          )),
    );
  }
}


