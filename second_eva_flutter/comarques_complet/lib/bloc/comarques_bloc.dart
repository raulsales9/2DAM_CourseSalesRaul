import 'dart:async';
import 'package:comarques_complet/models/Provincies.dart';
import 'package:comarques_complet/models/Comarques.dart';
import 'package:comarques_complet/services/Comarques_service.dart';

class ComarquesBloc {
  //referencia al nostre repo
  final _comarquesRepository = ComarquesService();
  List<dynamic>? _llistaComarques;
  String? _provinciaActual;
  String? _nomComarcaActual;
  Comarques? comarcaActual;

  static ComarquesBloc? _comarquesBloc;
  factory ComarquesBloc() {
    _comarquesBloc ??= ComarquesBloc._();
    return _comarquesBloc!;
  }

// Controlador per a la llista de provincies
  final _provinciesController = StreamController<List<Provincies>>.broadcast();

// Controlador per a la llista de comarques de la província actual
  final _comarquesController = StreamController<List<dynamic>?>.broadcast();

// Controlador per a la informació de la comarca actual
  final _comarcaActualController = StreamController<Comarques>.broadcast();

// Getter per a l'Stream de _provinciesController:
// Retorna l'stream sobre el qual s'emetrà la llista de províncies
// en la creació del BLoC.
// Aquest mètode l'utilitzarà la pantalla de selecció de províncies.
  Stream<List<Provincies>> get obtenirProvincies =>
      _provinciesController.stream;

// Getter per a l'Stream de _comarquesController:
// Retorna l'stream sobre el qual s'emetrà la llista amb el nom i
// la imatge descriptiva de cada comarca quan se seleccione una província.
// Aquest mètode l'utilitzarà la pantalla de selecció de comarques.
  Stream<List<dynamic>?> get obtenirComarquesStream =>
      _comarquesController.stream;

// Getter per a l'Stream de _comarcaActualController
// Retorna l'stream sobre el qual s'emetrà la informació
// completa de la comarca seleccionada al selector de comarques.
// Aquest mètode l'utilitzarà la pantalla que mostra la informació
// sobre la comarca (la pestanya general).

  Stream<Comarques?> get obtenirComarcaStream =>
      _comarcaActualController.stream;

//Càrrega inicial de les províncies
//El constructor privat del BLoC que hem implementat per al patró Singleton, invoca el mètode carregaProvincies:
  ComarquesBloc._();

  set nomComarcaActual(String? comarca) {
    if (comarca != null) {
      if (_nomComarcaActual != comarca) {
        _nomComarcaActual = comarca;
        carregaComarca(comarca);
      } else {
        actualitzaComarca();
      }
    }
  }

  set provinciaActual(String? provincia) {
    if (provincia != null) {
      if (_provinciaActual != provincia) {
        _provinciaActual = provincia;
        carregaComarques(_provinciaActual!);
      } else {
        actualitzaComarques();
      }
    }
  }

  void carregaProvincies() async {
    // Obtenim les províncies de del mètode corresponent del repositori
    List<dynamic> jsonProvincies =
        await _comarquesRepository.obtenirProvincies();

    //  El mapegem a una llista de províncies
    List<Provincies> provincies = List<Provincies>.from(
        jsonProvincies.map((provincia) => Provincies.fromJSON(provincia)));

    // I l'emetem l'Stream de les províncies
    _provinciesController.sink.add(provincies);
  }

  void carregaComarques(String provincia) async {
    List<dynamic> jsonComarques =
        await _comarquesRepository.getComarques(provincia);
    _llistaComarques = jsonComarques;

    // Emetem la llista per l'Stream corresponent
    _comarquesController.sink.add(_llistaComarques);
  }

  void actualitzaComarques() async {
    // Afegim una espera asíncrona de durada zero (Zero-Duration Delay)
    await Future.delayed(Duration.zero);
    // Emetem llista de comarques actual per l'Stream
    _comarquesController.sink.add(_llistaComarques);
  }

  void carregaComarca(String comarca) async {
    // Obtenim la informació sobre la comarca a través del repositori
    // i l'afegim a l'Stream del controlador corresponent.
    Comarques? infoComarca = await _comarquesRepository.getInfoComarca(comarca);
    comarcaActual = infoComarca;
    _comarcaActualController.sink.add(comarcaActual!);
  }

  void actualitzaComarca() async {
    // Afegim una espera asíncrona de durada zero (Zero-Duration Delay)
    await Future.delayed(Duration.zero);
    // Emetem la informació de la comarca actual per l'Stream de les comarques
    _comarcaActualController.sink.add(comarcaActual!);
  }

  void dispose() {
    _provinciesController.close();
    _comarquesController.close();
    _comarcaActualController.close();
  }
}
