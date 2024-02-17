
package postgre_obj.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Producto {
    @Id
    private int id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private int stock;
    
    @OneToMany(mappedBy = "producto")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "producto")
    private List<DetalleFactura> detallesFactura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getters y Setters
}