package postgre_obj.controllers;

import postgre_obj.entities.Producto;
import java.util.List;
import java.util.ArrayList;

public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public List<Producto> getAllProductos() {
        return productos;
    }

    public Producto getProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public void updateProducto(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    public void deleteProducto(int id) {
        productos.removeIf(producto -> producto.getId() == id);
    }
}
