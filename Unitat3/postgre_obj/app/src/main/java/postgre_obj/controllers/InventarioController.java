package postgre_obj.controllers;

import postgre_obj.entities.Inventario;
import java.util.List;
import java.util.ArrayList;

public class InventarioController {

    private List<Inventario> inventarios = new ArrayList<>();

    public List<Inventario> getAllInventarios() {
        return inventarios;
    }

    public Inventario getInventario(int id) {
        for (Inventario inventario : inventarios) {
            if (inventario.getId() == id) {
                return inventario;
            }
        }
        return null;
    }

    public void addInventario(Inventario inventario) {
        inventarios.add(inventario);
    }

    public void updateInventario(Inventario inventario) {
        for (int i = 0; i < inventarios.size(); i++) {
            if (inventarios.get(i).getId() == inventario.getId()) {
                inventarios.set(i, inventario);
                break;
            }
        }
    }

    public void deleteInventario(int id) {
        inventarios.removeIf(inventario -> inventario.getId() == id);
    }
}
