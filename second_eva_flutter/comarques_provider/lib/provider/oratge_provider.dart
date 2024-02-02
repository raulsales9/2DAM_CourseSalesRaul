import 'dart:async';
import 'package:comarques_provider/model/comarca.dart';
import 'package:comarques_provider/model/infoOratge.dart';

import 'package:comarques_provider/service/oratge_service.dart';
import 'package:flutter/foundation.dart';

class InfoOratgeProvider with ChangeNotifier {
  final oratgeService _oratgeRepository = oratgeService();

  Comarca? _comarcaActual;
  InfoOratge? _infoOratge;
  Comarca? get comarcaActual => _comarcaActual;
  final _infoOratgeController = StreamController<InfoOratge?>.broadcast();
  InfoOratge? get infoOratge => _infoOratge;
  get infoOratgeStream => _infoOratgeController.stream;

  void carregaInfoOratge(Comarca comarca) async {
    notifyListeners();

    try {
      _infoOratge = await _oratgeRepository.obteClima(
          longitud: comarca.longitud ?? 0, latitud: comarca.latitud ?? 0);
      _infoOratgeController.add(_infoOratge);
    } catch (error) {
    } finally {
      notifyListeners();
    }
  }

  @override
  void dispose() {
    _infoOratgeController.close();
    super.dispose();
  }
}
