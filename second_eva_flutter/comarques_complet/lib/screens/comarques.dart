import 'package:flutter/material.dart';
import 'package:comarques_complet/models/Comarques.dart';
import 'info_comarca_1.dart';
import 'package:comarques_complet/services/Comarques_service.dart';

class ComarquesScreen extends StatefulWidget {
  final String provincia;

  ComarquesScreen({required this.provincia});

  @override
  _ComarquesScreenState createState() => _ComarquesScreenState();
}

class _ComarquesScreenState extends State<ComarquesScreen> {
  late Future<List<Comarques>> comarquesFuture;
  late ComarquesService comarquesAPI = ComarquesService();

  @override
  void initState() {
    super.initState();
    if (widget.provincia.isNotEmpty) {
      comarquesFuture = comarquesAPI.getComarques(widget.provincia);
    } else {
      comarquesFuture = Future.value([]);
      print('La lista de comarcas está vacía');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Comarques'),
      ),
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("../assets/img/background_webapp.webp"),
            fit: BoxFit.fill,
          ),
        ),
        child: FutureBuilder<List<Comarques>>(
          future: comarquesFuture,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(
                child: CircularProgressIndicator(),
              );
            } else if (snapshot.hasError) {
              return Center(
                child: Text('Error al cargar las comarcas: ${snapshot.error}'),
              );
            } else {
              List<Comarques> comarques = snapshot.data ?? [];

              if (comarques.isEmpty) {
                return Center(
                  child: Text('No se encontraron comarcas.'),
                );
              }
              return ListView.builder(
                itemCount: comarques.length,
                itemBuilder: (context, index) {
                  Comarques comarca = comarques[index];
                  return ComarcaWidget(
                    comarca: comarca,
                    onTap: () {
                      if (comarca.nom != null && comarca.nom != "N/A") {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => InfoComarcaScreen(
                              comarcaNombre: comarca.nom,
                            ),
                          ),
                        );
                      }
                    },
                  );
                },
              );
            }
          },
        ),
      ),
    );
  }
}

class ComarcaWidget extends StatelessWidget {
  final Comarques comarca;
  final VoidCallback onTap;

  ComarcaWidget({
    required this.comarca,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      child: Card(
        child: Container(
          height: 220.0,
          width: 220.0,
          decoration: BoxDecoration(
            image: DecorationImage(
              image: NetworkImage(comarca.img ?? "default"),
              fit: BoxFit.fill,
            ),
          ),
          child: ListTile(
            title: Text(
              comarca.nom ?? "default",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
