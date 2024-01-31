class Comarques {
  String comarca;
  String? nom;
  String? capital;
  String? poblacio;
  String? img;
  String? desc;
  double? latitud;
  double? longitud;

  Comarques({
    required this.comarca,
    this.nom,
    this.capital,
    this.poblacio,
    this.img,
    this.desc,
    this.latitud,
    this.longitud,
  });

  Comarques.fromJSON(Map<String, dynamic> json)
      : comarca = json['comarca'] ?? 'N/A',
        nom = json['nom'] ?? 'N/A',
        capital = json['capital'] ?? 'N/A',
        poblacio = json['poblacio'] ?? 'N/A',
        img = json['img'] ?? 'N/A',
        desc = json['desc'] ?? 'N/A',
        latitud = json['latitud'],
        longitud = json['longitud'];

  @override
  String toString() {
    return '''
    comarca:        $comarca
    nom:            ${nom ?? 'N/A'}
    capital:        ${capital ?? 'N/A'}
    poblacio:       ${poblacio ?? 'N/A'}
    Imatge:         ${img ?? 'N/A'}
    descripció:     ${desc ?? 'N/A'}
    Coordenades:    (${latitud ?? 'N/A'}, ${longitud ?? 'N/A'})
    ''';
  }
}
