
import 'package:comarques_provider/model/comarca.dart';
import 'package:comarques_provider/model/provincia.dart';
import 'package:comarques_provider/service/comarques_service.dart';
import 'package:flutter/foundation.dart';

class ComarquesProvider with ChangeNotifier {
  // Referència al repositori
  final _comarquesRepository = ComarquesRepository();

  // Propietats per emmagatzemar l'estat al Provider
  String? _provinciaActual;
  List<dynamic>? llistaComarques;
  String? _nomComarcaActual;
  Comarca? comarcaActual;
  List<Provincia>? provincies;

  ComarquesProvider() {
    // En el moment que creem el provider
    // caldrà carregar la llista de províncies
    _carregaProvincies();
  }

  /* Mètodes Accessors */
  set provinciaActual(String provincia) {
    // Actualitzem la província actual
    if (_provinciaActual == null || _provinciaActual != provincia) {
      llistaComarques =
          null; // Amb això fem que aparega l'indicador de progrés en lloc de les comarques anteriors
      _provinciaActual = provincia;

      // Carreguem la llista de comarques de la província
      _carregaComarques(provincia);
    }
  }

  String get nomComarcaActual {
    return _nomComarcaActual!;
  }

  set nomComarcaActual(String comarca) {
    // Actualitzem la comarca actual

    if (_nomComarcaActual == null || _nomComarcaActual != comarca) {
      _nomComarcaActual = comarca;

      comarcaActual = null;

      // Carreguem la informació de la comarca
      _carregaComarca(comarca);
    }
  }

  /* Altres mètodes */

  void _carregaProvincies() async {
    // Obtenim les províncies de del mètode corresponent del repositori

    List<dynamic> jsonProvincies =
        await _comarquesRepository.obtenirProvincies();
    //  El mapegem a una llista de províncies
    provincies = List<Provincia>.from(
        jsonProvincies.map((provincia) => Provincia.fromJSON(provincia)));

    notifyListeners();
  }

  void _carregaComarques(String provincia) async {
    // Carreguem la llista de comarques
    List<dynamic> jsonComarques =
        await _comarquesRepository.obtenirComarques(provincia);
    // Actualitzem l'estat
    llistaComarques = jsonComarques;
    // I notifiquem a tots els listeners que estiguen pendents del Provider
    notifyListeners();
  }

  void _carregaComarca(String comarca) async {
    // Obtenim la informació sobre la comarca a través del repositori,
    // l'afegim a la propietat infoComarca i actualitzem el nom de la
    // comarca actual.

    comarcaActual = await _comarquesRepository.infoComarca(comarca);

    // Notifiquem als consumidors d'aquest provider
    notifyListeners();
  }
}
