package postgre_obj.controllers;

import postgre_obj.entities.Proveedor;
import java.util.List;
import java.util.ArrayList;

public class ProveedorController {

    private List<Proveedor> proveedores = new ArrayList<>();

    public List<Proveedor> getAllProveedores() {
        return proveedores;
    }

    public Proveedor getProveedor(int id) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null;
    }

    public void addProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public void updateProveedor(Proveedor proveedor) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getId() == proveedor.getId()) {
                proveedores.set(i, proveedor);
                break;
            }
        }
    }

    public void deleteProveedor(int id) {
        proveedores.removeIf(proveedor -> proveedor.getId() == id);
    }
}
