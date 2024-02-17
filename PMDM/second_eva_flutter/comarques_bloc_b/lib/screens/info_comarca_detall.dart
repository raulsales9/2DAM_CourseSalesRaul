import 'package:comarques_bloc_b/bloc/oratge_bloc.dart';
import 'package:comarques_bloc_b/model/InfoOratge.dart';
import 'package:comarques_bloc_b/model/comarca.dart';
import 'package:flutter/material.dart';


enum TipoViento {
  tramuntana,
  gregal,
  levante,
  ponent,
  mestral,
}
class ComarcaDetailScreen2 extends StatefulWidget {
  @override
  _ComarcaDetailScreenState createState() => _ComarcaDetailScreenState();
}

class _ComarcaDetailScreenState extends State<ComarcaDetailScreen2> {
  final OratgeBloc _oratgeBloc = OratgeBloc();

  @override
  void dispose() {
    _oratgeBloc.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Detalle de Comarca"),
      ),
      body: StreamBuilder<InfoOratge?>(
        stream: _oratgeBloc.infoOratgeStream,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return CircularProgressIndicator();
          } else if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          } else {
            final InfoOratge? clima = snapshot.data;
            final Comarca? comarcaActual = _oratgeBloc.comarcaActual;

            return Center(
              child: Container(
                color: Colors.white.withOpacity(0.8),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    _buildClimaWidget(clima, comarcaActual),
                  ],
                ),
              ),
            );
          }
        },
      ),
    );
  }

  Widget _buildClimaWidget(InfoOratge? clima, Comarca? comarca) {
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
                Text('${clima.temperatura}°C', style: TextStyle(fontSize: 24.0)),
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
                      Text('Población: ${comarca?.poblacio ?? ""}', style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const SizedBox(width: 8),
                      Text('Latitud: ${comarca?.latitud ?? ""}', style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const SizedBox(width: 8),
                      Text('Longitud: ${comarca?.longitud ?? ""}', style: TextStyle(fontSize: 24.0)),
                    ],
                  ),
                  SizedBox(height: 8),
                  _buildClimaDetails(clima),
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
