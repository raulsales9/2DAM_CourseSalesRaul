import 'package:comarques_provider/provider/comarques_provider.dart';
import 'package:comarques_provider/model/provincia.dart';
import 'package:comarques_provider/screens/selector_comarca.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class SelectorProvincia extends StatelessWidget {
  const SelectorProvincia({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    // Cal afegir de nou aquest Container com a pare de l'Scaffold,
    // ja que quan fem la navegació, el Container de la pantalla
    // anterior desapareix.

    var comarquesProvider = Provider.of<ComarquesProvider>(context);

    return Scaffold(
        backgroundColor: const Color.fromARGB(200, 255, 255, 255),
        /*appBar: AppBar(
          title: const Text('Selector de provincies'),
        ),*/
        body: Center(
          child: SingleChildScrollView(
            child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children:
                    _creaLlistaProvincies(comarquesProvider.provincies ?? [])),
          ),
      ),
    );
  }

  _creaLlistaProvincies(List<dynamic>? data) {
    if (data == null) {
      // Si la llista és nul·la retornem un indicador de progrés
      return const [CircularProgressIndicator()];
    }

    // En cas contrari, creem la llista de províncies
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
