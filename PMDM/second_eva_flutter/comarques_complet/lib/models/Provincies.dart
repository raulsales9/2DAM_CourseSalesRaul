class Provincies {
  late String? nom;
  String? imatge;

  // Constructor amb arguments amb nom,
  // obligatoris i opcionals
  Provincies({required this.nom, this.imatge});
  Provincies.fromJSON(Map<String, dynamic> objecteJSON) {
    nom = objecteJSON["provincia"] ?? "";
    imatge = objecteJSON["img"] ?? "";
  }

  @override
  String toString() => "$nom; $imatge";
}
