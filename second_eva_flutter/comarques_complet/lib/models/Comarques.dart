class Comarques {
  late String comarca;
  String? nom;
  String? capital;
  int? poblacio;
  String? img;
  String? desc;
  double? latitud;
  double? longitud;

  Comarques({
    required this.comarca,
    this.capital,
    this.poblacio,
    this.img,
    this.desc,
    this.latitud,
    this.longitud,
  });

  Comarques.fromJSON(Map<String, dynamic> json) {
    comarca = json['comarca'] ?? 'N/A';
    capital = json['capital'] ?? 'N/A';
    poblacio = int.parse(json["poblacio"].replaceAll(".", ""));
    img = json['img'] ?? 'N/A';
    desc = json['desc'] ?? 'N/A';
    latitud = json['latitud'] ?? 0.0;
    longitud = json['longitud'] ?? 0.0;
  }
  @override
  String toString() {
    return '''\x1B[34mnom:\t\t\x1B[36m$comarca\n\x1B[0m
\x1B[34mcapital:\t\x1B[36m$capital\n\x1B[0m
\x1B[34mpoblacio:\t\x1B[36m${poblacio.toString()}\n\x1B[0m
\x1B[34mImatge:\t\t\x1B[36m$img\n\x1B[0m
\x1B[34mdescripció:\t\x1B[36m$desc\n\x1B[0m
\x1B[34mCoordenades:\t\x1B[36m($latitud, $longitud)\x1B[0m''';
  }
}
