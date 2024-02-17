import 'dart:convert'; // Per realitzar conversions entre tipus de dades
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'package:comarques_provider/model/comarca.dart';

class ComarquesRepository {
  
  Future<List<dynamic>> obtenirComarques(String provincia) async {
    String url =
        'https://node-comarques-rest-server-production.up.railway.app/api/comarques/comarquesAmbImatge/$provincia';

    http.Response data = await http.get(Uri.parse(url));
    if (data.statusCode == HttpStatus.ok) {
      String body = utf8.decode(data.bodyBytes);
      final bodyJSON = jsonDecode(body) as List;
      return bodyJSON;
    } else {
      return [];
    }
  }

  Future<Comarca?> infoComarca(String comarca) async {
    String url =
        'https://node-comarques-rest-server-production.up.railway.app/api/comarques/infoComarca/$comarca';

    var data = await http.get(Uri.parse(url));

    if (data.statusCode == 200) {
      String body = utf8.decode(data.bodyBytes);
      final bodyJSON = jsonDecode(body);

      // Comarca comarca2 = Comarca.fromJSON(bodyJSON);

      Comarca comarca = Comarca(
        comarca: bodyJSON["comarca"],
        capital: bodyJSON["capital"],
        poblacio: int.parse(bodyJSON["poblacio"].replaceAll(".", "")),
        img: bodyJSON["img"],
        desc: bodyJSON["desc"],
        latitud: bodyJSON["latitud"],
        longitud: bodyJSON["longitud"],
      );
      return comarca;
    } else {
      return null;
    }
  }

  Future<List<dynamic>> obtenirProvincies() async {
    /// Obté la llista de províncies
    try {
      // Nota: Per a que funcione en web cal afegir al web/manifest.json la línia "permissions": ["http://*/", "https://*/"],
      String url =
          "https://node-comarques-rest-server-production.up.railway.app/api/comarques";
      var data = await http.get(Uri.parse(url));

      if (data.statusCode == 200) {
        String body = utf8.decode(data.bodyBytes);
        final bodyJSON = jsonDecode(body);
        return bodyJSON;
      } else {
        return [];
      }
    } catch (except) {
      debugPrint(except.toString());
      return [];
    }
  }
}
