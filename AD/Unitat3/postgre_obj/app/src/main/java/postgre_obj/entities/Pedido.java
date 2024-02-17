package postgre_obj.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    private int id;
    private int idProducto;
    private Date fecha;
    private int idCliente;
    private int cantidad;

    public Pedido(int id, int idProducto, Date fecha, int idCliente, int cantidad, Producto producto, Cliente cliente) {
        this.id = id;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.producto = producto;
        this.cliente = cliente;
    }

    public Pedido() {
    }
    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}