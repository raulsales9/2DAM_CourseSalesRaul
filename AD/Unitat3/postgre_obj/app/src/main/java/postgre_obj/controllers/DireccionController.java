package postgre_obj.controllers;

import postgre_obj.entities.Direccion;
import java.util.List;
import java.util.ArrayList;

public class DireccionController {

    private List<Direccion> direcciones = new ArrayList<>();

    public List<Direccion> getAllDirecciones() {
        return direcciones;
    }

    public Direccion getDireccion(String direccion) {
        for (Direccion dir : direcciones) {
            if (dir.getDireccion().equals(direccion)) {
                return dir;
            }
        }
        return null;
    }

    public void addDireccion(Direccion direccion) {
        direcciones.add(direccion);
    }

    public void updateDireccion(Direccion direccion) {
        for (int i = 0; i < direcciones.size(); i++) {
            if (direcciones.get(i).getDireccion().equals(direccion.getDireccion())) {
                direcciones.set(i, direccion);
                break;
            }
        }
    }

    public void deleteDireccion(String direccion) {
        direcciones.removeIf(dir -> dir.getDireccion().equals(direccion));
    }
}
