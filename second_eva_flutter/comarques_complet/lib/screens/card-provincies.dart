/* import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'comarques.dart';

void main() {
  runApp(ProvinciesScreen2());
}

class ProvinciaWidget extends StatelessWidget {
  final String provincia;
  final String imageUrl;

  const ProvinciaWidget({
    Key? key,
    required this.provincia,
    required this.imageUrl,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(8.0),
      child: ListTile(
        leading: CircleAvatar(
          backgroundImage: NetworkImage(imageUrl),
          radius: 40.0,
        ),
        title: Center(
          child: Text(provincia),
        ),
      ),
    );
  }
}

class ProvinciesScreen2 extends StatelessWidget {
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
        child: ListView.builder(
          itemCount: provincies.length,
          itemBuilder: (context, index) {
            return ProvinciaWidget(
              provincia: provincies[index]['provincia'],
              imageUrl: provincies[index]['img'],
            );
          },
        ),
      ),
    );
  }
}
 */