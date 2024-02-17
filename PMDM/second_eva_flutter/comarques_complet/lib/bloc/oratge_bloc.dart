import 'dart:async';
import 'package:comarques_complet/models/infooratge.dart';
import 'package:comarques_complet/services/oratge_service.dart';
import 'package:comarques_complet/models/comarques.dart';

class OratgeBloc {
  static OratgeBloc? _oratgeBloc;
  factory OratgeBloc() {
    _oratgeBloc ??= OratgeBloc._();
    return _oratgeBloc!;
  }

  OratgeBloc._() : _infoOratgeController = StreamController<infooratge>();

  final _oratgeRepository = oratgeService();
  Comarques? _comarcaActual;
  infooratge? infoOratge;

  final StreamController<infooratge> _infoOratgeController;

  Stream<infooratge> get obtenirInfoOratgeStream =>
      _infoOratgeController.stream;

  set comarcaActual(Comarques? comarca) {
    if (_comarcaActual != comarca) {
      _comarcaActual = comarca;
      carregaOratge(comarca!);
    } else {
      actualitzaOratge();
    }
  }

  void carregaOratge(Comarques comarca) async {
    try {
      if (comarca.longitud != null && comarca.latitud != null) {
        infooratge oratgeInfo = await _oratgeRepository.obteClima(
          longitud: comarca.longitud!,
          latitud: comarca.latitud!,
        );
        _infoOratgeController.add(oratgeInfo);
      } else {
        print('Las coordenadas de la comarca son nulas.');
      }
    } catch (e) {
      print('Error al carregar la informació de l\'oratge: $e');
    }
  }

  void actualitzaOratge() async {
    if (_comarcaActual != null) {
      carregaOratge(_comarcaActual!);
    }
  }

  void dispose() {
    _infoOratgeController.close();
  }
}
