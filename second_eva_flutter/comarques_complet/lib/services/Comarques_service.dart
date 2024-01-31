import 'package:comarques_complet/models/Comarques.dart';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'dart:convert';

class ComarquesService {
  var url1 =
      "https://node-comarques-rest-server-production.up.railway.app/api/comarques/comarquesAmbImatge/"; //València
  var url3 =
      "https://node-comarques-rest-server-production.up.railway.app/api/comarques";

  Future<List<Comarques>> getComarques(String provincia) async {
    try {
      final response = await http.get(Uri.parse(url1 + provincia));

      if (response.statusCode == 200) {
        final List<dynamic> comarquesData = jsonDecode(response.body) as List;

        final List<Comarques> comarquesList = comarquesData
            .map((comarca) =>
                Comarques.fromJSON(comarca as Map<String, dynamic>))
            .toList();
        comarquesList.forEach((comarca) {
          // print(comarca);
        });

        return comarquesList;
      } else {
        throw Exception(
            'Failed to load comarques. Status code: ${response.statusCode}');
      }
    } catch (e) {
      print('Error en la solicitud de comarques: $e');
      throw e;
    }
  }

  Future<Comarques> getInfoComarca(String comarca) async {
    try {
      var encodedComarca = Uri.encodeComponent(comarca);
      var url2 =
          "https://node-comarques-rest-server-production.up.railway.app/api/comarques/infoComarca/$encodedComarca";
      print('URL de la solicitud de infoComarca: $url2');

      final response = await http.get(Uri.parse(url2));

      print('Respuesta de la solicitud de infoComarca: ${response.body}');

      if (response.statusCode == 200) {
        final Map<String, dynamic> comarcaData = jsonDecode(response.body);
        final Comarques comarcaObject = Comarques.fromJSON(comarcaData);
        print(comarcaObject);
        return comarcaObject;
      } else {
        throw Exception(
            'Failed to load comarques. Status code: ${response.statusCode}');
      }
    } catch (e) {
      print('Error en la solicitud de infoComarca: $e');
      throw e;
    }
  }

  Future<List<dynamic>> obtenirProvincies() async {
    final response = await http.get(Uri.parse(url3));
    if (response.statusCode == 200) {
      final provincies = jsonDecode(response.body);
      //print(provincies);
      return provincies;
    } else {
      throw Exception(
          'Failed to load provinces. Status code: ${response.statusCode}');
    }
  }
}
