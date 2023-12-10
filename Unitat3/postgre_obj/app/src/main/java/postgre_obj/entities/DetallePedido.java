package postgre_obj.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetallePedido {
    @Id
    private int idPedido;
    private int idProducto;
    private int cantidad;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getters y Setters
}