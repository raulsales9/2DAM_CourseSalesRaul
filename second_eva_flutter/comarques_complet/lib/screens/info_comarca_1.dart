import 'package:flutter/material.dart';
import 'info_comarca_2.dart';
import 'package:comarques_complet/models/Comarques.dart';
import 'package:comarques_complet/services/Comarques_service.dart';

class InfoComarcaScreen extends StatefulWidget {
  final String? comarcaNombre;

  InfoComarcaScreen({this.comarcaNombre});

  @override
  _InfoComarcaScreenState createState() => _InfoComarcaScreenState();
}

class _InfoComarcaScreenState extends State<InfoComarcaScreen> {
  int _selectedIndex = 0;
  late Comarques? infocomarca;

  @override
  void initState() {
    super.initState();
    if (widget.comarcaNombre != null) {
      getInfoComarca(widget.comarcaNombre!).then((comarques) {
        setState(() {
          infocomarca = comarques;
        });
      });
    }
    }
  

void _onItemTapped(int index) {
  setState(() {
    _selectedIndex = index;
  });
  switch (index) {
    case 0:
      break;
    case 1:
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => InfoComarcaScreen2(
            comarcaData: infocomarca, 
          ),
        ),
      );
      break;
  }
}



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Info de la comarca'),
      ),
      body: FutureBuilder(
        future: infocomarca,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(
              child: CircularProgressIndicator(),
            );
          } else if (snapshot.hasError) {
            return Center(
              child: Text(
                'Error al obtener la información de la comarca: ${snapshot.error}',
              ),
            );
          } else {
            return InfoComarcaWidget(comarquesList: snapshot.data);
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
}

class InfoComarcaWidget extends StatelessWidget {
  final List<Comarques>? comarquesList;

  InfoComarcaWidget({this.comarquesList});

  @override
  Widget build(BuildContext context) {
    if (comarquesList != null && comarquesList!.isNotEmpty) {
      Comarques comarca = comarquesList![0];
      return Container(
        width: double.infinity,
        height: double.infinity,
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("../assets/img/background_webapp.webp"),
            fit: BoxFit.cover,
          ),
        ),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                height: 200,
                width: double.infinity,
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: NetworkImage(
                        comarca.img ?? 'URL_IMAGEN_POR_DEFECTO'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Capital: ${comarca.capital ?? 'N/A'}',
                      style:
                          TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                    ),
                    SizedBox(height: 8),
                    Text(
                      'Descripción: ${comarca.desc ?? 'N/A'}',
                      style:
                          TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                    ),
                    SizedBox(height: 8),
                    Text(
                      '${comarca.desc ?? 'DESCRIPCION'}',
                      style: TextStyle(fontSize: 16),
                    ),
                    SizedBox(height: 8),
                  ],
                ),
              ),
            ],
          ),
        ),
      );
    } else {
      return Container(
        child: Text('No se encontró información de la comarca.'),
      );
    }
  }
}
