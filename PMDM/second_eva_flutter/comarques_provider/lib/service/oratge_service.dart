import 'package:comarques_provider/model/infoOratge.dart';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'dart:convert';

class oratgeService{
  Future<dynamic> obteClima(
      {required double longitud, required double latitud}) async {
    try {
      var url4 =
          "https://api.open-meteo.com/v1/forecast?latitude=$latitud&longitude=$longitud&current_weather=true";
      final response = await http.get(Uri.parse(url4));
      if (response.statusCode == HttpStatus.ok) {
        String body = utf8.decode(response.bodyBytes);
        final result = jsonDecode(body);
        return result;
      } else {
        throw Exception(
            'Failed to load weather info. Status code: ${response.statusCode}');
      }
    } catch (e) {
      print('Error al obtener el clima: $e');
      throw e;
    }
  }
}