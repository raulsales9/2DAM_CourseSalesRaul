import 'package:http/http.dart' as http;
import 'package:projecte_exemple/models/Comarques.dart';
import 'dart:convert';

class ComarquesAPI {
  var url1 =
      "https://node-comarques-rest-server-production.up.railway.app/api/comarques/comarquesAmbImatge/"; //València
  var url2 =
      "https://node-comarques-rest-server-production.up.railway.app/api/comarques/infoComarca/"; //La%20Ribera%20Baixa

  Future<void> getComarques(String provincia) async {
    final response = await http.get(Uri.parse(url1 + provincia));
    //print('Response status: ${response.statusCode}');
    //print('Response body: ${response.body}');
    if (response.statusCode == 200) {
      final comarques = jsonDecode(response.body) as List;
      comarques.forEach((comarca) => print(Comarques.fromJSON(comarca)));
    } else {
      throw Exception(
          'Failed to load comarques. Status code: ${response.statusCode}');
    }
  }

  Future<void> getInfoComarca(String comarca) async {
    final response = await http.get(Uri.parse(url2 + comarca));
    //print('Response status: ${response.statusCode}');
    //print('Response body: ${response.body}');
    if (response.statusCode == 200) {
      final comarcaInfo = Comarques.fromJSON(jsonDecode(response.body));
      print(comarcaInfo);
    } else {
      throw Exception(
          'Failed to load comarca info. Status code: ${response.statusCode}');
    }
  }
}
