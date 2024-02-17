import 'dart:io';

import 'package:projecte_exemple/Comarques-services.dart';

void main(List<String> arguments) async {
  // Creem una còpia de la llista d'arguments del programa
  List<String> llistaArgs = List.from(arguments);

  // L'ordre és el primer argument
  String ordre = llistaArgs[0];

  // Eliminem l'ordre
  llistaArgs.removeAt(0);

  // I reconstruim la resta d'arguments com un String,
  // separant-los amb un espai.
  String args = llistaArgs.join(" ");

  ComarquesAPI api = ComarquesAPI();

  switch (ordre) {
    case 'comarques':
      await api.getComarques(args).catchError((err) {
        print('Error: $err');
        exit(1);
      });
      break;
    case 'infocomarca':
      await api.getInfoComarca(args).catchError((err) {
        print('Error: $err');
        exit(1);
      });
      break;
    default:
      print('Ordre desconeguda: $ordre');
      exit(1);
  }
}