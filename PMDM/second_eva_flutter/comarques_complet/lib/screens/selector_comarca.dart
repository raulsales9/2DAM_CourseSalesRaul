import 'package:comarques_complet/bloc/comarques_bloc.dart';
import 'package:comarques_complet/screens/info_comarca.dart';
import 'package:flutter/material.dart';

class SelectorComarca extends StatelessWidget {
  SelectorComarca({required this.provincia, super.key});

  final String provincia;
  // Definim una referència al BLoC
  final ComarquesBloc comarquesBloc = ComarquesBloc();

  @override
  Widget build(BuildContext context) {
    comarquesBloc.provinciaActual = provincia;
    return Container(
      decoration: const BoxDecoration(
        image: DecorationImage(
          image: AssetImage('assets/img/bg.webp'),
          fit: BoxFit.cover,
        ),
      ),
      child: Scaffold(
        body: Center(
          child: StreamBuilder(
            stream: comarquesBloc.obtenirComarquesStream,
            initialData: const [],
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              if (snapshot.connectionState == ConnectionState.active &&
                  snapshot.hasData) {
                return _creaLlistaComarques(snapshot.data);
              } else if (snapshot.hasError) {
                return Text(snapshot.error.toString());
              } else {
                return const CircularProgressIndicator();
              }
            },
          ),
        ),
      ),
    );
  }

  dynamic _creaLlistaComarques(List<dynamic> values) {
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