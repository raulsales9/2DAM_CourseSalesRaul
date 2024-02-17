import 'package:comarques_bloc_b/bloc/comarques_bloc.dart';
import 'package:comarques_bloc_b/model/comarca.dart';
import 'package:flutter/material.dart';

class InfoComarcaGeneral extends StatelessWidget {
  InfoComarcaGeneral({
    super.key,
  });

  // Definim una referència al BLoC
  final ComarquesBloc comarquesBloc = ComarquesBloc();

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: StreamBuilder(
        stream: comarquesBloc.obtenirComarcaStream,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.active &&
              snapshot.hasData) {
            Comarca? comarca = snapshot.data;
            return Container(
              margin: const EdgeInsets.all(16.0),
              decoration: const BoxDecoration(
                  color: Color.fromARGB(200, 255, 255, 255)),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const SizedBox(height: 80),
                  Image.network(comarca?.img ?? ""),
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
                          comarca?.comarca ?? "",
                          style: Theme.of(context).textTheme.headlineMedium,
                        ),
                        const SizedBox(
                          height: 10,
                        ),
                        Text(
                          'Capital: ${comarca?.capital}',
                          style: Theme.of(context).textTheme.headlineSmall,
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        Text(
                          comarca?.desc ?? "",
                          textAlign: TextAlign.justify,
                          style: Theme.of(context).textTheme.bodyMedium,
                        )
                      ],
                    ),
                  ),
                ],
              ),
            );
          } else {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      ),
    );
  }
}
