import 'package:flutter/material.dart';
import 'info_comarca_1.dart';
import 'package:comarques_complet/models/Comarques.dart';
import 'package:comarques_complet/services/Comarques_service.dart';
import 'package:comarques_complet/services/Oratge_service.dart';

class InfoComarcaScreen2 extends StatefulWidget {
  final Comarques comarcaData;

  InfoComarcaScreen2({required this.comarcaData});

  @override
  _InfoComarcaScreen2State createState() => _InfoComarcaScreen2State();
}

class _InfoComarcaScreen2State extends State<InfoComarcaScreen2> {
  int _selectedIndex = 1;
  late Future<dynamic> climaFuture;

  @override
  void initState() {
    super.initState();
    climaFuture = obtenerClima();
  }

  Future<dynamic> obtenerClima() async {
    try {
      /* var comarquesAPI = ComarquesAPI(); */
      var clima = oratgeService();
      return clima.obteClima(
        longitud: widget.comarcaData.longitud ?? 0.0,
        latitud: widget.comarcaData.latitud ?? 0.0,
      );
    } catch (e) {
      print('Error al obtener el clima: $e');
      throw e;
    }
  }

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
    switch (index) {
      case 0:
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => InfoComarcaScreen(
              comarcaNombre: widget.comarcaData.comarca,
            ),
          ),
        );
        break;
      case 1:
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.comarcaData.comarca ?? 'Nombre de la comarca'),
      ),
      body: FutureBuilder(
        future: climaFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(
              child: CircularProgressIndicator(),
            );
          } else if (snapshot.hasError) {
            return Center(
              child: Text('Error al obtener el clima: ${snapshot.error}'),
            );
          } else {
            return _buildWeatherWidget(snapshot.data);
          }
        },
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(_selectedIndex == 0 ? Icons.home : Icons.home_outlined),
            label: 'Info Comarques 1',
          ),
          BottomNavigationBarItem(
            icon:
                Icon(_selectedIndex == 1 ? Icons.people : Icons.people_outline),
            label: 'Info Comarques 2',
          ),
        ],
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
      ),
    );
  }

  Widget _buildWeatherWidget(dynamic weatherData) {
    if (weatherData != null) {
      String temperatura =
          weatherData["current_weather"]["temperature"].toString();
      String velVent = weatherData["current_weather"]["windspeed"].toString();
      String direccioVent =
          weatherData["current_weather"]["winddirection"].toString();
      String codi = weatherData["current_weather"]["weathercode"].toString();
      return Column(
        children: [
          _obtenerIconoOratge(codi),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Icon(
                Icons.thermostat,
                size: 35,
              ),
              Text(
                "$temperaturaº",
                style: Theme.of(context).textTheme.headlineMedium,
              ),
            ],
          ),
          const SizedBox(height: 20),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Icon(Icons.wind_power, size: 35),
              const SizedBox(width: 30),
              Text(
                "${velVent}km/h",
                style: Theme.of(context).textTheme.headlineSmall,
              ),
              const SizedBox(width: 30),
              _obtenerGinyDireccioVent(direccioVent),
            ],
          ),
        ],
      );
    } else {
      return const CircularProgressIndicator();
    }
  }

  Widget _obtenerGinyDireccioVent(String direccioVent) {
    double dir = double.parse(direccioVent);
    late Icon icona;
    late String nomVent;

    if (dir > 22.5 && dir < 65.5) {
      icona = const Icon(Icons.north_east);
      nomVent = "Gregal";
    } else if (dir > 67.5 && dir < 112.5) {
      icona = const Icon(Icons.east);
      nomVent = "Llevant";
    } else if (dir > 112.5 && dir < 157.5) {
      icona = const Icon(Icons.south_east);
      nomVent = "Xaloc";
    } else if (dir > 157.5 && dir < 202.5) {
      icona = const Icon(Icons.south);
      nomVent = "Migjorn";
    } else if (dir > 202.5 && dir < 247.5) {
      icona = const Icon(Icons.south_west);
      nomVent = "Llebeig/Garbí";
    } else if (dir > 247.5 && dir < 292.5) {
      icona = const Icon(Icons.west);
      nomVent = "Ponent";
    } else if (dir > 292.5 && dir < 337.5) {
      icona = const Icon(Icons.north_west);
      nomVent = "Mestral";
    } else {
      icona = const Icon(Icons.north);
      nomVent = "Tramuntana";
    }
    return Row(children: [
      Text(
        nomVent,
        style: Theme.of(context).textTheme.headlineSmall,
      ),
      icona,
    ]);
  }

  Widget _obtenerIconoOratge(String value) {
    Set<String> sol = <String>{"0"};
    Set<String> pocsNuvols = <String>{"1", "2", "3"};
    Set<String> nuvols = <String>{"45", "48"};
    Set<String> plujasuau = <String>{"51", "53", "55"};
    Set<String> pluja = <String>{
      "61",
      "63",
      "65",
      "66",
      "67",
      "80",
      "81",
      "82",
      "95",
      "96",
      "99"
    };
    Set<String> neu = <String>{"71", "73", "75", "77", "85", "86"};

    if (sol.contains(value)) {
      return Image.asset("../../assets/icons/png/soleado.png");
    }
    if (pocsNuvols.contains(value)) {
      return Image.asset("../../assets/icons/png/poco_nublado.png");
    }
    if (nuvols.contains(value)) {
      return Image.asset("../../assets/icons/png/nublado.png");
    }
    if (plujasuau.contains(value)) {
      return Image.asset("../../assets/icons/png/lluvia_debil.png");
    }
    if (pluja.contains(value)) {
      return Image.asset("../../assets/icons/png/lluvia.png");
    }
    if (neu.contains(value)) {
      return Image.asset("../../assets/icons/png/nieve.png");
    }

    return Image.asset("../../assets/icons/png/poco_nublado.png");
  }
}
