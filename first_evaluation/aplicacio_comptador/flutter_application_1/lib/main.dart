import 'package:flutter/material.dart';

void main() {
  runApp(NewWidget());
}

// cntl + . move "" to file
class NewWidget extends StatefulWidget {
  const NewWidget({
    super.key,
  });

  @override
  State<NewWidget> createState() => _NewWidgetState();
}

class _NewWidgetState extends State<NewWidget> {
  int comptador = 0;
  @override
  Widget build(BuildContext context) {
   
    return MaterialApp(
    title: 'New Widget',
    home: Scaffold(
      appBar: AppBar(
        title: Text("El meu comptador"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Numero de clicks"),
            Text('0',
            style: TextStyle(fontSize: 50),
            )
          ]
        )
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () {
          setState(() {
            comptador++;
          });
          debugPrint('Log');
        },
      ),
    )
  )
  }
}

@override
Wigdet 
