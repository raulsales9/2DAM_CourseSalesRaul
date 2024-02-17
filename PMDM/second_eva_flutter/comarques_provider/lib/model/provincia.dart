class Provincia {
  late String nom;
  String? imatge;

  // Constructor amb arguments amb nom,
  // obligatoris i opcionals

  Provincia({required this.nom, this.imatge});
  Provincia.fromJSON(Map<String, dynamic> objecteJSON) {
    nom = objecteJSON["provincia"] ?? "";
    imatge = objecteJSON["img"] ?? "";
  }

  @override
  String toString() => "$nom; $imatge";
}
