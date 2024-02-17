import 'package:comarques_provider/model/comarca.dart';
import 'package:comarques_provider/model/infoOratge.dart';
import 'package:comarques_provider/provider/oratge_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../provider/comarques_provider.dart';

enum TipoViento {
  tramuntana,
  gregal,
  levante,
  ponent,
  mestral,
}

class ComarcaDetailScreen2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final comarquesProvider = Provider.of<ComarquesProvider>(context);
    // final infoOratgeProvider = InfoOratgeProvider();
    final infoOratgeProvider = Provider.of<InfoOratgeProvider>(context);
    debugPrint(infoOratgeProvider.infoOratge.toString());
    debugPrint(comarquesProvider.comarcaActual.toString());
    return Scaffold(
      appBar: AppBar(
        title: Text(""),
      ),
      body: Center(
        child: Container(
          color: Colors.white.withOpacity(0.8),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              //_buildClimaWidget(infoOratgeProvider, comarquesProvider),
              _buildClimaWidget(infoOratgeProvider.infoOratge,
                  comarquesProvider.comarcaActual),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildClimaWidget(InfoOratge? clima, Comarca? comarca) {
    //final clima = infoOratgeProvider.infoOratge;
    if (clima == null) {
      return CircularProgressIndicator();
    } else {
      return Column(
        children: [
          ListTile(
            title: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(Icons.thermostat_outlined),
                SizedBox(width: 8),
                Text('${clima.temperatura}°C',
                    style: TextStyle(fontSize: 24.0)),
              ],
            ),
          ),
          ListTile(
            title: Center(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  SizedBox(height: 8),
                  const SizedBox(height: 8),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const SizedBox(width: 8),
                      //Text('Población: ${comarquesProvider.comarcaActual?.poblacio ?? ""}', style: TextStyle(fontSize: 24.0)),
                      Text('Población: ${comarca?.poblacio ?? ""}',
                          style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const SizedBox(width: 8),
                      //Text('Latitud: ${comarquesProvider.comarcaActual?.latitud ?? ""}', style: TextStyle(fontSize: 24.0)),
                      Text('Latitud: ${comarca?.latitud ?? ""}',
                          style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const SizedBox(width: 8),
                      //Text('Longitud: ${comarquesProvider.comarcaActual?.longitud ?? ""}', style: TextStyle(fontSize: 24.0)),
                      Text('Longitud: ${comarca?.longitud ?? ""}',
                          style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  SizedBox(height: 8),
                  _buildClimaDetails(clima as InfoOratge),
                  const SizedBox(height: 8),
                ],
              ),
            ),
          ),
        ],
      );
    }
  }

  Widget _buildClimaDetails(InfoOratge clima) {
    TipoViento tipoViento = obtenerTipoViento(clima.direccioVent ?? "");
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Icon(Icons.air),
        SizedBox(width: 8),
        Text('${clima.direccioVent ?? ""}°', style: TextStyle(fontSize: 24.0)),
        SizedBox(width: 8),
        Text(_getTextoTipoViento(tipoViento), style: TextStyle(fontSize: 24.0)),
        Icon(Icons.arrow_upward),
      ],
    );
  }

  String _getTextoTipoViento(TipoViento tipoViento) {
    switch (tipoViento) {
      case TipoViento.tramuntana:
        return 'Tramuntana';
      case TipoViento.gregal:
        return 'Gregal';
      case TipoViento.levante:
        return 'Levante';
      case TipoViento.ponent:
        return 'Ponent';
      case TipoViento.mestral:
        return 'Mestral';
      default:
        return "No hay datos";
    }
  }

  TipoViento obtenerTipoViento(dynamic direccion) {
    if (direccion is num) {
      if ((direccion >= 337.5 && direccion <= 360) ||
          (direccion >= 0 && direccion < 22.5)) {
        return TipoViento.tramuntana;
      } else if (direccion >= 22.5 && direccion < 67.5) {
        return TipoViento.gregal;
      } else if (direccion >= 67.5 && direccion < 112.5) {
        return TipoViento.levante;
      } else if (direccion >= 112.5 && direccion < 157.5) {
        return TipoViento.ponent;
      } else if (direccion >= 157.5 && direccion < 202.5) {
        return TipoViento.mestral;
      } else {
        return TipoViento.tramuntana;
      }
    } else {
      return TipoViento.tramuntana;
    }
  }
}
