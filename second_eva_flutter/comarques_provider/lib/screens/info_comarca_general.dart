import 'package:comarques_provider/provider/comarques_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class InfoComarcaGeneral extends StatelessWidget {
  InfoComarcaGeneral({
    super.key,
    //required this.comarca,
  });

  @override
  Widget build(BuildContext context) {
    // Creem una instància del Provider
    var comarquesProvider = Provider.of<ComarquesProvider>(context);

    if (comarquesProvider.comarcaActual == null) {
      return const Center(child: CircularProgressIndicator());
    } else {
      return SingleChildScrollView(
        child: Container(
              margin: const EdgeInsets.all(16.0),
              decoration: const BoxDecoration(
                  color: Color.fromARGB(200, 255, 255, 255)),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const SizedBox(height: 80),
                  Image.network(comarquesProvider.comarcaActual?.img ?? ""),
                  Padding(
                    padding: const EdgeInsets.all(24.0),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const SizedBox(
                          height: 20,
                        ),
                        Text(
                          comarquesProvider.comarcaActual?.comarca ?? "",
                          style: Theme.of(context).textTheme.headlineMedium,
                        ),
                        const SizedBox(
                          height: 10,
                        ),
                        Text(
                          //'Capital: tralari',
                          'Capital: ${comarquesProvider.comarcaActual?.capital ?? ""}',
                          style: Theme.of(context).textTheme.headlineSmall,
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        Text(
                          comarquesProvider.comarcaActual?.desc ?? "",
                          textAlign: TextAlign.justify,
                          style: Theme.of(context).textTheme.bodyMedium,
                        )
                      ],
                    ),
                  ),
                ],
              ),
            ),
      );
    }
  }
}
