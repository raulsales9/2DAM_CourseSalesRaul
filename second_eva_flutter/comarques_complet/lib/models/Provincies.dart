class Provincies {
  late String? nom;
  String? imatge;

  Provincies({required this.nom, this.imatge});
  Provincies.fromJSON(Map<String, dynamic> objecteJSON) {
    nom = objecteJSON["provincia"] ?? "";
    imatge = objecteJSON["img"] ?? "";
  }

  @override
  String toString() => "$nom; $imatge";
}
