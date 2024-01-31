import 'package:flutter/material.dart';
import 'package:comarques_complet/screens/comarques.dart'; 
import 'package:comarques_complet/services/Comarques_service.dart';

void main() {
  runApp(ProvinciesApp());
}

class ProvinciesApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ProvinciesScreen(),
    );
  }
}

class ProvinciaWidget extends StatelessWidget {
  final String provincia;
  final String imageUrl;
  final List<Map<String, dynamic>> comarques;

  const ProvinciaWidget({
    Key? key,
    required this.provincia,
    required this.imageUrl,
    required this.comarques,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => ComarquesScreen(provincia: provincia),
          ),
        );
      },
      child: Container(
        margin: EdgeInsets.all(8.0),
        child: Stack(
          alignment: Alignment.center,
          children: [
            Container(
              height: 220.0,
              width: 220.0,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                image: DecorationImage(
                  image: NetworkImage(imageUrl),
                  fit: BoxFit.fill,
                ),
              ),
            ),
            Positioned(
              child: Container(
                padding: EdgeInsets.all(8.0),
                child: Text(
                  provincia,
                  style: TextStyle(fontSize: 16.0, color: Colors.white),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class ProvinciesScreen extends StatefulWidget {
  @override
  _ProvinciesScreenState createState() => _ProvinciesScreenState();
}

class _ProvinciesScreenState extends State<ProvinciesScreen> {
  late Future<List<dynamic>> provinciesFuture;
  late ComarquesService comarquesAPI = ComarquesService();

  @override
  void initState() {
    super.initState();
    provinciesFuture = comarquesAPI.obtenirProvincies();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Provincias'),
      ),
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("../assets/img/background_webapp.webp"),
            fit: BoxFit.fill,
          ),
        ),
        child: FutureBuilder<List<dynamic>>(
          future: provinciesFuture,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(
                child: CircularProgressIndicator(),
              );
            } else if (snapshot.hasError) {
              return Center(
                child: Text('Error: ${snapshot.error}'),
              );
            } else {
              List<dynamic> provincies = snapshot.data ?? [];
              return ListView.builder(
                itemCount: provincies.length,
                itemBuilder: (context, index) {
                  List<Map<String, dynamic>> comarques =
                      provincies[index]['comarques'] ?? [];
                  return ProvinciaWidget(
                    provincia: provincies[index]['provincia'],
                    imageUrl: provincies[index]['img'],
                    comarques: comarques,
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

