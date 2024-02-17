package postgre_obj.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Factura{
    @Id
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private int precioUnitario;

    public int getIdFactura() { 
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
