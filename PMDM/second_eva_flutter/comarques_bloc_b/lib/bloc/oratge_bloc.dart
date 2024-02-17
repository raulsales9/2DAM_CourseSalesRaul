import 'dart:async';
import 'package:comarques_bloc_b/model/InfoOratge.dart';
import 'package:comarques_bloc_b/model/comarca.dart';
import 'package:comarques_bloc_b/service/oratge_service.dart';

class OratgeBloc {
  final _oratgeRepository = OratgeRepository();
  Comarca? _comarcaActual;
  InfoOratge? _infoOratge;

  final _infoOratgeController = StreamController<InfoOratge?>.broadcast();
  Stream<InfoOratge?> get infoOratgeStream => _infoOratgeController.stream;

  OratgeBloc._(); // Singleton

  static OratgeBloc? _instance;

  factory OratgeBloc() {
    _instance ??= OratgeBloc._();
    return _instance!;
  }

  set comarcaActual(Comarca? comarca) {
    if (comarca != null) {
      if (_comarcaActual != comarca) {
        _comarcaActual = comarca;
        carregaOratge(comarca);
      } else {
        actualitzaOratge();
      }
    }
  }

  void carregaOratge(Comarca comarca) async {
    try {
      dynamic result = await _oratgeRepository.obteClima(
        longitud: comarca.longitud ?? 0,
        latitud: comarca.latitud ?? 0,
      );

      InfoOratge infoOratge = InfoOratge(
        temperatura: result['temperatura'],
        velocitatVent: result['velocitatVent'],
        direccioVent: result['direccioVent'],
        codiOratge: result['codiOratge'],
      );

      _infoOratge = infoOratge;
      _infoOratgeController.add(infoOratge);
    } catch (e) {
      print('Error al obtener la información del tiempo: $e');
    }
  }

  void actualitzaOratge() async {
    await Future.delayed(Duration.zero);
    _infoOratgeController.add(_infoOratge);
  }

  Comarca? get comarcaActual => _comarcaActual;

  void actualizarComarcaActual(Comarca comarca) {
    _comarcaActual = comarca;
  }

  void dispose() {
    _infoOratgeController.close();
  }
}
