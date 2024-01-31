import 'package:comarques_complet/models/infooratge.dart';
import 'package:comarques_complet/services/Oratge_service.dart';
import 'package:comarques_complet/models/Comarques.dart';

class OratgeBloc{
  static OratgeBloc? _oratgeBloc;
  factory OratgeBloc() {
    _oratgeBloc ??= OratgeBloc._();
    return _oratgeBloc!;
}
  final _oratgeRepository = oratgeService();
  Comarques? comarcaActual = Comarques(comarca: Comarques);
  final _infoOratgeController;
  infooratge? infoOratge;

  get obtenirInfoOratgeStream(){}
  set comarcaActual(Comarques? comarca){}
  void carregaOratge(Comarques comarca) async{}
  void actualitzaOratge() async{}
}