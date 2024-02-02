import 'package:comarques_bloc_b/bloc/comarques_bloc.dart';
import 'package:comarques_bloc_b/model/provincia.dart';
import 'package:comarques_bloc_b/screens/selector_comarca.dart';
import 'package:flutter/material.dart';

class SelectorProvincia extends StatelessWidget {
  SelectorProvincia({
    super.key,
  });

  final ComarquesBloc comarquesBloc = ComarquesBloc();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromARGB(200, 255, 255, 255),
        body: Center(
          child: SingleChildScrollView(
            child: StreamBuilder(
              stream: comarquesBloc.obtenirProvinciesStream,
              builder: (BuildContext context, AsyncSnapshot snapshot) {
                if (snapshot.connectionState == ConnectionState.active &&
                    snapshot.hasData) {
                  return Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: _creaLlistaProvincies(snapshot.data ?? []),
                  );
                }
                return const Center(child: CircularProgressIndicator());
              },
            ),
          ),
        ),
      );
  }

  _creaLlistaProvincies(List<dynamic> data) {
    List<Widget> llista = [];
    for (Provincia provincia in data) {
      llista.add(ProvinciaRBWithGesture(
          nom: provincia.nom, img: provincia.imatge ?? ""));
      llista.add(const SizedBox(height: 20));
    }

    return llista;
  }
}

class ProvinciaRBWithGesture extends StatelessWidget {
  const ProvinciaRBWithGesture(
      {required this.img, required this.nom, super.key});

  final String img;
  final String nom;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        child: ProvinciaRoundButton(
          img: img,
          nom: nom,
        ),
        onTap: () {
          debugPrint("Clic en $nom");
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: ((context) => SelectorComarca(provincia: nom)),
            ),
          );
        });
  }
}

class ProvinciaRoundButton extends StatelessWidget {
  const ProvinciaRoundButton({required this.img, required this.nom, super.key});

  final String img;
  final String nom;

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      radius: 110,
      backgroundImage: NetworkImage(img),
      child: Text(
        nom,
        textAlign: TextAlign.end,
        style: Theme.of(context).textTheme.displayMedium,
      ),
    );
  }
}
